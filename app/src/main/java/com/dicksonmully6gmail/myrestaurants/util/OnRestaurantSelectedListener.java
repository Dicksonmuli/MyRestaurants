package com.dicksonmully6gmail.myrestaurants.util;

import com.dicksonmully6gmail.myrestaurants.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by dickson on 6/14/17.
 */

public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants);
}
