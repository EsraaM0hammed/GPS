package com.example.gps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnShowLocation;
	GpsTracker tracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnShowLocation = (Button) findViewById(R.id.showlocationBtn);

	}

	public void Show(View view) {
		tracker = new GpsTracker(MainActivity.this);
		if (tracker.canGetLocation) {
			double latidude = tracker.longtide;
			double longitude = tracker.longtide;
			Toast.makeText(
					getBaseContext(),
					"Your Location  : "+"\n" + longitude + " Longitude" +"\n"+ latidude
							+ " Latidude", Toast.LENGTH_SHORT).show();
		}
		else {
			tracker.showSettingAlert();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
