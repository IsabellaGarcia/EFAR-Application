/**
 * Created by Michyo SONG
 * Created Date: 28/11/2014
 * Description: Database operations help,
 * 				include add, delete and 
 * 				so on.
 */

package com.efar.database;

import static com.efar.database.DatabaseConstants.*;

import java.util.Vector;

import com.efar.database.DatabaseOpener;
import com.efar.datamodel.EfarModel;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import static android.provider.BaseColumns._ID;

public class DatabaseHelper {

	private DatabaseOpener db_opener = null;
	private Context activity_context = null;
	
	public DatabaseHelper(Context c) {
		activity_context = c;
		openDatabase();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void openDatabase(){
		db_opener = new DatabaseOpener(activity_context); 
    }
	
	public void addEfar(String name, String phone, String address, String time, String skill) {
        SQLiteDatabase db = db_opener.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(ADDRESS_TAG, address);
        values.put(TIME_AVAILABLE, time);
        values.put(SKILL_AVAILABLE, skill);
        db.insert(TABLE_BLOCK_EFARS, null, values);
    }
	
	public Vector<EfarModel> getEfarByAddress(String address_tag) {
		Vector<EfarModel> result = new Vector<EfarModel>();
    	SQLiteDatabase db = db_opener.getReadableDatabase();
    	Cursor cursor = db.rawQuery("select * from "+ TABLE_BLOCK_EFARS + 
    			" where "+ ADDRESS_TAG +" = ? ",
    			new String[]{address_tag.trim()});
    	while(cursor.moveToNext()){
    		EfarModel efar = new EfarModel();
    		efar.setId(cursor.getInt(0));
    		efar.setName(cursor.getString(1));
    		efar.setPhone(cursor.getString(2));
    		efar.setAddress_tag(cursor.getString(3));
    		efar.setTime_available(cursor.getString(4));
    		efar.setSkill_available(cursor.getString(5));
    		result.add(efar);
    	}    	
    	db.close();
    	return result;
	}

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	private Cursor getEfarCursor(String table_name) {
        SQLiteDatabase db = db_opener.getReadableDatabase();
        String[] columns = {_ID, NAME, PHONE, ADDRESS_TAG, TIME_AVAILABLE, SKILL_AVAILABLE};
        Cursor cursor = db.query(table_name, columns, null, null, null, null, null);
        CursorLoader cursor_loader = new CursorLoader(activity_context);
        cursor_loader.deliverResult(cursor);
        db.close();
        return cursor;
    }
    
	public Cursor getById(String table_name, int id) {
    	SQLiteDatabase db = db_opener.getReadableDatabase();  
        Cursor cursor = db.query(table_name, null, _ID + "=?", new String[]{String.valueOf(id)}, null, null, null);  
        db.close();
        return cursor;
    }

	public void delete(String table_name, int id) {
        SQLiteDatabase db = db_opener.getWritableDatabase();
        db.delete(table_name, _ID + "=" + id, null);
        db.close();
    }

	public void update(String table_name, int id, ContentValues values) {
       /* ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());*/

        SQLiteDatabase db = db_opener.getWritableDatabase();
        db.update(table_name, values, _ID + "=" + id, null);
        db.close();
    }
}
