/** 
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: HActivity for Record list
* 			   
*/
package com.mobile.efar;

import java.util.ArrayList;
import java.util.List;

import com.example.efar.R;
import com.mobile.efar.adapter.RecordAdapter;
import com.mobile.efar.datamodel.recordModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

public class RecordActivity extends Activity{
	private ListView lv_list;
	private RecordAdapter mAdapter;
	private FrameLayout container;
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Historical Record List");
		setContentView(R.layout.list);
		lv_list = (ListView)findViewById(R.id.lv_list);
		mAdapter = new RecordAdapter(this,getData());
		lv_list.setAdapter(mAdapter);
		container = (FrameLayout) findViewById(R.id.framelayout_main);
		
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, ContactActivity.class);
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
	}
	
	private List<recordModel> getData() {
		List<recordModel> list = new ArrayList<recordModel>();
		recordModel record1 = new recordModel();
		record1.setRecord_name("Emergency M in xxxx");
		list.add(record1);
		return list;
	}  
	
}
