package com.dicksonmully6gmail.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

//import com.dicksonmully6gmail.myrestaurants.adapters.RestaurantListAdapter;
//import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.dicksonmully6gmail.myrestaurants.Constants;
import com.dicksonmully6gmail.myrestaurants.R;
import com.dicksonmully6gmail.myrestaurants.adapters.RestaurantListAdapter;
import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.dicksonmully6gmail.myrestaurants.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantListActivity extends AppCompatActivity {
//    without butterknife
//    private TextView mLocationTextView;
//    private ListView mListView;

//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;

    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    public static final String TAG = RestaurantListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;
//
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
//
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the restaurants near: " + location);

        getRestaurants(location);

//        testing shared preferences
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, location);
            if (mRecentAddress != null) {
                getRestaurants(mRecentAddress);
            };
    }
    //callback method for req and res
    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            //overriding onResponse and save data in logcat(for now)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                calling runOnUiThread() method and override its run()
                try {
                    mRestaurants = yelpService.processResults(response);
                }catch (StringIndexOutOfBoundsException e) {
                    System.out.print("LOADING ....");
                }
//
//
                RestaurantListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new RestaurantListAdapter(getApplicationContext(), mRestaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RestaurantListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }

//                        String[] restaurantNames = new String[mRestaurants.size()];
//                        for (int i = 0; i < restaurantNames.length; i++) {
//                            restaurantNames[i] = mRestaurants.get(i).getName();
//                        }
//                        ArrayAdapter adapter = new ArrayAdapter(RestaurantListActivity.this, android.R.layout.simple_list_item_1, restaurantNames);
//                        mListView.setAdapter(adapter);
//
//                        for (Restaurant restaurant : mRestaurants) {
//                            Log.d(TAG, "Name: " + restaurant.getName());
//                            Log.d(TAG, "Phone: " + restaurant.getPhone());
//                            Log.d(TAG, "Website: " + restaurant.getWebsite());
//                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
//                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
//                            Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
//                        }
//                    }
                });


            }
        });
    }
}
