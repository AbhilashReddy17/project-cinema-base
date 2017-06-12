package com.android.abhi.redeyes.cinemabase.UI;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.bumptech.glide.Glide;

import info.movito.themoviedbapi.model.MovieDb;

import static android.R.attr.configure;

public class ShowDetails extends AppCompatActivity {

    ImageView poster;
    TextView overview;
    TextView title;
    TextView rating;
     TextView revenue;
    TextView releaseDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        configureViews();

        MovieDb movie = (MovieDb) getIntent().getSerializableExtra("movies");

        Glide.with(this).load(movie.getPosterPath()).into(poster);
        title.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());
        rating.setText(movie.getUserRating()+"");
        revenue.setText(movie.getRevenue()+"");
        releaseDate.setText(movie.getReleaseDate());

    }

    private void configureViews() {
        poster = (ImageView) findViewById(R.id.movie_full_poster);
        overview = (TextView) findViewById(R.id.movie_description);
        title = (TextView) findViewById(R.id.movie_title);
        rating = (TextView) findViewById(R.id.rating);
        revenue = (TextView) findViewById(R.id.revenue);
        releaseDate = (TextView) findViewById(R.id.releasedate);
    }
}
