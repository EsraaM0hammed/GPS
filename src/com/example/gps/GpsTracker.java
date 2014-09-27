package com.example.gps;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GpsTracker extends Service implements LocationListener {

	private Context mcontext;
	boolean isGpsEnabled = false;
	boolean isNetworkenabled = false;
	boolean canGetLocation = false;
	Location location;
	double latidude;
	double longtide;
	private static final long MIN_DISTANCE_CHANGE_FOR = 10;
	private static final long MIN_TIME_BW_UPDATE = 1000 * 60 * 1;
	protected LocationManager locationManager;

	public GpsTracker(Context context) {
		this.mcontext = context;
		getlocation();

	}

	private Location getlocation() {
		locationManager = (LocationManager) mcontext
				.getSystemService(LOCATION_SERVICE);
		isGpsEnabled = locationManager
				.isProviderEnabled(locationManager.GPS_PROVIDER);
		isNetworkenabled = locationManager
				.isProviderEnabled(locationManager.NETWORK_PROVIDER);
		if (!isGpsEnabled && !isNetworkenabled) {

		} else {
			this.canGetLocation = true;
			if (isNetworkenabled) {
				locationManager.requestLocationUpdates(
						locationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATE,
						MIN_DISTANCE_CHANGE_FOR, this);
				Log.d("Network", "Network");
				if (locationManager != null) {
					location = locationManager
							.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
					if (location != null) {
						latidude = location.getAltitude();
						longtide = location.getLongitude();

					}

				}

			}
		}
		return location;

	}

	public void stopUsingGBS() {
		if (locationManager != null) {
			locationManager.removeUpdates(GpsTracker.this);

		}

	}

	public double getLatidude() {
		if (location != null) {
			latidude = location.getLatitude();

		}
		return latidude;

	}

	public double getLongtide() {
		if (location != null)
			longtide = location.getLongitude();
		return longtide;

	}

	public boolean canGetLocation() {
		return this.canGetLocation;

	}

	public void showSettingAlert() {
		AlertDialog.Builder alertdialog = new AlertDialog.Builder(mcontext);
		alertdialog.setTitle("GPS Settings");
		alertdialog
				.setMessage("GPS Is Not Enabled . Do You want To GO TO Setting Menu ??");
		alertdialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						mcontext.startActivity(intent);

					}
				});
		alertdialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				});
		alertdialog.show();
	}

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
