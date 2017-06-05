package com.android.abhi.redeyes.cinemabase.model;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by Abhilash Reddy on 6/3/2017.
 */

public class CinemaBaseContract {

    public static final String CONTENT_AUTHORITY = "com.android.abhi.redeyes.cinemabase.model.MyDataProvider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";


    public static class Movies {
        public static final int MOVIES = 0;
        public static final int RECENT_MOVIES = 1;
        public static final int POPULAR_MOVIES = 2;
        public static final int UPCOMING_MOVIES = 3;
        public static final int TOPRATED_MOVIES = 4;


        public static final String TABLE_POPULARMOVIES = "popularmovies";
        public static final String TABLE_RECENTMOVIES = "recentmovies";
        public static final String TABLE_UPCOMINGMOVIES = "upcomingmovies";
        public static final String TABLE_TOPRATEDMOVIES = "topratedmovies";

        public static final String COL_ID = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_POSTER_PATH = "poster_path";
        public static final String COL_OVERVIEW = "overview";

        //uris for the movietables
        public static final Uri CONTENT_URI_POPULARMOVIES = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_POPULARMOVIES);
        public static final Uri CONTENT_URI_RECENTMOVIES = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_RECENTMOVIES);
        public static final Uri CONTENT_URI_UPCOMINGMOVIES = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_UPCOMINGMOVIES);
        public static final Uri CONTENT_URI_TOPRATEDMOVIES = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_TOPRATEDMOVIES);

        //mime types
        public static final String CONTENT_POPULARMOVIES =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + POPULAR_MOVIES;

        public static final String CONTENT_RECENTMOVIES =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + RECENT_MOVIES;
        public static final String CONTENT_UPCOMINGMOVIES =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + UPCOMING_MOVIES;
        public static final String CONTENT_TOPRATEDMOVIES =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TOPRATED_MOVIES;

    }

    public static class TVShows {
        public static final int TOPRATEDTV_SHOWS = 5;
        public static final int POPULARTV_SHOWS = 6;
        public static final int TV_SHOWS = 7;

        public static final String TABLE_POPULARTVSHOW = "populartvshow";
        public static final String TABLE_TOPRATEDTVSHOW = "topraredtvshow";

        public static final String COL_ID = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_POSTER_PATH = "poster_path";
        public static final String COL_OVERVIEW = "overview";

        //uris for the tables tvshows
        public static final Uri CONTENT_URI_POPULARTVSHOWS = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_POPULARTVSHOW);
        public static final Uri CONTENT_URI_TOPRATEDTVSHOWS = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_TOPRATEDTVSHOW);

        //mime types
        public static final String CONTENT_TOPRATEDTVSHOW =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TOPRATEDTV_SHOWS;
        public static final String CONTENT_POPULARTVSHOWs =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + POPULARTV_SHOWS;


    }

}
