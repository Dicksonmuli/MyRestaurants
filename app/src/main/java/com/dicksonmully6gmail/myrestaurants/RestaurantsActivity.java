package com.dicksonmully6gmail.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
//    without butterknife
//    private TextView mLocationTextView;
//    private ListView mListView;

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    public static final String TAG = RestaurantsActivity.class.getSimpleName();
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    private String[] restaurants = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House",
            "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell",
            "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche",
            "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups",
            "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//        mListView = (ListView) findViewById(R.id.listView);
//
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);

        MyRestaurantsArrayAdapter adapter = new
                MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines); //must match constructor!
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
                Log.v(TAG,"In the onItemClickListener");
            }
        });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are the restaurants near: " + location);
        Log.d(TAG, "In the onCreate method!");

        getRestaurants(location);

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
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
//                    mRestaurants array will  replace our hard-coded restaurants array
                    mRestaurants = yelpService.processResults(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
