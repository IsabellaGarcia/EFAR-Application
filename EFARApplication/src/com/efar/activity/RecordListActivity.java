/**
 * 
 */
package com.efar.activity;

import java.util.Vector;

import com.efar.adapter.RecordAdapter;
import com.efar.application.EfarApplication;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Michyo
 *
 */
public class RecordListActivity extends ListActivity {
	private Vector<RecordModel> records = null;  
	private TextView title;  
    private RecordModel selected_record = new RecordModel();
    
	@Override  
    protected void onCreate(Bundle icicle) {  
		
		// Initialize whole ListActivity.
        super.onCreate(icicle);  
        setContentView(R.layout.activity_record_list); 
        
        // Get the title on the top of screen.
        title = (TextView) findViewById(R.id.mTitle); 
        
		// Database processing.
        EmbededDatabase dbhelper = new EmbededDatabase();
		records = dbhelper.getAllRecords();
		
		final EfarApplication global_variables = (EfarApplication) this.getApplication();
		
		/*
		 * Two buttons at the bottom.
		 * 	- confirm to notice efars.
		 * 	- cancel this action.
		 */
        Button buttonConfirm = (Button) findViewById(R.id.button_select_record);         
        buttonConfirm.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  

            	// Pass global variables to Application.
                global_variables.setSelectedRecord(selected_record);
                Intent intent = new Intent(RecordListActivity.this, RecordActivity.class);
                startActivity(intent);
            	RecordListActivity.this.finish();
            }  
        });  
        Button buttonCancle = (Button) findViewById(R.id.button_cancel_record);  
        buttonCancle.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) { 
            	RecordListActivity.this.finish();
            }  
        });  

		
		// Inflate list with adapter.
        setListAdapter(new RecordAdapter(this, records)); 
    }  
    
	/**
	 * Set action when one of the item in list be clicked.
	 */
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) { 
    	selected_record = records.get(position);
    	title.setText(selected_record.getEventName());
    }

}
