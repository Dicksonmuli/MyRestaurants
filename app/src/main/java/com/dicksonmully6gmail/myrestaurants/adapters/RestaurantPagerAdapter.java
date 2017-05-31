package com.dicksonmully6gmail.myrestaurants.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ArrayAdapter;

import com.dicksonmully6gmail.myrestaurants.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by dickson on 5/31/17.
 */

public class RestaurantPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Restaurant> mRestaurants;

    public RestaurantPagerAdapter(FragmentManager fm, ArrayList<Restaurant> restaurants) {
        super(fm);
        mRestaurants
    }


}
