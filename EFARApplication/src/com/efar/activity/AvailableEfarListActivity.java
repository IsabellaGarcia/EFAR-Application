package com.efar.activity;

import java.util.Vector;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.efar.adapter.EfarAdapter;
import com.efar.application.EfarApplication;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

public class AvailableEfarListActivity extends ListActivity {
	private Vector<EfarModel> efars = null;  
	private TextView selected_efar_info;  
    private EfarModel selected_efar = new EfarModel();
    private EventModel event_now = new EventModel();
    private EmbededDatabase dbhelper = new EmbededDatabase();
    
	@Override  
    protected void onCreate(Bundle icicle) {  
		
		// Initialize whole ListActivity.
        super.onCreate(icicle);  
        setContentView(R.layout.activity_efar_list); 
        
        // Get the title on the top of screen.
        selected_efar_info = (TextView) findViewById(R.id.mTitle); 
        
        // Get global variables from Application.
        EfarApplication global_variables = (EfarApplication) this.getApplication();
        event_now = global_variables.getEventNow();
        String address_tag = event_now.getAddress_tag();
        
		// Database processing.
		efars = dbhelper.getEfarByAddress(address_tag);
		Toast.makeText(getApplicationContext(), Integer.toString(efars.size()),
			     Toast.LENGTH_SHORT).show();
        
		/*
		 * Two buttons at the bottom.
		 * 	- confirm to notice efars.
		 * 	- cancel this action.
		 */
        Button buttonConfirm = (Button) findViewById(R.id.buttonSelectEfar);         
        buttonConfirm.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) { 
            	// Create efars for calling or further actions.
            	Vector<EfarModel> efars = convert(event_now.getRelatedEfarsVector());
            	/**
            	 * Please add 
            	 * 		- Call efars.
            	 * 		- Text efars.
            	 * functions here.
            	 */ 
            	AvailableEfarListActivity.this.finish();
            }  
        });  
        Button buttonCancle = (Button) findViewById(R.id.buttonCancelEfar);  
        buttonCancle.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {
            	event_now.setRelatedEfarsVector(new Vector<String>());
            	AvailableEfarListActivity.this.finish();
            }  
        });  

        setListAdapter(new EfarAdapter(this, efars)); 
    }  
    
	/**
	 * Set action when one of the item in list be clicked.
	 */
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) { 
    	selected_efar = efars.get(position);
    	selected_efar_info.setText(selected_efar.getName() + " @" + selected_efar.getAddressTag());
    	CheckBox select_this_efar = (CheckBox) v.findViewById(R.id.select_this_efar);
    	if (select_this_efar.isChecked()) {
    		select_this_efar.setChecked(false);
    		event_now.deleteEfar(selected_efar);
        } else {
        	select_this_efar.setChecked(true);
        	event_now.addEfar(selected_efar);
        }
    }
	
    protected Vector<EfarModel> convert(Vector<String> names) {
    	Vector<EfarModel> efars = new Vector<EfarModel>();
    	for(int i = 0; i < names.size(); i++) {
    		EfarModel efar = dbhelper.getEfarByName(names.get(i));
    		efars.add(efar);
    	}
    	return efars;
    }

}
