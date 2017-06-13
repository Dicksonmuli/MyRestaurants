package com.dicksonmully6gmail.myrestaurants.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.dicksonmully6gmail.myrestaurants.Constants;
import com.dicksonmully6gmail.myrestaurants.R;
import com.dicksonmully6gmail.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.dicksonmully6gmail.myrestaurants.adapters.FirebaseRestaurantViewHolder;
import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.dicksonmully6gmail.myrestaurants.util.OnStartDragListener;
import com.dicksonmully6gmail.myrestaurants.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity  {

   //refactoring this activity to a fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_restaurant_list);
    }
}
