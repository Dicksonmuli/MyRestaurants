package com.dicksonmully6gmail.myrestaurants;



import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by dickson on 5/27/17.
 */

public class YelpService {
    public void findRestaurants(String location, Callback callback) {
//        sign post - to construct oauth_signature parameter required by the Yelp API before we send the request
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);
//        create OkHttpClient to create and send our request
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

//        request using the created url
    }
}
