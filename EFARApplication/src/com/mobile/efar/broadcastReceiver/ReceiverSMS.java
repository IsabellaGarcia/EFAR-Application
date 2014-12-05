/**
 * Created by Xinyi HUANG
 * Created Date: 27/11/2014
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

package com.mobile.efar.broadcastReceiver;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.mobile.efar.activity.EventActivity;

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
			StringBuilder sb = new StringBuilder();
			ArrayList<String> list =new ArrayList<String>(); 
			//get information from intent
			Bundle bundle = intent.getExtras();
			String sender1;
			String body1;
			String sendtime;
			
			if(bundle!=null){
				//Get all the context of SMS by pdus
				Object[] pdus = (Object[])bundle.get("pdus");  
				//Store in msg
				SmsMessage[] msg = new SmsMessage[pdus.length]; 
				
				for(int i = 0 ;i<pdus.length;i++){ 
					//SMS Object, might be multiple SMS
					 msg[i] = SmsMessage.createFromPdu((byte[])pdus[i]);				 
					 /*
					  * Get sender, text body, and sent time
					  * append into sb, like 
					  * Garcia#2014-11-05 12:17:00#@Central #Event: There is...... &
					  */
					 sender1 = msg[i].getOriginatingAddress();
					 sb.append(sender1);
					 sb.append("#");
					 Date date = new Date(msg[i].getTimestampMillis());
					 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 sendtime = format.format(date);
					 sb.append(sendtime);
					 sb.append("#");
					 body1 = msg[i].getDisplayMessageBody();
					 sb.append(body1);
					 sb.append("&");
					 list.add(sb.toString());
					//FOR RECORD
					//Log.i(ReceiverSMS.LOG_TAG, "[EFAR] onReceiveIntent0: "+ sb); 
					 abortBroadcast();
					 
					 //Emergency SMS starts with '@' 
					 if(body1.startsWith(queryString)){
						 Toast.makeText(context, "Sender£º"+sender1 + "\r\n#Content:"+ body1 + sendtime, Toast.LENGTH_LONG).show();
						 //Activate EventActivity
						 Intent in = new Intent(context,EventActivity.class);
						 Bundle bundle2 = new Bundle();
						 bundle2.putStringArrayList("message", list);
						 in.putExtras(bundle2);
						 //destroy other activities
						 in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 context.startActivity(in);
						//FOR RECORD
						//Log.i(ReceiverSMS.LOG_TAG, "[EFAR] onReceiveIntent0 Over: "); 	
					 }
				}//Process for one message
			}//for one bundle
		}
	}
}
