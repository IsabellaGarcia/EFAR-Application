/** 
* Created by Xinyi HUANG
* Created Date: 06/12/2014
* Description: Activity for One Event
*/

package com.mobile.efar.activity;

import java.util.ArrayList;

import com.example.efar.R;
import com.mobile.efar.datamodel.EventModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

public class EventDetail extends Activity{
	
	//New for receiving SMS from broadcast receiver
	private static final String LOG_TAG ="SMSReceiver"; 
	private TextView senderName;
	private TextView sendTime;
	private TextView address_tag;
	private TextView body;	

	private Button all_contacts;
	private Button search_contacts;
	private Button done;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setTitle("Emergency Events List");
	setContentView(R.layout.event_detail); 
	Layout();
}

protected void onStart(){
	super.onStart();		
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

private void Layout(){
	 
	//Click to open a record list
	all_contacts = (Button) findViewById(R.id.all_contacts);
	all_contacts.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(EventDetail.this, ContactListActivity.class);
				startActivity(intent);
			}
		});
	
	done = (Button) findViewById(R.id.done_indetail);
	done.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});	
}
}