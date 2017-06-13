package com.dicksonmully6gmail.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//import com.dicksonmully6gmail.myrestaurants.adapters.RestaurantListAdapter;
//import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.dicksonmully6gmail.myrestaurants.Constants;
import com.dicksonmully6gmail.myrestaurants.R;
import com.dicksonmully6gmail.myrestaurants.adapters.RestaurantListAdapter;
import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.dicksonmully6gmail.myrestaurants.services.YelpService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantListActivity extends AppCompatActivity {
//refactored to have list content on RestaurantListFragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
//

    }
//
}
