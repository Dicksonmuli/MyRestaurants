package com.dicksonmully6gmail.myrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextMessage;
    // butterknife to make code DRY
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

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
               String location = mLocationEditText.getText().toString();
               Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
               intent.putExtra("location", location);
               startActivity(intent);
           }
    }


}
