package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 3/28/2016.
 */
public class User {
    //List atribute
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagLine;
    private int follwersCount;
    private int follwingCount;


    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getTagLine() {
        return tagLine;
    }

    public int getFollwersCount() {
        return follwersCount;
    }

    public int getFollwingCount() {
        return follwingCount;
    }

    //deserialize the  user json => user
    public static User fromJSON(JSONObject json){
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagLine = json.getString("description");
            u.follwersCount = json.getInt("followers_count");
            u.follwingCount = json.getInt("friends_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return u;
    }
}
