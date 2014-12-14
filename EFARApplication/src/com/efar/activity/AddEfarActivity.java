package com.efar.activity;

import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.example.efar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Michyo
 * Add Efar by filling in blanks.
 */
public class AddEfarActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_efar); 
	 	
    	Button button_add = (Button) findViewById(R.id.button_add_efar);         
    	button_add.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) { 
        		EditText efar_name = (EditText) findViewById(R.id.edittext_efar_name);
        		EditText efar_phone = (EditText) findViewById(R.id.edittext_efar_phone);
        		EditText efar_address = (EditText) findViewById(R.id.edittext_address_tag);
        		EditText efar_time = (EditText) findViewById(R.id.edittext_time_available);
        		EditText efar_skill = (EditText) findViewById(R.id.edittext_skill_tag);
            	
            	Log.d("test", "cansal button ---> click");
                String name = efar_name.getText().toString().trim();
                String phone = efar_phone.getText().toString().trim();
                String address = efar_address.getText().toString().trim();
                String time = efar_time.getText().toString().trim();
                String skill = efar_skill.getText().toString().trim();
                if(!name.equals("") && !phone.equals("")){
                	EfarModel efar = new EfarModel(name, phone, address, time, skill);
                	
                	// Database operations.
                	EmbededDatabase dbhelper = new EmbededDatabase();
                	dbhelper.addEfar(efar);
            		Toast.makeText(getApplicationContext(), efar.getName(),
            			     Toast.LENGTH_SHORT).show();
            		Intent intent = new Intent(AddEfarActivity.this, EfarListActivity.class);  
                    startActivity(intent);
                	AddEfarActivity.this.finish();
                }
            }
        });
	}
}
