/**
 * 
 */
package com.efar.activity;

import java.util.Vector;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.efar.adapter.SimpleEfarAdapter;
import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.example.efar.R;

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
		
		// Initialize whole ListActivity.
        super.onCreate(icicle);  
        setContentView(R.layout.activity_efar_list); 
        
        // Get the title on the top of screen.
        selected_efar_info = (TextView) findViewById(R.id.mTitle); 
        
        /* Get global variables from Application.
        EfarApplication global_variables = (EfarApplication) this.getApplication();
        EventModel event_now = global_variables.getEventNow();
        String address_tag = event_now.getAddress_tag();*/
        
		// Database processing.
        EmbededDatabase dbhelper = new EmbededDatabase();
		// efars = dbhelper.getEfarByAddress(address_tag);
        efars = dbhelper.getAllEfar();
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
                Intent intent = new Intent(EfarListActivity.this, AddEfarActivity.class);  
                startActivity(intent);
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

        setListAdapter(new SimpleEfarAdapter(this, efars)); 
    }  
    
	/**
	 * Set action when one of the item in list be clicked.
	 */
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) { 
    	selected_efar = efars.get(position);
    	selected_efar_info.setText(selected_efar.getName() + " @" + selected_efar.getAddressTag());
    }
}
