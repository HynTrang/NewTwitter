package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//[
// ]
//Parse the JSON + Store the data, encapsulate state logic or display logic
public class Tweet {
    //List out the attribute
    private String body;
    private long uid;// unique id for the tweet
    private User user;//store embeddied user object
    private String createdAt;

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    //Deserialize the JSON and build Tweet objects
    //Tweet.fromJSON
    public static  Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        //Extract the values from the json, store then
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //return the tweet
        return tweet;
    }
    //Tweet.fromJSONArray[{....},{....},{.....}]=>List<Tweet>
    public static ArrayList<Tweet>fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();
        //Iterate the json array and create tweets
        for(int i = 0;i<jsonArray.length();i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if(tweet !=null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        //Return the finished list
        return tweets;
    }

}
