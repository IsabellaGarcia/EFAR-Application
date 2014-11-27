/** 
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: Activity for Emergency Events list
*/
package com.mobile.efar.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.efar.R;
import com.mobile.efar.adapter.EventAdapter;
import com.mobile.efar.datamodel.eventModel;

public class EventActivity extends Activity{
	private ListView lv_list;
	private FrameLayout container;
	private EventAdapter mAdapter;
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_record;
	private TextView textView; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Emergency Events List");
		 setContentView(R.layout.event_list_view);
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
				 
		/* textView = (TextView) findViewById(R.id.textView1);  
		Intent intent = getIntent();
		if(intent != null){
			  String address = intent.getStringExtra("sms_address");  
			  if (address != null) { 
				  textView.append("\n\n发件人：\n" + address);  
				  String bodyString = intent.getStringExtra("sms_body"); 
				  if (bodyString != null) { 
					  textView.append("\n短信内容：\n" + bodyString); 
				  }
			  }
		}*/
	}
	
	private List<eventModel> getData() {
		List<eventModel> list = new ArrayList<eventModel>();
		eventModel event1 = new eventModel();
		event1.setEvent_name("Emergency from Block B");
		list.add(event1);
		eventModel event2 = new eventModel();
		event2.setEvent_name("Emergency from Bloc");
		list.add(event2);
		return list;
	}  
	 
}
