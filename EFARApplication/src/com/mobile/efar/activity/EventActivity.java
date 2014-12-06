package com.mobile.efar.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Handler;
import android.net.Uri;

import com.example.efar.R;
import com.mobile.efar.adapter.EventAdapter;
import com.mobile.efar.contentobserver.SMSContentObserver;
import com.mobile.efar.datamodel.EventModel;

import android.R.integer;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class EventActivity extends Activity{
	private List<EventModel> list = new ArrayList<EventModel>();
	private String allSMS;
	private ListView lv_list;
	private EventAdapter mAdapter;
	private static final String queryString = "@";
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_record;
	private String[] messeges;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list_view); 
		allSMS = getSmsInPhone();	
		layout();
	}
	
	//Get Unread SMS from inbox  -- return as a String includes all unread SMS
	private String getSmsInPhone() 
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
	
		//smsBuilder.append("getSmsInPhone has executed!"); 
		} catch(SQLiteException ex) { 
		Log.d("SQLiteException in getSmsInPhone", ex.getMessage()); 
		} 
		return smsBuilder.toString(); 
		}
	
	//Get separated SMS and store in list
	private List<EventModel> getSeparateSMS(String S){
		
		messeges = S.split("&");
		for(int j = 0; j<messeges.length; j++){
			String[] info = messeges[j].split("#");
			//if(info[2].startsWith(queryString)){
				EventModel event1= new EventModel();
				//event1.setId(i);
				event1.setPhone(info[0]);
				//senderName = (TextView)findViewById(R.id.sender_name);
				//senderName.setText(event1.getPhone());
				event1.setTime(info[1]);
				//sendTime = (TextView)findViewById(R.id.send_time);
				//sendTime.setText(event1.getTime());
				event1.setAddress_tag(info[2]);
				//address_tag = (TextView)findViewById(R.id.address_tag);
				//address_tag.setText(event1.getAddress_tag());
				event1.setDescription(info[3]);
				//body = (TextView)findViewById(R.id.body);
				//body.setText(event1.getDescription());
				list.add(event1);
			//}
			
		}
		return list;
	}

	//Initialization of layout
	private void layout(){
		lv_list = (ListView)findViewById(R.id.event_list);
		if(allSMS!="no result"){
			mAdapter = new EventAdapter(this, getSeparateSMS(allSMS));
			lv_list.setAdapter(mAdapter);
		}
		imagebutton_record = (ImageButton) findViewById(R.id.button_record);
		imagebutton_record.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(EventActivity.this, RecordActivity.class);
					startActivity(intent);
					finish();
				}
			});
		
		//Click to open a contact list
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(EventActivity.this, ContactListActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//Click on ListView
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 Intent in = new Intent(EventActivity.this,EventDetail.class);
				 Bundle bundle2 = new Bundle();
				 StringBuilder sb = new StringBuilder();
				 sb.append(messeges[position]);
				 sb.append("&");
				 ArrayList<String> list_t=new ArrayList<String>(); 
				 list_t.add(sb.toString());
				 bundle2.putStringArrayList("message", list_t);			 
				 in.putExtras(bundle2);
			     //destroy other activities
				 //in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 startActivity(in); 				
			}
		});
		
	}
	
	
	
}
