/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Activity for Contact List
*/ 

package com.mobile.efar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.efar.R;
import com.mobile.efar.adapter.ContactAdapter;
import com.mobile.efar.datamodel.contactModel;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ContactActivity extends Activity{

    private ListView lv_list;
    private ContactAdapter mAdapter;
    
	private FrameLayout container;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_record;
	
	//for getting data from address book
	private List<contactModel> list;
	// asynchronous query the database class object 
	private AsyncQueryHandler asyncQueryHandler; 
	//Fast index article
	//private QuickAlphabeticBar alphabeticBar;
	private Map<Integer, contactModel> contactIdMap = null;
	
	    public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);        
	        setTitle("Contacts Volunteers List");
	        setContentView(R.layout.list);
	        lv_list = (ListView) findViewById(R.id.lv_list);
	        mAdapter = new ContactAdapter(this,getData());
	        lv_list.setAdapter(mAdapter);
	        
	        container = (FrameLayout) findViewById(R.id.framelayout_main); 
	      //Click event to open Event list
			imagebutton_event = (ImageButton) findViewById(R.id.button_event);
			imagebutton_event.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(ContactActivity.this, EventActivity.class);
					startActivity(intent);
				}
			});
			
			//Click event to open Record list
			imagebutton_record = (ImageButton) findViewById(R.id.button_record);
			imagebutton_record.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(ContactActivity.this, RecordActivity.class);
					startActivity(intent);
				}
			});
	    }
	    
	    //get data into list
		private List<contactModel> getData() {
			List<contactModel> list = new ArrayList<contactModel>();
			contactModel contact1 = new contactModel();
			contact1.setName("Isabell Garcia");
			contact1.setNumber("852-56457255");
			contact1.setBlock_num("B");
			list.add(contact1);
			return list;
		}        
			
		
}
