/** 
* /**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 * Created Date: 06/12/2014
 * Description: Activity for One Event
 */

/** 
* @author Xinyi HUANG
* Revised Date: 14/12/2014
* Description: Connect with Searching for contacts activity
*/
package com.efar.activity;

import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class EventDetail extends Activity{
	
	//New for receiving SMS from broadcast receiver
	private static final String LOG_TAG ="SMSReceiver"; 
	private final static int REQUEST_CODE = 1 ;
    public final static int RESULT_CODE = 2;
	StringBuilder sm_for_event = new StringBuilder();
	private EmbededDatabase dbhelper = new EmbededDatabase();
	private TextView senderName;
	private TextView sendTime;
	private TextView address_tag;
	private TextView body;	
	private TextView volunteer;
	
	private Button all_contacts;
	private Button search_contacts;
	private Button done_save;
	private EventModel event1;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setTitle("Event Detail");
	setContentView(R.layout.event_detail); 
	Layout();
}

protected void onStart(){
	super.onStart();		
	Intent intent = this.getIntent();
	Bundle bundle = intent.getExtras();
	event1 = (EventModel)bundle.getSerializable("eventinfo"); 
	senderName = (TextView)findViewById(R.id.sender_name);
	senderName.setText(event1.getPhone());
	sendTime = (TextView)findViewById(R.id.send_time);
	sendTime.setText(event1.getTime());
	address_tag = (TextView)findViewById(R.id.address_tag);
	address_tag.setText(event1.getAddress_tag());
	body = (TextView)findViewById(R.id.body);
	body.setText(event1.getDescription());
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
	
	//Pass the EventModel to search contact activity
	search_contacts = (Button) findViewById(R.id.search_contacts);
	search_contacts.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 Intent in = new Intent(EventDetail.this,AvailableEfarListActivity.class);
				 Bundle bundle2 = new Bundle(); 
				 bundle2.putSerializable("eventinfo",event1);
				 in.putExtras(bundle2);
				 startActivityForResult(in,REQUEST_CODE); 
			}
		});
		
	done_save = (Button) findViewById(R.id.done_indetail);
	done_save.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dbhelper.addRecord(event1);			
				Intent intent = new Intent();  
				int a [] = {event1.getIndex(),event1.getPosition()};
				intent.putExtra("position", a);
				//intent.putExtra("position", event1.getIndex());
		        //Toast.makeText(getApplicationContext(), String.valueOf(event1.getPosition()), Toast.LENGTH_SHORT).show();
            	setResult(RESULT_CODE , intent);			
				finish();
			}
		});	
	volunteer = (TextView) findViewById(R.id.volunteer);
}

protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    
    super.onActivityResult(requestCode, resultCode, data);      
    if(requestCode == REQUEST_CODE){  
        if(resultCode == AvailableEfarListActivity.RESULT_CODE){               
            String send_out_list = data.getStringExtra("send_out");
            Toast.makeText(getApplicationContext(), send_out_list,
   			     Toast.LENGTH_SHORT).show();
            sm_for_event.append(send_out_list);
            volunteer.setText(sm_for_event.toString()); 
            event1.setSend_list(sm_for_event.toString());
        } 
    }
}


}



