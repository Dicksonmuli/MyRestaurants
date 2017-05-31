package com.dicksonmully6gmail.myrestaurants.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.myrestaurants.R;

import butterknife.Bind;
import butterknife.ButterKnife;

//import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextMessage;
    // butterknife to make code DRY
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface alexBrushFont = Typeface.createFromAsset(getAssets(), "fonts/AlexBrush-Regular.ttf");
        mAppNameTextView.setTypeface(alexBrushFont);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);

        mFindRestaurantsButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
           if(v == mFindRestaurantsButton) {
               try{
                   String location = mLocationEditText.getText().toString();
                   Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                   intent.putExtra("location", location);
                   startActivity(intent);
               }catch (Exception e) {
                   System.out.println("Ooops! No Restaurants Found!! " + e.getMessage());
               }
           }
    }


}
