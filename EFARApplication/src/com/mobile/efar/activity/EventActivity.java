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
		setContentView(R.layout.event_detail); 
		Layout();
	}
	
 	protected void onStart(){
		super.onStart();
		List<EventModel> list = new ArrayList<EventModel>();
		
		Bundle extras = getIntent().getExtras();
		ArrayList<String> data = null;
		if(extras != null){
			data = extras.getStringArrayList("message");
			for(int j = 0; j<data.size(); j++){
				//Split into single SMS
				String[] messeges = data.get(j).split("&");
				/*
				 * Split one SMS
				 * Garcia#2014-11-05 12:17:00#@Central #Event: There is...... &
				 * info[0]: sender
				 * info[1]: time
				 * info[2]: @tag
				 * info[3]: body
				 */
				String[] info = messeges[0].split("#");
				EventModel event1 = new EventModel();
				//NEED TO MODIFY
				int i = 0;
				event1.setId(i);
				event1.setPhone(info[0]);
				senderName = (TextView)findViewById(R.id.sender_name);
				senderName.setText(event1.getPhone());
				event1.setTime(info[1]);
				sendTime = (TextView)findViewById(R.id.send_time);
				sendTime.setText(event1.getTime());
				event1.setAddress_tag(info[2]);
				address_tag = (TextView)findViewById(R.id.address_tag);
				address_tag.setText(event1.getAddress_tag());
				event1.setDescription(info[3]);
				body = (TextView)findViewById(R.id.body);
				body.setText(event1.getDescription());
			}	
		}		
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
		/* lv_list = (ListView) findViewById(R.id.event_list);
		 mAdapter = new EventAdapter(this, getData());
		 lv_list.setAdapter(mAdapter);
		 container = (FrameLayout) findViewById(R.id.framelayout_main);*/
		 
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
