package com.dicksonmully6gmail.myrestaurants;

/**
 * Created by dickson on 5/26/17.
 */

public class Constants {
        public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
        public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
        public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
        public static final String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search?term=food";
        public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
//        a constant to save to shared preferences
        public static final String PREFERENCES_LOCATION_KEY = "location";

        public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
        public static final String FIREBASE_CHILD_RESTAURANTS = "restaurants";
//        index string constant to reference the 'index' key of Restaurant objects
        public static final String FIREBASE_QUERY_INDEX = "index";

        public static final String EXTRA_KEY_POSITION = "position";
        public static final String EXTRA_KEY_RESTAURANTS = "restaurants";
        // navigation constants
        public static final String KEY_SOURCE = "source";
        public static final String SOURCE_SAVED ="saved";
        public static final String SOURCE_FIND = "find";
}
