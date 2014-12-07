package com.efar.contentobserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class SMSContentObserver extends ContentObserver{
	private Context mcontext;
	
	public SMSContentObserver(Handler handler ,Context context) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.mcontext = context;
	}
	
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		
		//Read all SMS in inbox
		Cursor cursor = this.mcontext.getContentResolver().query(Uri.parse("content://sms/inbox"),
				null, null, null, null);  
		 while (cursor.moveToNext()){
			 StringBuilder sb = new StringBuilder();
			 
			  sb.append("_id=").append(cursor.getInt(cursor.getColumnIndex("_id")));

	          sb.append(",address=").append(cursor.getString(cursor.getColumnIndex("address")));

	          sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));

	          sb.append(";time=").append(cursor.getLong(cursor.getColumnIndex("date")));

	          Log.v("ReceiveSendSMS", sb.toString()); 
		 }
	}

}

	