
package com.efar.activity;

import java.util.ArrayList;
import java.util.List;

import com.efar.adapter.EventAdapter;
import com.efar.adapter.RecordAdapter;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**CSIT 6000B
 *@author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 *
 */
public class RecordActivity extends Activity {
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_record;
	private ImageButton test;
	private List<RecordModel> records = new ArrayList<RecordModel>(); 
	private ListView lv_list;
	private RecordAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list_view); 
		layout();
		getRecord();
	}

	private void layout(){
		 // Menu operation.
		//Click event to open contact list
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, ContactListActivity.class);
				startActivity(intent);
			}
		});
		
		//Click event to open Event list
		imagebutton_event = (ImageButton) findViewById(R.id.button_event);
		imagebutton_event.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, EventActivity.class);
				startActivity(intent);
			}
		});
		
		//Click event to open Record list
		imagebutton_record = (ImageButton) findViewById(R.id.button_record);
		imagebutton_record.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, RecordListActivity.class);
				startActivity(intent);
			}
		});
		

		test = (ImageButton) findViewById(R.id.button_setting);
		test.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, EfarListActivity.class);
				startActivity(intent);
			}
		});
		
	}

	public void getRecord(){
		EmbededDatabase dbhelper = new EmbededDatabase();
		records = dbhelper.getAllRecords();
		lv_list = (ListView)findViewById(R.id.event_list);
		mAdapter = new RecordAdapter(this, records);
		lv_list.setAdapter(mAdapter);
	}


}
