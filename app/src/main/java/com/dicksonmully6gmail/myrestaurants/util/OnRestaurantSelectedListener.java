package com.dicksonmully6gmail.myrestaurants.util;

import com.dicksonmully6gmail.myrestaurants.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by dickson on 6/14/17.
 */

public interface OnRestaurantSelectedListener {
    /**
     *
     * @param position
     * @param restaurants
     * @param source -source will be the String name of the activity the user views our reusable fragment,
     *               from; Either "RestaurantListActivity" or "SavedRestaurantListActivity"
     */
    public void onRestaurantSelected(Integer position,
                                     ArrayList<Restaurant> restaurants, String source);

}
