/**
 * 
 */
package com.efar.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.efar.adapter.RecordAdapter;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**CSIT 6000B
 * @author Michyo
 * Implement by Xinyi HUANG
 *
 */
public class RecordListActivity extends ListActivity {
	private List<RecordModel> records = new ArrayList<RecordModel>(); 
	private TextView title;  
    private RecordModel selected_record = new RecordModel();
    private ImageButton imagebutton_contact;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_setting;
    
	@Override  
    protected void onCreate(Bundle icicle) {  
		
		// Initialize whole ListActivity.
        super.onCreate(icicle);  
        setContentView(R.layout.z); 
        layout();
    }  
    
	/**
	 * Set action when one of the item in list be clicked.
	 */
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) { 
    	selected_record = records.get(position);
    	title.setText(selected_record.getSend_list());
    	Toast.makeText(getApplicationContext(),selected_record.getSend_list(), Toast.LENGTH_SHORT).show();
    	
    }
    
    private void layout(){
        
        // Get the title on the top of screen.
        title = (TextView) findViewById(R.id.mTitle); 
        
		// Database processing.
        EmbededDatabase dbhelper = new EmbededDatabase();
		records = dbhelper.getAllRecords();
		
        
		imagebutton_event = (ImageButton) findViewById(R.id.button_event);
		imagebutton_event.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RecordListActivity.this, EventActivity.class);
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
				intent.setClass(RecordListActivity.this, ContactListActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//Click to switch to the AddEfarActivity
		imagebutton_setting = (ImageButton) findViewById(R.id.button_setting);
		imagebutton_setting.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecordListActivity.this, AddEfarActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// Inflate list with adapter.
        setListAdapter(new RecordAdapter(this, records)); 
    }
}
