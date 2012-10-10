package com.ahura.lunchbox.restaurants;

import com.ahura.lunchbox.R;
import com.ahura.lunchbox.R.layout;
import com.ahura.lunchbox.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RestaurantDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_restaurant_detail, menu);
        return true;
    }
}
