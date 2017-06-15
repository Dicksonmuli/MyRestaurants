package com.dicksonmully6gmail.myrestaurants.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dicksonmully6gmail.myrestaurants.Constants;
import com.dicksonmully6gmail.myrestaurants.R;
import com.dicksonmully6gmail.myrestaurants.models.Restaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

//  restaurant object
    private Restaurant mRestaurant;
    private ArrayList<Restaurant> mRestaurants;
    private int mPosition;
    private String mSource;
    private static final int REQUEST_IMAGE_CAPTURE = 111;


    public static RestaurantDetailFragment newInstance(ArrayList<Restaurant> restaurants,
                                                       Integer position, String source) {
        //wrapping restaurant with parcels for serialization
        RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(restaurants));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        unwrapping restaurant on onCreate
        mRestaurants = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RESTAURANTS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRestaurant = mRestaurants.get(mPosition);
//        invoke the menu items
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

//    restaurant object used to set our ImageView and TextViews in onCreateView on onCreate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ButterKnife.bind(this, view);


           Picasso.with(view.getContext())
                   .load(mRestaurant.getImageUrl())
                   .resize(MAX_WIDTH, MAX_HEIGHT)
                   .centerCrop()
                   .into(mImageLabel);

           mNameLabel.setText(mRestaurant.getName());
           mCategoriesLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
           mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
           mPhoneLabel.setText(mRestaurant.getPhone());
           mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));

        mWebsiteLabel.setOnClickListener( this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener( this);
        //setting click listener to saveRestaurant button
        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveRestaurantButton.setVisibility(View.GONE);
        } else {
            mSaveRestaurantButton.setOnClickListener(this);
        }


        return view;
    }
//    implicit intent
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mRestaurant.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mRestaurant.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mRestaurant.getLatitude()
                            + "," + mRestaurant.getLongitude()
                            + "?q=(" + mRestaurant.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveRestaurantButton) {
            //getting the current user by user id when saveRest button is clicked
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                    .child(uid);
            /** add the pushID of the restaurant to be saved before setting the
            * value at given reference
             */
            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mRestaurant.setPushId(pushId);
            pushRef.setValue(mRestaurant);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
    //inflating photo menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (mSource.equals(Constants.SOURCE_SAVED)) {
            inflater.inflate(R.menu.menu_photo, menu);
        } else {
            inflater.inflate(R.menu.menu_main, menu);
        }
    }
    //overriding item selected method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
             default:
                 break;
        }
        return false;
    }
    //launches the camera
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * overriding onActivityResult triggered by startActivityForResult(),
     * in order to snag our picture
     * @param requestCode - represents the REQUEST_IMAGE_CAPTURE value in onLaunchCamera
     * @param resultCode -represents the status of the activity
     * @param data - is an Intent object that includes intent extras
     *             containing the information being returned
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageLabel.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    /**
     * encoding Bitmap image and saving to the firebase
     * creating ByteArrayOutputStream
     * @param bitmap
     */
    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        compressing the image and determining the quality (100)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        locate the node containing the current image URL for this specific restaurant on this
//        specific user's saved restaurants list, and overwrite it with our new, encoded image.
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(mRestaurant.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }

}
