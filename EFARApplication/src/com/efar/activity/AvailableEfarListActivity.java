
/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719    
 * Email: xhuangap@connect.ust.hk
 */
package com.efar.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.efar.adapter.EfarAdapter;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

public class AvailableEfarListActivity extends Activity {
	 
	private List<EfarModel> efars_list = new ArrayList<EfarModel>();
	private List<EfarModel> select_list = new ArrayList<EfarModel>();
    private EfarModel selected_efar = new EfarModel();
    public final static int RESULT_CODE = 1;
    private ListView lv_list;
    private EfarAdapter mAdapter;
    private EmbededDatabase dbhelper = new EmbededDatabase();
    private EventModel event1;   
    private int selectTimes[];
    private int position_1[];
    private TextView Title;
	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
		
		// Initialize whole ListActivity.
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_efar_list); 
        
        //get the eventModel from Event Detail Activity
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        
        //EventModel
        event1 = (EventModel)bundle.getSerializable("eventinfo");
        
		// Database processing.
        String temp[] = event1.getAddress_tag().split("@");
        
		efars_list = dbhelper.getEfarByAddress(temp[1],event1.getWeekday());
		selectTimes = new int[efars_list.size()];
        position_1 = new int[efars_list.size()];
		for(int i = 0; i <efars_list.size()-1; i++){
			selectTimes[i] = 0;
			position_1[i] = 0;
		}
        
		layout();
    }  
    
	
    private void layout(){
		/*
		 * Two buttons at the bottom.
		 * 	- confirm to notice efars.
		 * 	- cancel this action.
		 */

        Button buttonConfirm = (Button) findViewById(R.id.buttonSelectEfar);    
        //send out SMS
        buttonConfirm.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {
            	StringBuilder sm = new StringBuilder();
            	sm.append("NEED YOUR HELP!");
            	sm.append("\n");
            	sm.append("Place: ");
            	sm.append(event1.getAddress_tag());
            	sm.append("\n");
            	sm.append("Description: ");
            	sm.append(event1.getDescription());
            	
	            	// Create efars for calling or further actions.
	            for(int i = 0; i<select_list.size(); i++){
	            String content =sm.toString();//短信内容
	           
	            String phone_1 =select_list.get(i).getPhone();//电话号码
	
	            SmsManager smsManager =SmsManager.getDefault();
	            List<String> texts =smsManager.divideMessage(content);
	
	            for(int j = 0;j<texts.size();j++){
	                   String text =texts.get(j);
	                   smsManager.sendTextMessage(phone_1,null, content, null, null);
	            }  
            }
	            Intent intent = new Intent();  
            	intent.putExtra("send_out", convert());
            	setResult(RESULT_CODE , intent);
            	finish();
            }  
        });  
        
        Button buttonCancle = (Button) findViewById(R.id.buttonCancelEfar);  
        buttonCancle.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) { 
            	finish();
            }  
        }); 
        
        Title = (TextView)findViewById(R.id.mTitle);
        
    	lv_list = (ListView)findViewById(R.id.list_v);
    	mAdapter = new EfarAdapter(this, efars_list);
		lv_list.setAdapter(mAdapter);
		lv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				StringBuilder sm = new StringBuilder();
				sm.append("");
			 	selected_efar = efars_list.get(position);
			 	//The item is be selected
			 	if(selectTimes[position]%2 == 0){
			 	select_list.add(selected_efar);
			 	selectTimes[position]++;
			 	position_1[position] = select_list.size()-1;
			 	sm.append(selected_efar.getPhone());
			 	sm.append(" is added into the list");
	    		Toast.makeText(getApplicationContext(), sm.toString(),
	   			     Toast.LENGTH_SHORT).show();
			 	}
			 	//The item has been removed from the list
			 	else{
			 		sm.append(selected_efar.getPhone());
				 	sm.append(" is removed from the list");
				 	selectTimes[position]++;
		    		Toast.makeText(getApplicationContext(), sm.toString(),
		   			     Toast.LENGTH_SHORT).show();
			 		select_list.remove(position_1[position]);
			 		
			 		
			 	}
			 	Title.setText(convert());
			 
			}
		});
    }

    // Select volunteers for returning to the EventDetail Activity
    private String convert(){
    	StringBuilder sm = new StringBuilder();
    	sm.append("Select: ");
    	for(int i = 0; i<select_list.size(); i++){
    		sm.append(select_list.get(i).getName());
    		sm.append(",");
    	}
    	return sm.toString();
    }
}
