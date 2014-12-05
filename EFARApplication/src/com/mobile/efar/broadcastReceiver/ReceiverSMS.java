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
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//If gets the intent of receiving SMS for EFAR
		if(strRes.equals(intent.getAction())){
			StringBuilder b = new StringBuilder();
			 Intent event1 = new Intent(context, EventActivity.class);  
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
					//SMS Object
					 msg[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
					 
					 
					 /*
					  * Get sender, text body, and sent time
					  */
					 sender1 = msg[i].getOriginatingAddress();
					 body1 = msg[i].getDisplayMessageBody();
					 Date date = new Date(msg[i].getTimestampMillis());
					 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 sendtime = format.format(date);
					 
					 
					 //Emergency SMS starts with '@' 
					 if(body1.startsWith(queryString)){
						 Toast.makeText(context, "Sender£º"+sender1 + "\r\n#Content:"+ body1, Toast.LENGTH_LONG).show();
						 //Activate EventActivity
					 }
				}
				
			/*	for(SmsMessage curMsg:msg){
					b.append("You got the message From:¡¾");
					//Get Sender
					b.append(curMsg.getDisplayOriginatingAddress());
					//Get content
					b.append("¡¿Content£º");
					b.append(curMsg.getDisplayMessageBody());
				}*/
				//start EventActivity
				//context.startActivity(event1);
			}
		}
	}

}
