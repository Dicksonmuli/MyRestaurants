package com.dicksonmully6gmail.myrestaurants.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicksonmully6gmail.myrestaurants.R;
import com.dicksonmully6gmail.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.dicksonmully6gmail.myrestaurants.util.OnStartDragListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedRestaurantListFragment extends Fragment implements OnStartDragListener{

    private FirebaseRestaurantListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public SavedRestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_restaurant_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

}
