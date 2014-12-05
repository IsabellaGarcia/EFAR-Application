/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Activity for Welcoming
*/ 

package com.mobile.efar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.example.efar.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        // Delay 2 seconds to run() and jump to another page.
        new Handler().postDelayed(new Runnable() {
        	
        	public void run() {
        		Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        		startActivity(intent);
        		WelcomeActivity.this.finish();
        	}
        }, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
