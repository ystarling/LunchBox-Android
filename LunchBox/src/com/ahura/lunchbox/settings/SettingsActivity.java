package com.ahura.lunchbox.settings;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ahura.lunchbox.R;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;


public class SettingsActivity extends PreferenceActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.setting_headers, target);
    }

    /**
     * This fragment shows the preferences for the radius header.
     */
    public static class ResultRadiusFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_distance);
            
           ListPreference listPreference = (ListPreference) findPreference("walking_distance");
           listPreference.setSummary(listPreference.getEntry());
           
           listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					ListPreference listPreference = (ListPreference) preference;
					int index = listPreference.findIndexOfValue(newValue.toString());
					preference.setSummary(listPreference.getEntries()[index]);
					return true;
				}
			});
        }
    }
    
    /**
     * This fragment shows the preferences for the food selection header.
     */
    public static class FoodSelectionFragment extends PreferenceFragment {
    	
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_food);
            fixMultiListSummary("food_restrictions", R.string.no_food_restriction);
            fixMultiListSummary("exclude_food", R.string.no_food_exclusion);
        }
        
        private Set<CharSequence> getSelectedEntries(
        		Set<String> values, MultiSelectListPreference multilistPreference) {
        	Set<CharSequence> labels = new HashSet<CharSequence>();
        	for(String value: values) {
        		int index = multilistPreference.findIndexOfValue(value);
        		labels.add(multilistPreference.getEntries()[index]);
        	}
        	multilistPreference.setSummary(labels.toString());
	        return labels;
        }
        
        private void fixMultiListSummary(final String name, final int emptyStringId){
        	MultiSelectListPreference multilistPreference = (MultiSelectListPreference) findPreference(name);
        	Set<String> values = multilistPreference.getValues();
            if (values.size() > 0) {
            	multilistPreference.setSummary(
            			getSelectedEntries(multilistPreference.getValues(), multilistPreference)
            			.toString());
            }
            
            multilistPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				public boolean onPreferenceChange(Preference preference, Object newValue) {
 					MultiSelectListPreference multilistPreference = (MultiSelectListPreference) preference;
 					@SuppressWarnings("unchecked")
					Set<String> values = (Set<String>) newValue;
 					
 			        if (!values.isEmpty()) {
 			        	multilistPreference.setSummary(
 		            			getSelectedEntries(multilistPreference.getValues(), multilistPreference)
 		            			.toString());
 			        }
 			        else{
 			        	multilistPreference.setSummary(emptyStringId);
 			        }
 					return true;
 				}
 			});
        }
    }

}
