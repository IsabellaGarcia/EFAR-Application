/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 * Description: Get useful SMS for Emergency
 * 				SMS should start by '@'
 * 				Activate EventActivity
 */

/**
 * Changed by Xinyi HUANG
 * Created Date: 05/12/2014
 * Description: Get sendtime from user
 * 				Format sendtime		
 */

package com.efar.broadcastReceiver;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.efar.activity.EventActivity;
import com.efar.activity.EventDetail;
import com.efar.datamodel.EventModel;

public class ReceiverSMS extends BroadcastReceiver{

	//Define intent for SMS receive
	private static final String strRes = "android.provider.Telephony.SMS_RECEIVED";
	//Define specific starting-character for SMS to filter useless SMS
	private static final String queryString = "@";
	private static final String LOG_TAG ="SMSReceiver"; 
	@Override
	public void onReceive(Context context, Intent intent) {
		//If gets the intent of receiving SMS for EFAR

		if(strRes.equals(intent.getAction())){
			Bundle bundle = intent.getExtras();
			if(bundle!=null){
				Object[] pdus = (Object[])bundle.get("pdus");
				SmsMessage[] msg = new SmsMessage[pdus.length];
				for(int i = 0 ;i<pdus.length;i++){ 
					msg[i] = SmsMessage.createFromPdu((byte[])pdus[i]);	
					String body1 = msg[i].getDisplayMessageBody();
					if(body1.startsWith(queryString)){
						//Switch to Event List
						Toast.makeText(context, "A New Emergency Event", Toast.LENGTH_SHORT).show();
						Intent in = new Intent(context,EventActivity.class);
						in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(in);
					}
				}
			}
			
		}
			
	}//end of OnReceive
}// end of class
