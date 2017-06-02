package com.android.abhi.redeyes.cinemabase.UI;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.abhi.redeyes.cinemabase.R;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView selectCategory;
    AlertDialog.Builder dialog;
    MoviesFragmentAdapter movieadapter;
    TVShowsFragmentAdapter tvsowadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureViews();

        final FragmentManager manager = getSupportFragmentManager();
        final ViewPager pager = (ViewPager) findViewById(R.id.mainactivity_viewpager);
        movieadapter =null;
        movieadapter = new MoviesFragmentAdapter(manager,MainActivity.this);
        pager.setAdapter(movieadapter);

        selectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] categorys = {"Movies","TV Shows"};
                dialog.setItems(categorys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case 0:
                                movieadapter =null;
                                movieadapter = new MoviesFragmentAdapter(manager,MainActivity.this);
                                pager.setAdapter(movieadapter);
                                break;
                            case 1:
                                tvsowadapter = null;
                                tvsowadapter =new TVShowsFragmentAdapter(manager,MainActivity.this);
                                pager.setAdapter(tvsowadapter);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

    }

    private void configureViews() {
        selectCategory = (ImageView) findViewById(R.id.selectCategory);
        dialog = new AlertDialog.Builder(this);
    }

    class checkAsynkTask extends AsyncTask<String,MovieDb,MovieDb> {

        @Override
        protected MovieDb doInBackground(String... params) {
            TmdbMovies movies = new TmdbApi("19c112a1364b89f6c5739a931f9fded6").getMovies();
            MovieDb movie = movies.getMovie(5353, "en");

            publishProgress(movie);
            return movie;
        }

        @Override
        protected void onProgressUpdate(MovieDb... values) {
            Log.d(TAG, "onProgressUpdate: "+values[0].getTitle());
        }
    }
}
