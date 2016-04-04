package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    TwitterClient client;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        client = TwitterApplication.getRestClient();
        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJSON(response);
                getSupportActionBar().setTitle("@" + user.getScreenName());
                populateProfileHeader(user);
            }
        });
        if(savedInstanceState == null){
            String screenName = getIntent().getStringExtra("screen_name");

            UserTimelineFragment fragmentUserTimeline = UserTimelineFragment.newInstance(screenName);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer,fragmentUserTimeline);
            ft.commit();
        }
    }

    private void populateProfileHeader(User user) {
        TextView tvname = (TextView)findViewById(R.id.tvName);
        TextView tvTagLine = (TextView)findViewById(R.id.tvTagLine);
        TextView tvFollwers = (TextView)findViewById(R.id.tvFollwers);
        TextView tvFollwing = (TextView)findViewById(R.id.tvFollwing);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);

        tvname.setText(user.getName());
        tvTagLine.setText(user.getTagLine());
        tvFollwers.setText(user.getFollwersCount()+" Followers");
        tvFollwing.setText(user.getFollwingCount()+" Following");
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.) {
//            Toast.makeText(this,"back",Toast.LENGTH_LONG).show();
//            Intent i = new Intent(this,TimelineActivity.class);
//            startActivity(i);
//        }

        return super.onOptionsItemSelected(item);
    }
}
