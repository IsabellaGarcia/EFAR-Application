/** 
* @author Xinyi HUANG
* Created Date: 26/11/2014
* Description: Activity for Record list
* 			   
*/
package com.efar.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.efar.adapter.RecordAdapter;
import com.efar.database.*;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;


import static com.efar.database.DatabaseConstants.*;

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
		setContentView(R.layout.record_list_view);
		lv_list = (ListView)findViewById(R.id.record_list);
		mAdapter = new RecordAdapter(this, getData());
		lv_list.setAdapter(mAdapter);
		container = (FrameLayout) findViewById(R.id.framelayout_main);
		
		/*DatabaseHelper dbhelper = new DatabaseHelper(this);
		dbhelper.addEfar("a", null, null, null, null);
		Cursor cursor = dbhelper.getById(TABLE_BLOCK_EFARS, 1);
		EfarModel contact = reductEfar(cursor);
		Toast.makeText(getApplicationContext(), contact.getName(),
			     Toast.LENGTH_SHORT).show();*/
		
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordActivity.this, ContactListActivity.class);
				startActivity(intent);
				finish();
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
				finish();
			}
		});
		
	
	}
	
	private List<RecordModel> getData() {
		List<RecordModel> list = new ArrayList<RecordModel>();
		RecordModel record1 = new RecordModel();
		record1.setRecord_name("Emergency M in xxxx");
		list.add(record1);
		return list;
	}  
	
}
