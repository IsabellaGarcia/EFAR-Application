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
import android.util.Log;
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
	private List<EventModel> list = new ArrayList<EventModel>();
	private TextView senderName;
	private TextView sendTime;
	private TextView address_tag;
	private TextView body;
	
	private ListView lv_list;
	private EventAdapter mAdapter;
	
	//For 4 button to skip to other interface
	private FrameLayout container;
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_record;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.d("",List)
		setTitle("Emergency List");
		setContentView(R.layout.event_list_view); 
		Layout();
		
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
	
	private List<EventModel> getData() {
		EventModel e = new EventModel();
		e.setPhone("11");
		e.setTime("2014-11-11");
		list.add(e);
		return list;
	} 
	
}
