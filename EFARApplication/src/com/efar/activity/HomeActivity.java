/** CSIT 6000B
* @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
* Email: xhuangap@connect.ust.hk
* Description: Home Activity(Main Activity)
* 			   Link to ContactActivity/EventActivity/RecordActivity
*/ 

package com.efar.activity;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

public class HomeActivity extends Activity{
	
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_record;
	private ImageButton test;
	private TextView menu_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
			
		EmbededDatabase dbhelper = new EmbededDatabase();
		EfarModel efar = dbhelper.getEfarById(2);
	
		layout();
	}
	
	private void layout(){
		 
		//Click event to open contact list
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
				intent.setClass(HomeActivity.this, RecordListActivity.class);
				startActivity(intent);
			}
		});
		
		test = (ImageButton) findViewById(R.id.button_setting);
		test.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, AddEfarActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
}

