/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FavoriteMovieActivity extends AppCompatActivity {
    ArrayList<MovieInfo> trkarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);


        if(MainActivity.sp.getFavorites(getApplicationContext())!=null) {
            trkarray = MainActivity.sp.getFavorites(getApplicationContext());
            if (trkarray.size() != 0) {
                ListView listView = (ListView) findViewById(R.id.ListViewFavorite);
                CustomAdapter customAdapter = new CustomAdapter(this, trkarray);
                listView.setAdapter(customAdapter);
                customAdapter.setNotifyOnChange(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("demo", "onItemClick: ");
                        Intent i1 = new Intent (FavoriteMovieActivity.this, MovieDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("json",trkarray.get(i));
                        i1.putExtras(bundle);
                        startActivity(i1);

                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_Home:
                Intent intent = new Intent(FavoriteMovieActivity.this,MainActivity.class);
                startActivity(intent); // call activity mainactivity
                finish();
                break;
            case R.id.action_sortPopulr:
                Collections.sort(trkarray, new RatingCompare());
                ListView listView = (ListView) findViewById(R.id.ListViewFavorite);
                CustomAdapter customAdapter = new CustomAdapter(FavoriteMovieActivity.this, trkarray);
                listView.setAdapter(customAdapter);
                customAdapter.setNotifyOnChange(true);
                break;
            case R.id.action_SortRating:
                Collections.sort(trkarray, new PopularityCompare());
                ListView listView1 = (ListView) findViewById(R.id.ListViewFavorite);
                CustomAdapter customAdapter1 = new CustomAdapter(FavoriteMovieActivity.this, trkarray);
                listView1.setAdapter(customAdapter1);
                customAdapter1.setNotifyOnChange(true);
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
        mi.inflate(R.menu.favoriteoverview, menu);
        return true;    }

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
