package com.dicksonmully6gmail.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.myrestaurants.Constants;
import com.dicksonmully6gmail.myrestaurants.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

//import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    public static final String TAG = MainActivity.class.getSimpleName();

    //member variables to store reference to the shared preferences
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//    private DatabaseReference mSearchedLocationReference;
//    private ValueEventListener mSearchedLocationReferenceListener;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // butterknife to make code DRY
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
//    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedRestaurantsButton) Button mSavedRestaurantsButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        instance of our SearchedLocations DatabaseReference
//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION); //pinpoint location node
//
//        mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {//when smth change
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Location updated", "locaton: " + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //update UI here if erroe occcured
//            }
//        });

        Typeface alexBrushFont = Typeface.createFromAsset(getAssets(), "fonts/AlexBrush-Regular.ttf");
        mAppNameTextView.setTypeface(alexBrushFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mFindRestaurantsButton.setOnClickListener(this);
        mSavedRestaurantsButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                }else {

                }
            }
        };


    }
    //overriding onstart
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    //overriding onStop
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onClick(View v) {
           if(v == mFindRestaurantsButton) {
//                   String location = mLocationEditText.getText().toString();

//                   saveLocationtoFirebase(location);
//                   if(!(location).equals("")) {
//                       addToSharedPreferences(location);
//                   }
                   Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
//                   intent.putExtra("location", location);
                   startActivity(intent);

           }
        if (v == mSavedRestaurantsButton) {
            Intent intent = new Intent(MainActivity.this, SavedRestaurantListActivity.class);
            startActivity(intent);
        }

    }
    //inflate overflow menu in the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
//    action for item selected in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //logout method
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        //returning to login activity after the user logs out
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    //save to firebase method
//    public void saveLocationtoFirebase(String location) {
//        mSearchedLocationReference.push().setValue(location); //call the push() method before setting the value to prevent overwriting
//    }
    //a method  which takes the user-inputted zip code
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

//    remove listener when the user quits interacting with the activity
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        try {
//            mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
//        }catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

}
