/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Activity for Welcoming
*/ 

package com.efar.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.example.efar.R;
import static com.efar.database.DatabaseConstants.*;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        // Initialize database from assets/main.sqlite
        if ((new File(DATABASE_PATH + DATABASE_NAME)).exists() == false) {
			// if sqlite file does not exist, check for folder.
			File f = new File(DATABASE_PATH);
			// if folder does not exist, new folder.
			if (!f.exists()) {
				f.mkdir();
			}

			try {
				// get database from /assets/<db name>
				InputStream is = getBaseContext().getAssets().open(DATABASE_NAME);
				OutputStream os = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

				// flush into the database we want it to be.
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}

				// close connections.
				os.flush();
				os.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + DATABASE_NAME, null);
        
        // Delay 2 seconds to run() and jump to another page.
        new Handler().postDelayed(new Runnable() {
        	
        	public void run() {
        		// Intent intent = new Intent(WelcomeActivity.this, EfarListActivity.class); // Test for database.
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
