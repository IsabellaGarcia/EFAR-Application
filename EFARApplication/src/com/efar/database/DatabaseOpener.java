/**
 * < No Use >
 * Created by Michyo SONG
 * Created Date: 28/11/2014
 * Description: Database helper extends from SQLiteOpenHelper.
 */

package com.efar.database;

import static com.efar.database.DatabaseConstants.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpener extends SQLiteOpenHelper {
	
    private final static int DATABASE_VERSION = 2;
    
	public DatabaseOpener(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void onCreate(SQLiteDatabase db) {
        final String INIT_EFAR_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BLOCK_EFARS + " (" +
                                  ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  NAME + " CHAR, " +
                                  PHONE + " CHAR, " +
                                  ADDRESS_TAG + " CHAR, " +
                                  TIME_AVAILABLE + " CHAR, " +
                                  SKILL_AVAILABLE + " CHAR);"; 
        db.execSQL(INIT_EFAR_TABLE);
        final String INIT_EVENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_NAME + " CHAR, " +
                EFAR_ID + " INTEGER);"; 
        db.execSQL(INIT_EVENT_TABLE);
    }
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/**
		 * Drop all tables if exist.
		 */
        final String DROP_EFAR_TABLE = "DROP TABLE IF EXISTS " + TABLE_BLOCK_EFARS;
        db.execSQL(DROP_EFAR_TABLE);
        final String DROP_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_EVENTS;
        db.execSQL(DROP_EVENT_TABLE);
        
        onCreate(db);
    }
}
