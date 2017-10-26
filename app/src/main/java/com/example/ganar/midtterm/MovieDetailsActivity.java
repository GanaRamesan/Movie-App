/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    static MovieInfo movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movieInfo = intent.getExtras().getParcelable("json");
        ImageView imageView = (ImageView) findViewById(R.id.imageViewMovie);
        TextView title = (TextView) findViewById(R.id.textViewTitile);
        TextView overview = (TextView) findViewById(R.id.textViewOverview);
        TextView release = (TextView) findViewById(R.id.textViewRelease);
        TextView rating = (TextView) findViewById(R.id.textViewRatingg);


        title.setText(movieInfo.getMovie_name());
        overview.setText(movieInfo.getOverview());
        release.setText(movieInfo.getReleasedate());
        rating.setText(movieInfo.getRating());

        String imgUrl = "http://image.tmdb.org/t/p/w342/" + movieInfo.getPosterpath();
        if(!movieInfo.getPosterpath().isEmpty())
            Picasso.with(MovieDetailsActivity.this).load(imgUrl).into(imageView);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(MovieDetailsActivity.this,MainActivity.class);
                startActivity(intent); // call activity mainactivity
                finish();
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
        mi.inflate(R.menu.finaloverflow, menu);
        return true;    }
}
