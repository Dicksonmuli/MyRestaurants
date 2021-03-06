package com.dicksonmully6gmail.myrestaurants.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dickson on 5/27/17.
 */

//changing m-prefix-vars to match the name of the keys of children nodes
//    for firebase to to read and write POJOs (plain old Java objects)
@Parcel
public class Restaurant {
    // fields must be public
    String name;
    String phone;
    String website;
    double rating;
    String imageUrl;
    List<String> address = new ArrayList<>();
    double latitude;
    double longitude;
    List<String> categories = new ArrayList<>();
    private  String pushId;
    String index;

//    empty constructor needed by the Parceler library
    public Restaurant() {}

    public Restaurant(String name, String phone, String website,
                      double rating, String imageUrl, double latitude, double longitude, ArrayList<String> address,
                       ArrayList<String> categories) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = getLargeImageUrl(imageUrl);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
        this.index = "not_specified";
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getCategories() {
        return categories;
    }

    //refactoring image getter method to retrieve high quality image
    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 5).concat("o.jpg");
        return largeImageUrl;

    }
    //adding pushId - a unique id to identify each user
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

}