/** 
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: Home Activity(Main Activity)
* 			   Link to ContactActivity/EventActivity/RecordActivity
*/ 

package com.efar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.efar.application.EfarApplication;
import com.efar.database.DatabaseHelper;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

public class HomeActivity extends Activity{
	
	private FrameLayout container;
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_record;
	private ImageButton test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		// Test for EfarListActivity.
		EfarApplication global_variables = (EfarApplication) this.getApplication();
		EventModel event_now = new EventModel();
		event_now.setAddress_tag("2");
		global_variables.setEventNow(event_now);
		
		EmbededDatabase dbhelper = new EmbededDatabase();
		EfarModel efar = dbhelper.getEfarById(2);
		Toast.makeText(getApplicationContext(), efar.getAddressTag(),
			     Toast.LENGTH_SHORT).show();
		// Test end.
		
		//Click event to open contact list
		container = (FrameLayout) findViewById(R.id.framelayout_main);
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, ContactListActivity.class);
				startActivity(intent);
			}
		});
		
		//Click event to open Event list
		imagebutton_event = (ImageButton) findViewById(R.id.button_event);
		imagebutton_event.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, EventActivity.class);
				startActivity(intent);
			}
		});
		
		//Click event to open Record list
		imagebutton_record = (ImageButton) findViewById(R.id.button_record);
		imagebutton_record.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, RecordActivity.class);
				startActivity(intent);
			}
		});
		

		test = (ImageButton) findViewById(R.id.button_setting);
		test.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, EfarListActivity.class);
				startActivity(intent);
			}
		});
		
	}
}