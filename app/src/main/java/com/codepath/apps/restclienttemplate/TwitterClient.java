package com.codepath.apps.restclienttemplate;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "Ti0t9CdgXwgUo3ANBfAbLNT87";       // Change this
	public static final String REST_CONSUMER_SECRET = "1c31159VUwrYKDPR87qFbOxM8icSYJLv0HlmWsBgKWdfWzZJH2"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}


	public void getHomeTimeline(AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		//Specify the params
		RequestParams params = new RequestParams();
		params.put("count",25);
		params.put("since_id",1);
		//Execute the request
		getClient().get(apiUrl,params,handler);
	}

	public void getMentionsTimeline(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		//Specify the params
		RequestParams params = new RequestParams();
		params.put("count",25);
		//Execute the request
		getClient().get(apiUrl,params,handler);
	}
	public void getUserTimeline(String screenName, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count",25);
		params.put("screen_name",screenName);
		getClient().get(apiUrl, params, handler);
	}
	public void getUserInfo(AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("account/verify_credentials.json");
		getClient().get(apiUrl,null,handler);
	}
	public void postTweet(String status,AsyncHttpResponseHandler handler){
		String apiUrl  = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status",status);
		getClient().post(apiUrl, params, handler);
	}

}