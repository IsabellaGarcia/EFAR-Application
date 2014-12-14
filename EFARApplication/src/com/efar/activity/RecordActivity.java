/**
 * 
 */
package com.efar.activity;

import com.efar.application.EfarApplication;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Michyo
 *
 */
public class RecordActivity extends Activity {
	private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_record;
	private ImageButton test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record); 
		
		
		TextView title = (TextView) findViewById(R.id.record_title);
		TextView efars = (TextView) findViewById(R.id.record_efars);
		TextView detail = (TextView) findViewById(R.id.record_detail);

		// Get global variables from Application.
	    EfarApplication global_variables = (EfarApplication) this.getApplication();
	    RecordModel selected_record = global_variables.getSelectedRecord();
	    title.setText(selected_record.getEventName());
	    efars.setText(selected_record.getRelatedEfars());
	    detail.setText(selected_record.getEventDetail());
		
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

}
