package com.mobile.efar.database;

import static com.mobile.efar.database.DatabaseConstants.*;
import com.mobile.efar.database.DatabaseOpener;

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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unused")
	private void openDatabase(){
		db_opener = new DatabaseOpener(activity_context); 
    }
	
	
	@SuppressWarnings("unused")
	private void addEfar(String name, String phone, String address, String time, String skill){
        SQLiteDatabase db = db_opener.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(ADDRESS_TAG, address);
        values.put(TIME_AVAILABLE, time);
        values.put(SKILL_AVAILABLE, skill);
        db.insert(TABLE_BLOCK_EFARS, null, values);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	private Cursor getCursor(String table_name){
        SQLiteDatabase db = db_opener.getReadableDatabase();
        String[] columns = {_ID, NAME, PHONE, ADDRESS_TAG, TIME_AVAILABLE, SKILL_AVAILABLE};

        Cursor cursor = db.query(table_name, columns, null, null, null, null, null);
        CursorLoader cursor_loader = new CursorLoader(activity_context);
        cursor_loader.deliverResult(cursor);

        return cursor;
    }

    @SuppressWarnings("unused")
	private void del(String table_name, int id){
        SQLiteDatabase db = db_opener.getWritableDatabase();
        db.delete(table_name, _ID + "=" + id, null);
    }

    private void update(){
       /* ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.update(TABLE_NAME, values, _ID + "=" + id, null);*/

    }

}
