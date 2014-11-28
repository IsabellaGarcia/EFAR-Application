/**
 * Created by Michyo SONG
 * Created Date: 28/11/2014
 * Description: Database helper extends from SQLiteOpenHelper.
 */

package com.mobile.efar.database;

import static com.mobile.efar.database.DatabaseConstants.*;
import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpener extends SQLiteOpenHelper {
	
    private final static String DATABASE_NAME = "test.db";
    private final static int DATABASE_VERSION = 1;
    
	public DatabaseOpener(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void onCreate(SQLiteDatabase db) {
        final String INIT_TABLE = "CREATE TABLE " + TABLE_BLOCK_EFARS + " (" +
                                  _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  NAME + " CHAR, " +
                                  PHONE + " CHAR, " +
                                  ADDRESS_TAG + " CHAR, " +
                                  TIME_AVAILABLE + " CHAR, " +
                                  SKILL_AVAILABLE + " CHAR);"; 
        db.execSQL(INIT_TABLE);
    }
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BLOCK_EFARS;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
