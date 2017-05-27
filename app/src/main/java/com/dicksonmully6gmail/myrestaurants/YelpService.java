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
//        HttpUrl class to construct the URL we'll send our request to
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

//        request using the created url
    }
}
