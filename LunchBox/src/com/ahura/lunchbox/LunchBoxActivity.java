package com.ahura.lunchbox;

import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import com.ahura.lunchbox.map.MyLocationOverlay;
import com.ahura.lunchbox.map.RadiusOverlay;
import com.ahura.lunchbox.settings.SettingsActivity;

public class LunchBoxActivity extends MapActivity implements LocationListener {

	private MapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private MyLocationOverlay myLocationOverlay;
	private RadiusOverlay radiusOverlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lunch_box);

		String svcName = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(svcName);
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapController = mapView.getController();
		mapView.setBuiltInZoomControls(true);
		
		myLocationOverlay = new MyLocationOverlay();
		radiusOverlay = new RadiusOverlay(1000);
		List<Overlay> myLocationOverlays = mapView.getOverlays();
		myLocationOverlays.add(myLocationOverlay);
		myLocationOverlays.add(radiusOverlay);
		mapView.postInvalidate();

		mapController.setZoom(17);
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 2000, 10, this);
		updateWithNewLocation(
				locationManager.getLastKnownLocation(
						LocationManager.GPS_PROVIDER));
	}

	private void updateWithNewLocation(Location location) {
		if (location == null) {
		   return;
		}
		GeoPoint geopoint = new GeoPoint(
				(int) (location.getLatitude() * 1E6), (int) (location
						.getLongitude() * 1E6));
		myLocationOverlay.setGeopoint(geopoint);
		radiusOverlay.setGeopoint(geopoint);
		radiusOverlay.setRadiusMeters(100);
		mapController.animateTo(geopoint);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_lunch_box, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// return true is the view is displaying driving directions
		return false;
	}

	public void onLocationChanged(Location location) {
		updateWithNewLocation(location);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		locationManager.requestLocationUpdates(provider, 2000, 10, this);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
