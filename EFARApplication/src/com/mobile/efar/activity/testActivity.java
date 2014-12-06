package com.mobile.efar.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.net.Uri;

import com.example.efar.R;
import com.mobile.efar.contentobserver.SMSContentObserver;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class testActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TextView showMessege;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_list_view); 
		String allSMS = getSmsInPhone();
		showMessege = (TextView)findViewById(R.id.record_list);
		showMessege.setText(allSMS);
		//Log.d("ReceiveSendSMS", getSmsInPhone());
		//SMSContentObserver content = new SMSContentObserver(new Handler(), this);
		//this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
	}
	
	//Get Unread SMS from inbox  -- return as a String includes all unread SMS
	public String getSmsInPhone() 
	{ 
		final String SMS_URI_ALL = "content://sms/"; 
		final String SMS_URI_INBOX = "content://sms/inbox"; 
		final String SMS_URI_SEND = "content://sms/sent"; 
		final String SMS_URI_DRAFT = "content://sms/draft"; 
	
		StringBuilder smsBuilder = new StringBuilder(); 
	
		try{ 
		ContentResolver cr = getContentResolver(); 
		String[] projection = new String[]{"_id", "address", "person", 
		"body", "date", "read"}; 
		Uri uri = Uri.parse(SMS_URI_INBOX); 
		Cursor cur = cr.query(uri, projection, null, null, "date desc"); 
	
		if (cur.moveToFirst()) { 
		String name; 
		String phoneNumber; 
		String smsbody; 
		String date; 
		String type; 
	
		int nameColumn = cur.getColumnIndex("person"); 
		int phoneNumberColumn = cur.getColumnIndex("address"); 
		int smsbodyColumn = cur.getColumnIndex("body"); 
		int dateColumn = cur.getColumnIndex("date"); 
		int typeColumn = cur.getColumnIndex("read"); 
	
		do{ 
			name = cur.getString(nameColumn); 
			phoneNumber = cur.getString(phoneNumberColumn); 
			smsbody = cur.getString(smsbodyColumn); 
	
			SimpleDateFormat dateFormat = new SimpleDateFormat( 
			"yyyy-MM-dd hh:mm:ss"); 
			Date d = new Date(Long.parseLong(cur.getString(dateColumn))); 
			date = dateFormat.format(d); 
		
			int typeId = cur.getInt(typeColumn); 
			if(typeId == 0){ 
				type = "Î´¶Á"; 
				smsBuilder.append(phoneNumber);
				smsBuilder.append("#");
				smsBuilder.append(date); 
				smsBuilder.append("#");
				smsBuilder.append(smsbody); 
				smsBuilder.append("&");

			} else if(typeId == 1){ 
				type = "ÒÑ¶Á"; 
			} else { 
			type = ""; 
			} 
		if(smsbody == null) smsbody = ""; 
		}while(cur.moveToNext()); 
		} else { 
		smsBuilder.append("no result!"); 
		} 
	
		smsBuilder.append("getSmsInPhone has executed!"); 
		} catch(SQLiteException ex) { 
		Log.d("SQLiteException in getSmsInPhone", ex.getMessage()); 
		} 
		return smsBuilder.toString(); 
		}
		
	
}
