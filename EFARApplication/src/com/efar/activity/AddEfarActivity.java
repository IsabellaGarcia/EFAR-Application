package com.efar.activity;

import com.efar.database.EmbededDatabase;
import com.efar.datamodel.EfarModel;
import com.example.efar.R;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719    
 * Email: xhuangap@connect.ust.hk
 */
public class AddEfarActivity extends Activity {
	//value for spinner
	private static final String[] m={"Sunday","Monday","Thuesday","Wednesday","Thursday","Friday","Saturday"};
	private ImageButton imagebutton_record;
	private ImageButton imagebutton_event;
	private ImageButton imagebutton_contact;
    private Spinner spinner;
    private TextView view ;
    private ArrayAdapter<String> adapter;
    private EfarModel volunteer = new EfarModel();
    private Button confirm;
    private Button cancel;
    private EditText name, phone, address_tag;
    private int free_day;
    private EmbededDatabase dbhelper = new EmbededDatabase();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);  
	        setContentView(R.layout.add_volunteers); 
	        layout();
	}
	
	private void layout(){
		//Setting for Spinner
		view = (TextView) findViewById(R.id.spinnerText);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);    
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);      
        spinner.setAdapter(adapter);   
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setVisibility(View.VISIBLE);
        
        //Setting for input blanks
        name = (EditText)findViewById(R.id.name_input);
        phone = (EditText)findViewById(R.id.phone_input);
        address_tag = (EditText)findViewById(R.id.address_input);
        
        //Setting for buttons
        confirm = (Button) findViewById(R.id.add_volunteer);
        confirm.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//Save the inputs from user
				boolean empty = false;
				StringBuilder sm = new StringBuilder();
				sm.append("Cannot save: ");
				if("".equals(name.getText().toString().trim())){
					sm.append("NAME ");
					empty = true;
				}
				if("".equals(phone.getText().toString().trim())){
					sm.append("PHONE ");
					empty = true;
				}
				if("".equals(address_tag.getText().toString().trim())){
					sm.append("ADDRESS_TAG ");
					empty = true;
				}
				if(!empty){
					volunteer.setName(name.getText().toString());
					volunteer.setPhone(phone.getText().toString());
					volunteer.setAddressTag(address_tag.getText().toString());
					//Toast.makeText(getApplicationContext(), String.valueOf(free_day), Toast.LENGTH_SHORT).show();
					volunteer.setTimeAvailable(String.valueOf(free_day));
					//save into database
					dbhelper.addEfar(volunteer);
					Toast.makeText(getApplicationContext(), "Succesfully saved", Toast.LENGTH_SHORT).show();
				}
				else{
					sm.append("is/are empty");
					Toast.makeText(getApplicationContext(), sm.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
        cancel = (Button) findViewById(R.id.cancel_volunteer);
        cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});     
        
		imagebutton_contact = (ImageButton) findViewById(R.id.button_contact);
		imagebutton_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddEfarActivity.this, ContactListActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//Click event to open Event list
		imagebutton_event = (ImageButton) findViewById(R.id.button_event);
		imagebutton_event.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddEfarActivity.this, EventActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//Click event to open Record list
		imagebutton_record = (ImageButton) findViewById(R.id.button_record);
		imagebutton_record.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddEfarActivity.this, RecordListActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	//Select the available day for the new volunteer
	  class SpinnerSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			((TextView) view).setText(m[position]);
			free_day = position + 1;
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	  }
  
	  
}
