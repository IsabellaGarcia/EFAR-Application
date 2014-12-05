/** 
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: Activity for Emergency Events list
*/
package com.mobile.efar.activity;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.efar.R;
import com.mobile.efar.adapter.EventAdapter;
import com.mobile.efar.datamodel.EventModel;
import android.widget.TextView; 
public class EventActivity extends Activity{
	//New for receiving SMS from broadcast receiver
	private static final String LOG_TAG ="SMSReceiver"; 
	private TextView senderName;
	private TextView sendTime;
	private TextView address_tag;
	private TextView body;
	
	private ListView lv_list;
	private EventAdapter mAdapter;
	private TextView textView; 
	
	//For 4 button to skip to other interface
	private FrameLayout container;
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_record;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Emergency Events List");
		setContentView(R.layout.event_list_view); 
		Layout();
	}
	
	

	private List<EventModel> getData() {
		List<EventModel> list = new ArrayList<EventModel>();
		EventModel event1 = new EventModel();
		//event1.setEvent_name("Emergency from Block B");
		list.add(event1);
		EventModel event2 = new EventModel();
		//event2.setEvent_name("Emergency from Bloc");
		list.add(event2);
		return list;
	}  
	
	//Initialize the layout of this activity
	private void Layout(){
		 lv_list = (ListView) findViewById(R.id.event_list);
		 mAdapter = new EventAdapter(this, getData());
		 lv_list.setAdapter(mAdapter);
		 container = (FrameLayout) findViewById(R.id.framelayout_main);
		 
		//Click to open a record list
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
			
	}
	 
}
