/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    EditText MovieName;
    RequestParam rp;
    static SharedPreference sp = new SharedPreference();
    static ArrayList<MovieInfo> InfoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.buttonSearch);
        MovieName = (EditText) findViewById(R.id.editTextTrackSearch);

        //        getSharedPreferences("MUSIC_APP", 0).edit().clear().commit(); // ----- to clear all shared preferences


        //on click of search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String track = MovieName.getText().toString();
                if(MovieName.length()!=0)
                {
                    if (isCOnnected()) {

                        rp = new RequestParam("GET", "https://api.themoviedb.org/3/search/movie");
                        rp.addParam("query",MovieName.getText().toString());
                        rp.addParam("api_key","16ebc4ecccf4b777280e1156500c8245");
                        Log.d("demo", "onClick: track" + MovieName.getText().toString());
                        try {
                            Log.d("demo", "onClick: rp" + rp.Encodedurl());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        new DownloadJsonAsync(MainActivity.this,MainActivity.this).execute(rp);
                    } else {
                        Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    Toast.makeText(MainActivity.this, "No Movie name entered!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_SortRating:
                Collections.sort(InfoArrayList, new RatingCompare());
                ListView listView = (ListView) findViewById(R.id.tracklistResults);
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, InfoArrayList);
                listView.setAdapter(customAdapter);
                customAdapter.setNotifyOnChange(true);
                break;
            case R.id.action_sortPopulr:
                Collections.sort(InfoArrayList, new RatingCompare());
                ListView listView1 = (ListView) findViewById(R.id.tracklistResults);
                CustomAdapter customAdapter1 = new CustomAdapter(MainActivity.this, InfoArrayList);
                listView1.setAdapter(customAdapter1);
                customAdapter1.setNotifyOnChange(true);
                break;
            case R.id.action_Favorite:
                Intent i1 = new Intent(MainActivity.this, FavoriteMovieActivity.class);
                startActivity(i1);
                break;

            case R.id.action_quit:
                finishAffinity();  // to finish current activity and all parent activity
                System.exit(0);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // to add the options overflow in action bar
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.mainoverflow, menu);
        return true;    }

    //checks for connection
    private boolean isCOnnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public class RatingCompare implements Comparator<MovieInfo>
    {
        public int compare(MovieInfo left, MovieInfo right) {
            return left.getRating().compareTo(right.getRating());
        }
    }
    public class PopularityCompare implements Comparator<MovieInfo>
    {
        public int compare(MovieInfo left, MovieInfo right) {
            return left.getPopularity().compareTo(right.getPopularity());
        }
    }
}
