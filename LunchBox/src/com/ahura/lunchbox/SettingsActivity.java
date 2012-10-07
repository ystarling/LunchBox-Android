package com.ahura.lunchbox;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_settings); }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.activity_settings, menu); return true; }
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		// Get the custom preference
		//PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		Preference customPref = (Preference) findPreference("settings");
		customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					public boolean onPreferenceClick(Preference preference) {
						Toast.makeText(getBaseContext(),
								"The custom preference has been clicked",
								Toast.LENGTH_LONG).show();
						SharedPreferences customSharedPreference = getSharedPreferences(
								"myCustomSharedPrefs", Activity.MODE_PRIVATE);
						SharedPreferences.Editor editor = customSharedPreference
								.edit();
						editor.putString("myCustomPref",
								"The preference has been clicked");
						editor.commit();
						return true;
					}

				});
	}
}
