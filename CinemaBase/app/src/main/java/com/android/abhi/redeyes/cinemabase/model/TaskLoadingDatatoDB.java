package com.android.abhi.redeyes.cinemabase.model;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by Abhilash Reddy on 6/4/2017.
 */

public class TaskLoadingDatatoDB extends AsyncTask<String, Void, Void> {
    Context context;

    public TaskLoadingDatatoDB(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {


        //inserting popularmovies data into database;
        if (DataModel.DBMovies.mpopularMovies != null) {
            //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
            context.getContentResolver().delete(CinemaBaseContract.Movies.CONTENT_URI_POPULARMOVIES, null, null);

            for (MovieDb movie : DataModel.DBMovies.mupcomingMovies) {
                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, movie.getOriginalTitle());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, movie.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, movie.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.Movies.CONTENT_URI_POPULARMOVIES, value);
            }
        }
        //inserting recentmovies data into database
        if (DataModel.DBMovies.mrecentMovies != null) {
            //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
            context.getContentResolver().delete(CinemaBaseContract.Movies.CONTENT_URI_RECENTMOVIES, null, null);

            for (MovieDb movie : DataModel.DBMovies.mrecentMovies) {
                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, movie.getOriginalTitle());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, movie.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, movie.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.Movies.CONTENT_URI_RECENTMOVIES, value);
            }
        }
        //inserting upcomingmovies data into database
        if (DataModel.DBMovies.mupcomingMovies != null) {
            //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
            context.getContentResolver().delete(CinemaBaseContract.Movies.CONTENT_URI_UPCOMINGMOVIES, null, null);

            for (MovieDb movie : DataModel.DBMovies.mupcomingMovies) {
                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, movie.getOriginalTitle());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, movie.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, movie.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.Movies.CONTENT_URI_UPCOMINGMOVIES, value);
            }
        }
        //inserting topratedmovies data into database
        if (DataModel.DBMovies.mtopratedmovies != null) {
            for (MovieDb movie : DataModel.DBMovies.mtopratedmovies) {
                //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
                context.getContentResolver().delete(CinemaBaseContract.Movies.CONTENT_URI_TOPRATEDMOVIES, null, null);

                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, movie.getOriginalTitle());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, movie.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, movie.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.Movies.CONTENT_URI_TOPRATEDMOVIES, value);
            }
        }
        //inserting populartvshows data into database
        if (DataModel.TVShows.mpopularTvShows != null) {
            //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
            context.getContentResolver().delete(CinemaBaseContract.TVShows.CONTENT_URI_POPULARTVSHOWS, null, null);

            for (TvSeries series : DataModel.TVShows.mpopularTvShows) {
                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, series.getOriginalName());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, series.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, series.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.TVShows.CONTENT_URI_POPULARTVSHOWS, value);
            }
        }
        //inserting topratedtvshows data into database
        if (DataModel.TVShows.mtopRatedTvShows != null) {
            //deleting the data before loading it because we wanted to store only the last fetched data when accessed through internet
            context.getContentResolver().delete(CinemaBaseContract.TVShows.CONTENT_URI_TOPRATEDTVSHOWS, null, null);

            for (TvSeries series : DataModel.TVShows.mtopRatedTvShows) {
                ContentValues value = new ContentValues();
                value.put(CinemaBaseContract.Movies.COL_TITLE, series.getOriginalName());
                value.put(CinemaBaseContract.Movies.COL_POSTER_PATH, series.getPosterPath());
                value.put(CinemaBaseContract.Movies.COL_OVERVIEW, series.getOverview());
                context.getContentResolver().insert(CinemaBaseContract.TVShows.CONTENT_URI_TOPRATEDTVSHOWS, value);
            }
        }

        return null;
    }

}
