/**
 * Created by Michyo SONG
 * Created Date: 28/11/2014
 * Description: Database constants used in
 * 				Database package.
 */

package com.efar.database;

public class DatabaseConstants {
	
	/**
	 * These constants point out location of database.
	 */
	private final static int DATABASE_VERSION = 3;
	
	public static final String DATABASE_PATH = android.os.Environment
    .getExternalStorageDirectory().getAbsolutePath() + "/efar_test";
	
	public static final String DATABASE_NAME = "efar.sqlite";
	
	public static String getDatabaseFullPath() {
		return DATABASE_PATH + DATABASE_VERSION + DATABASE_NAME;
	}

	/**
	 * These constants are related with EFAR table.
	 */
	public static final String ID = "id";
	public static final String TABLE_BLOCK_EFARS = "block_efars";
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String ADDRESS_TAG = "address_tag";
	public static final String TIME_AVAILABLE = "time_available";
	public static final String SKILL_AVAILABLE = "skill_available";
	
	/**
	 * These constants are related with Event-Efars table.
	 */
	public static final String TABLE_EVENTS = "events_efars";
	public static final String EVENT_NAME = "event_name";
	public static final String EFAR_ID = "efar_id";
	
	/**
	 * These constants are related with Records table.
	 */
	public static final String TABLE_RECORDS = "records";
	// public static final String EVENT_NAME = "event_name";
	public static final String EVENT_DETAIL = "event_detail";
	public static final String RELATED_EFARS = "related_efars";

}
