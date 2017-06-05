package com.android.abhi.redeyes.cinemabase.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.model.DataModel;
import com.android.abhi.redeyes.cinemabase.model.TaskLoadingDatatoDB;

import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.ResponseStatusException;
import info.movito.themoviedbapi.model.tv.TvSeries;

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

        //configuring all required viems and assignments.
        configureViews();

        if (!isNetworkAvailable()) {
            DataModel.TVShows.mpopularTvShows = null;
            DataModel.TVShows.mtopRatedTvShows = null;

            DataModel.DBMovies.mpopularMovies = null;
            DataModel.DBMovies.mrecentMovies = null;
            DataModel.DBMovies.mtopratedmovies = null;
            DataModel.DBMovies.mupcomingMovies = null;


            Toast.makeText(this, getResources().getString(R.string.NoDataAvailable), Toast.LENGTH_LONG).show();
        } else {
            //loading movies into the model
            TaskLoadingData task = new TaskLoadingData();
            task.execute();
        }


        final FragmentManager manager = getSupportFragmentManager();
        final ViewPager pager = (ViewPager) findViewById(R.id.mainactivity_viewpager);
        movieadapter = null;
        movieadapter = new MoviesFragmentAdapter(manager, MainActivity.this);
        pager.setAdapter(movieadapter);


        //implementing the onclick listners for the respective categorys
        selectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] categorys = {"Movies", "TV Shows"};
                dialog.setItems(categorys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                movieadapter = null;
                                movieadapter = new MoviesFragmentAdapter(manager, MainActivity.this);
                                pager.setAdapter(movieadapter);
                                break;
                            case 1:
                                tvsowadapter = null;
                                tvsowadapter = new TVShowsFragmentAdapter(manager, MainActivity.this);
                                pager.setAdapter(tvsowadapter);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });


    }

    private boolean isNetworkAvailable() {
        // checking if device has access to internet
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void configureViews() {
        selectCategory = (ImageView) findViewById(R.id.selectCategory);
        dialog = new AlertDialog.Builder(this);
    }

    class TaskLoadingData extends AsyncTask<String, MovieDb, Void> {
        LinearLayout appconfigureLayout = (LinearLayout) findViewById(R.id.configureAppLayout);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            appconfigureLayout.setVisibility(View.VISIBLE);
            Log.d(TAG, "onPreExecute: ");

            //checking orientation and freezing it till we fetch data
            if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                MainActivity.this.setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
            } else {
                MainActivity.this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.d(TAG, "doInBackground: entered");

//18776af9228988fa403a2b0ad682e344
//19c112a1364b89f6c5739a931f9fded6
            try {
                TmdbApi tmdbapi = new TmdbApi("18776af9228988fa403a2b0ad682e344");

                try {
                    TmdbMovies tmdbMovies = tmdbapi.getMovies();
                    List<MovieDb> popularmovies = tmdbMovies.getPopularMovies("en", 1).getResults();
                    DataModel.DBMovies.mpopularMovies = popularmovies;
                    List<MovieDb> topratedMovies = tmdbMovies.getTopRatedMovies("en", 1).getResults();
                    DataModel.DBMovies.mtopratedmovies = topratedMovies;
                    List<MovieDb> upcomingMovies = tmdbMovies.getUpcoming("en", 1).getResults();
                    DataModel.DBMovies.mupcomingMovies = upcomingMovies;
                    List<MovieDb> recentMovies = tmdbMovies.getNowPlayingMovies("en", 1).getResults();
                    DataModel.DBMovies.mrecentMovies = recentMovies;
                    Log.d(TAG, "doInBackground: exiting");
                } catch (ResponseStatusException ex) {
                    ex.printStackTrace();
                    Log.d(TAG, "doInBackground: responsecode " + ex.getResponseStatus().getStatusCode());
                } catch (Exception ex) {
                    Log.d(TAG, "doInBackground: exception thrown");
                }

                try {
                    TmdbTV tvseries = tmdbapi.getTvSeries();
                    List<TvSeries> populartvseries = tvseries.getPopular("en", 1).getResults();
                    DataModel.TVShows.mpopularTvShows = populartvseries;
                    List<TvSeries> topratedTvSeries = tvseries.getTopRated("en", 1).getResults();
                    DataModel.TVShows.mtopRatedTvShows = topratedTvSeries;

                } catch (ResponseStatusException ex) {
                    Log.d(TAG, "doInBackground: exceptipon in connecting TV shows");
                }

            } catch (ResponseStatusException ex) {
                Log.d(TAG, "doInBackground: exception in connecting api");
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "completed loading movies");
            appconfigureLayout.setVisibility(View.GONE);
            //releasing the orientation
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

            //loading data into database
            TaskLoadingDatatoDB task = new TaskLoadingDatatoDB(MainActivity.this);
            task.execute();
        }
    }

}
