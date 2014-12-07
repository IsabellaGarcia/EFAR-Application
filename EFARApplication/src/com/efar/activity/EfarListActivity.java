/**
 * 
 */
package com.efar.activity;

import java.util.Vector;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.efar.adapter.EfarAdapter;
import com.efar.database.DatabaseHelper;
import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;
import com.efar.global.GlobalVariables;

/**
 * @author Michyo
 * Show a list of available Efars to be selected.
 */

public class EfarListActivity extends ListActivity {
	private Vector<EfarModel> efars = null;  
	private TextView selected_efar_info;  
    private EfarModel selected_efar = new EfarModel();
    
	@Override  
    protected void onCreate(Bundle icicle) {  
		/*
		 * Initialize whole ListActivity.
		 */
        super.onCreate(icicle);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.activity_efar_list); 
        
        /*
         * Get the title on the top of screen.
         */
        selected_efar_info = (TextView) findViewById(R.id.mTitle); 
        
        /*
         * Get global variables from Application.
         */
        GlobalVariables global_variables = (GlobalVariables) this.getApplication();
        EventModel event_now = global_variables.getEventNow();
        String address_tag = event_now.getAddress_tag();
        
		/*
		 *  Database processing.
		 */
		DatabaseHelper dbhelper = new DatabaseHelper(this);
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
            	
            	/**
            	 * Please add 
            	 * 		- Call efars.
            	 * 		- Text efars.
            	 * functions here.
            	 */
            	
                /*Intent intent = new Intent(EfarListActivity.this, SelectFriendActivity.class);   
                //intent.putExtra("chosedUser", chosedUser);
                //setResult(1, intent);*/  
            	EfarListActivity.this.finish();
            }  
        });  
        Button buttonCancle = (Button) findViewById(R.id.buttonCancelEfar);  
        buttonCancle.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) { 
            	/*Intent intent = new Intent(MyFriendManager.this, SelectFriendActivity.class); 
            	intent.putExtra("chosedUser", chosedUser);
                setResult(2, intent);  */ 
            	EfarListActivity.this.finish();
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
    	selected_efar_info.setText(selected_efar.getName() + " @" + selected_efar.getAddress_tag());
    	CheckBox select_this_efar = (CheckBox) v.findViewById(R.id.select_this_efar);
    	if (select_this_efar.isChecked()) {
    		select_this_efar.setChecked(false);
        } else {
        	select_this_efar.setChecked(true);
        }
    }
	

}
