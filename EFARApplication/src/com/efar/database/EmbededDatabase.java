/**
 * CSIT 6000B
 */
package com.efar.database;

import static com.efar.database.DatabaseConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.efar.datamodel.RecordModel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Michyo
 * Enable embedded database released with application.
 * Changed by Xinyi HUANG
 */
public class EmbededDatabase {
	
	private SQLiteDatabase embededDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabaseFullPath(), null);
	
	public void addEfar(EfarModel efar) {
        ContentValues values = new ContentValues();
        values.put(NAME, efar.getName());
        values.put(PHONE, efar.getPhone());
        values.put(ADDRESS_TAG, efar.getAddressTag());
        values.put(TIME_AVAILABLE, efar.getTimeAvailable());
        //values.put(SKILL_AVAILABLE, efar.getSkillAvailable());
        embededDatabase.insert(TABLE_BLOCK_EFARS, null, values);
    }
	
	public List<EfarModel> getAllEfar() {
		List<EfarModel> result = new ArrayList<EfarModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_BLOCK_EFARS, null);
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		//efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddressTag(cursor.getString(3));
    		efar.setTimeAvailable(cursor.getString(4));
    		//efar.setSkillAvailable(cursor.getString(5));
    		result.add(efar);
    	}    	
    	embededDatabase.close();
    	return result;
	}
	
	// ---- Search by Address and time   
	public List<EfarModel> getEfarByAddress(String address_tag, String time) {
		List<EfarModel> result = new ArrayList<EfarModel>();
		
		Log.v(address_tag, address_tag); 
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_BLOCK_EFARS
    		+ " where "+ TIME_AVAILABLE +" = ? AND " + ADDRESS_TAG +" = ?" ,
    			new String[]{time, address_tag});
    			//null);
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddressTag(cursor.getString(3));
    		efar.setTimeAvailable(cursor.getString(4));
    		//efar.setSkillAvailable(cursor.getString(5));
    		result.add(efar);
    	}    	
    	cursor.close();
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
		values.put(RELATED_EFARS, event.getSend_list());
		values.put(EVENT_DETAIL, event.getDescription());
		embededDatabase.insert(TABLE_RECORDS, null, values);
	}
	
	public List<RecordModel> getAllRecords() {
		List<RecordModel> result = new ArrayList<RecordModel>();
    	Cursor cursor = embededDatabase.rawQuery("select * from "+ TABLE_RECORDS, null);
    	while(cursor.moveToNext()){
    		RecordModel record = new RecordModel();
    		record.setId(cursor.getInt(0));
    		record.setEventName(cursor.getString(1));
    		record.setSend_list(cursor.getString(2));
    	//	Toast.makeText(context,record.getSend_list(), Toast.LENGTH_SHORT).show();
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
