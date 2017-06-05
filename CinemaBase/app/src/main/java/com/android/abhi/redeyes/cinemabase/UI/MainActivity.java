package com.android.abhi.redeyes.cinemabase.UI;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
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
import com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract;
import com.android.abhi.redeyes.cinemabase.model.DataModel;
import com.android.abhi.redeyes.cinemabase.model.Offline_Data;
import com.android.abhi.redeyes.cinemabase.model.TaskLoadingDatatoDB;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.ResponseStatusException;
import info.movito.themoviedbapi.model.tv.TvSeries;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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
            getLoaderManager().initLoader(CinemaBaseContract.Movies.POPULAR_MOVIES, null, this);
            getLoaderManager().initLoader(CinemaBaseContract.Movies.RECENT_MOVIES, null, this);
            getLoaderManager().initLoader(CinemaBaseContract.Movies.UPCOMING_MOVIES, null, this);
            getLoaderManager().initLoader(CinemaBaseContract.Movies.TOPRATED_MOVIES, null, this);
            getLoaderManager().initLoader(CinemaBaseContract.TVShows.POPULARTV_SHOWS, null, this);
            getLoaderManager().initLoader(CinemaBaseContract.TVShows.TOPRATEDTV_SHOWS, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CinemaBaseContract.Movies.POPULAR_MOVIES:
                return new CursorLoader(this, CinemaBaseContract.Movies.CONTENT_URI_POPULARMOVIES, null, null, null, null);
            case CinemaBaseContract.Movies.RECENT_MOVIES:
                return new CursorLoader(this, CinemaBaseContract.Movies.CONTENT_URI_RECENTMOVIES, null, null, null, null);
            case CinemaBaseContract.Movies.UPCOMING_MOVIES:
                return new CursorLoader(this, CinemaBaseContract.Movies.CONTENT_URI_UPCOMINGMOVIES, null, null, null, null);
            case CinemaBaseContract.Movies.TOPRATED_MOVIES:
                return new CursorLoader(this, CinemaBaseContract.Movies.CONTENT_URI_TOPRATEDMOVIES, null, null, null, null);
            case CinemaBaseContract.TVShows.POPULARTV_SHOWS:
                return new CursorLoader(this, CinemaBaseContract.TVShows.CONTENT_URI_POPULARTVSHOWS, null, null, null, null);
            case CinemaBaseContract.TVShows.TOPRATEDTV_SHOWS:
                return new CursorLoader(this, CinemaBaseContract.TVShows.CONTENT_URI_TOPRATEDTVSHOWS, null, null, null, null);

        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            Log.d(TAG, "onLoadFinished: empty");
            return;
        }
        switch (loader.getId()) {
            case CinemaBaseContract.Movies.POPULAR_MOVIES:
                DataModel.DBMovies_Offline.mpopularMovies_offlinedata = loadOfflineData(cursor);
                break;
            case CinemaBaseContract.Movies.RECENT_MOVIES:
                DataModel.DBMovies_Offline.mrecentMovies_offlinedata = loadOfflineData(cursor);
                break;
            case CinemaBaseContract.Movies.UPCOMING_MOVIES:
                DataModel.DBMovies_Offline.mupcomingMovies_offlinedata = loadOfflineData(cursor);
                break;
            case CinemaBaseContract.Movies.TOPRATED_MOVIES:
                DataModel.DBMovies_Offline.mtopratedmovies_offlinedata = loadOfflineData(cursor);
                break;
            case CinemaBaseContract.TVShows.POPULARTV_SHOWS:
                DataModel.TVShows_Offline.mpopularTvShows_offlinedata = loadOfflineData(cursor);
                break;
            case CinemaBaseContract.TVShows.TOPRATEDTV_SHOWS:
                DataModel.TVShows_Offline.mtopRatedTvShows_offlinedata = loadOfflineData(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private List<Offline_Data> loadOfflineData(Cursor cursor) {
        cursor.moveToFirst();
        List<Offline_Data> datas = new ArrayList<Offline_Data>();
        while (!cursor.isAfterLast()) {
            Offline_Data data = new Offline_Data();
            data.setTitle(cursor.getString(cursor.getColumnIndex(CinemaBaseContract.Movies.COL_TITLE)));
            data.setPoster_path(cursor.getString(cursor.getColumnIndex(CinemaBaseContract.Movies.COL_POSTER_PATH)));
            data.setOverview(cursor.getString(cursor.getColumnIndex(CinemaBaseContract.Movies.COL_OVERVIEW)));
            cursor.moveToNext();
            datas.add(data);
        }
        return datas;
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
