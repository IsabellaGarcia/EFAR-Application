/**
 * 
 */
package com.efar.database;

import static com.efar.database.DatabaseConstants.*;

import java.util.Vector;

import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.efar.datamodel.RecordModel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Michyo
 * Enable embedded database released with application.
 */
public class EmbededDatabase {
	
	private SQLiteDatabase embededDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabaseFullPath(), null);
	
	public void addEfar(String name, String phone, String address, String time, String skill) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(ADDRESS_TAG, address);
        values.put(TIME_AVAILABLE, time);
        values.put(SKILL_AVAILABLE, skill);
        embededDatabase.insert(TABLE_BLOCK_EFARS, null, values);
    }
	
	public void addEfar(EfarModel efar) {
        ContentValues values = new ContentValues();
        values.put(NAME, efar.getName());
        values.put(PHONE, efar.getPhone());
        values.put(ADDRESS_TAG, efar.getAddressTag());
        values.put(TIME_AVAILABLE, efar.getTimeAvailable());
        values.put(SKILL_AVAILABLE, efar.getSkillAvailable());
        embededDatabase.insert(TABLE_BLOCK_EFARS, null, values);
    }
	
	public Vector<EfarModel> getAllEfar() {
		Vector<EfarModel> result = new Vector<EfarModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_BLOCK_EFARS, null);
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddressTag(cursor.getString(3));
    		efar.setTimeAvailable(cursor.getString(4));
    		efar.setSkillAvailable(cursor.getString(5));
    		result.add(efar);
    	}    	
    	embededDatabase.close();
    	return result;
	}
	
	public Vector<EfarModel> getEfarByAddress(String address_tag) {
		Vector<EfarModel> result = new Vector<EfarModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_BLOCK_EFARS + 
    			" where "+ ADDRESS_TAG +" = ? ",
    			new String[]{address_tag.trim()});
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddressTag(cursor.getString(3));
    		efar.setTimeAvailable(cursor.getString(4));
    		efar.setSkillAvailable(cursor.getString(5));
    		result.add(efar);
    	}    	
    	embededDatabase.close();
    	return result;
	}
	
	public Vector<EfarModel> getEfarBySkill(String skill) {
		Vector<EfarModel> result = new Vector<EfarModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_BLOCK_EFARS + 
    			" where "+ SKILL_AVAILABLE +" = ? ",
    			new String[]{skill.trim()});
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddressTag(cursor.getString(3));
    		efar.setTimeAvailable(cursor.getString(4));
    		efar.setSkillAvailable(cursor.getString(5));
    		result.add(efar);
    	}    	
    	embededDatabase.close();
    	return result;
	}
	
	public void addRecord(EventModel event) {
		String event_name = event.getPhone() + "@" + event.getTime();
		ContentValues values = new ContentValues();
		values.put(EVENT_NAME, event_name);
		values.put(RELATED_EFARS, event.getRelatedEfars());
		values.put(EVENT_DETAIL, event.generateDetail());
		embededDatabase.insert(TABLE_RECORDS, null, values);
	}
	
	public Vector<RecordModel> getAllRecords() {
		Vector<RecordModel> result = new Vector<RecordModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_RECORDS, null);
    	while(cursor.moveToNext()){
    		RecordModel record = new RecordModel();
    		record.setId(cursor.getInt(0));
    		record.setEventName(cursor.getString(1));
    		record.setRelatedEfars(cursor.getString(2));
    		record.setEventDetail(cursor.getString(3));
    		result.add(record);
    	}    	
    	embededDatabase.close();
    	return result;
	}
	
    /**
     * Reduct from a cursor to build a class of EfarModel.
     * @param table_name
     * @param id
     * @return
     */
	public EfarModel getEfarById(int id) {
        Cursor cursor = embededDatabase.query(TABLE_BLOCK_EFARS, null, ID + "=?", new String[]{String.valueOf(id)}, null, null, null);  
        EfarModel result = new EfarModel();
		if(cursor != null && cursor.moveToFirst()){   
            result.setId(cursor.getInt(cursor.getColumnIndex(ID)));  
            result.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            result.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            result.setAddressTag(cursor.getString(cursor.getColumnIndex(ADDRESS_TAG)));
            result.setTimeAvailable(cursor.getString(cursor.getColumnIndex(TIME_AVAILABLE)));
            result.setSkillAvailable(cursor.getString(cursor.getColumnIndex(SKILL_AVAILABLE)));
        }  
		embededDatabase.close();
        return result;  
    }
	
	public EfarModel getEfarByName(String name) {
        Cursor cursor = embededDatabase.query(TABLE_BLOCK_EFARS, null, NAME + "=?", new String[]{name}, null, null, null);  
        EfarModel result = new EfarModel();
		if(cursor != null && cursor.moveToFirst()){   
            result.setId(cursor.getInt(cursor.getColumnIndex(ID)));  
            result.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            result.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            result.setAddressTag(cursor.getString(cursor.getColumnIndex(ADDRESS_TAG)));
            result.setTimeAvailable(cursor.getString(cursor.getColumnIndex(TIME_AVAILABLE)));
            result.setSkillAvailable(cursor.getString(cursor.getColumnIndex(SKILL_AVAILABLE)));
        }  
		embededDatabase.close();
        return result;  
    }

	public void delete(String table_name, int id) {
		embededDatabase.delete(table_name, ID + "=" + id, null);
		embededDatabase.close();
    }

	public void update(String table_name, int id, ContentValues values) {
		embededDatabase.update(table_name, values, ID + "=" + id, null);
		embededDatabase.close();
    }
}
