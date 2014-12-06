package com.mobile.efar.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.efar.R;
import com.mobile.efar.adapter.EventAdapter;
import com.mobile.efar.datamodel.EventModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class EventDetail extends Activity{

	
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
}
