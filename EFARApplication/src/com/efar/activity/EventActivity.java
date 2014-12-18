/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 * Created Date: 26/11/2014
 * Description: Activity for finding unread SMS in phone
 */

package com.efar.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import android.os.Handler;
import android.net.Uri;

import com.efar.adapter.EventAdapter;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

import android.R.integer;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
import android.widget.Toast;

public class EventActivity extends Activity{
	private List<EventModel> list = new ArrayList<EventModel>();
	private String allSMS;
	private ListView lv_list;
	private EventAdapter mAdapter;
	private final static int REQUEST_CODE = 2 ;
	private static final String queryString = "@";
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_record;
	private ImageButton test;
	final String SMS_URI_INBOX = "content://sms/inbox"; 
	private int index_r;
	private int position_r;
	Cursor cur;
	private List<EventModel> event_l = new ArrayList<EventModel>();
	int day;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list_view); 
		getSmsInPhone();	
		
		if(event_l.size()==0){
			 Toast.makeText(getApplicationContext(), "There is no unprocessed event", Toast.LENGTH_LONG).show();
		}
		layout();
		mAdapter.notifyDataSetChanged();
	}
	
	//Get Unread SMS from inbox  -- return as a String includes all unread SMS
	private String getSmsInPhone() 
	{ 
		//int index_1 = 0;
		StringBuilder smsBuilder = new StringBuilder(); 
		try{ 
		ContentResolver cr = getContentResolver(); 
		String[] projection = new String[]{"_id", "address", "person", 
		"body", "date", "read"}; 
		Uri uri = Uri.parse(SMS_URI_INBOX);   
		cur = cr.query(uri, projection, null, null, "date desc"); 
		
			if (cur.moveToFirst()) { 
			String name; 
			String phoneNumber; 
			String smsbody; 
			String date; 
			String type; 
			int index = 0;
		    int position;
			int nameColumn = cur.getColumnIndex("person"); 
			int phoneNumberColumn = cur.getColumnIndex("address"); 
			int smsbodyColumn = cur.getColumnIndex("body"); 
			int dateColumn = cur.getColumnIndex("date"); 
			int typeColumn = cur.getColumnIndex("read"); 
		   // int index_1 = cur.getColumnIndex("_id");
		    //Toast.makeText(getApplicationContext(), String.valueOf(index_1), Toast.LENGTH_SHORT).show();
				do{ 
					name = cur.getString(nameColumn); 
					phoneNumber = cur.getString(phoneNumberColumn); 
					smsbody = cur.getString(smsbodyColumn); 
			        position = cur.getInt(0);
	
					SimpleDateFormat dateFormat = new SimpleDateFormat( 
					"yyyy-MM-dd hh:mm:ss"); 
					Date d = new Date(Long.parseLong(cur.getString(dateColumn))); 
					date = dateFormat.format(d); 
				    
					day = getDay(d);
					
					int typeId = cur.getInt(typeColumn); 
					if((typeId == 0)&&(smsbody.startsWith(queryString))){ 
						type = "Î´¶Á"; 
						EventModel event_temp = new EventModel();
						event_temp.setPhone(phoneNumber);
						event_temp.setTime(date);
						
						String messeges[] = smsbody.split("#");
						event_temp.setAddress_tag(messeges[0]);
						event_temp.setDescription(messeges[1]);
						event_temp.setWeekday(String.valueOf(day));
						event_temp.setPosition(position);
						event_temp.setIndex(index);
						index++;
						event_l.add(event_temp);
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
	

	//Initialization of layout
	private void layout(){
		lv_list = (ListView)findViewById(R.id.event_list);
		if(allSMS!="no result"){
			mAdapter = new EventAdapter(this, event_l);
			lv_list.setAdapter(mAdapter);
		}
		imagebutton_record = (ImageButton) findViewById(R.id.button_record);
		imagebutton_record.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(EventActivity.this, RecordListActivity.class);
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
		
		//Click to switch to the AddEfarActivity
		test = (ImageButton) findViewById(R.id.button_setting);
		test.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(EventActivity.this, AddEfarActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//Click on ListView
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				 Intent in = new Intent(EventActivity.this, EventDetail.class);
				 Bundle bundle2 = new Bundle(); 
				 bundle2.putSerializable("eventinfo",event_l.get(position));
				 in.putExtras(bundle2);
				 index_r = event_l.get(position).getIndex();
				 position_r = event_l.get(position).getPosition();
			     //destroy other activities
				 //in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 startActivityForResult(in,REQUEST_CODE); 				
			}
		});
		mAdapter.notifyDataSetChanged();
	}
	protected void onStart(){
		 super.onStart(); 
		mAdapter.notifyDataSetChanged();
	}
	//get the index of weekday of the time
	public int getDay(Date date) {
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal.get(Calendar.DAY_OF_WEEK);
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    
	    super.onActivityResult(requestCode, resultCode, data);      
	    if(requestCode == REQUEST_CODE){  
	        if(resultCode == EventDetail.RESULT_CODE){               
	        	 ContentValues values = new ContentValues();  
	        	 values.put("read", "1");
	        	 //int a[] = data.getIntArrayExtra("position");
	        	// Toast.makeText(getApplicationContext(), String.valueOf(index_r),
	       			//     Toast.LENGTH_SHORT).show();
	        	 //Toast.makeText(getApplicationContext(), String.valueOf(position_r),
	       			//     Toast.LENGTH_SHORT).show();
	        	 event_l.remove(index_r);
	        	 mAdapter.notifyDataSetChanged();
	        	 
	        	 ContentValues values_1 = new ContentValues();  

	             values_1.put("read", "1");         

	             getContentResolver().update(Uri.parse("content://sms/inbox"), values_1, "_id = ?", new String[]{String.valueOf(position_r)});  
	        } 
	    }
	}

}
