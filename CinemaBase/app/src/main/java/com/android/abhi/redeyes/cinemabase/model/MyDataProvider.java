package com.android.abhi.redeyes.cinemabase.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.R.attr.id;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.CONTENT_AUTHORITY;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.POPULAR_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.RECENT_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TABLE_POPULARMOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TABLE_RECENTMOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TABLE_TOPRATEDMOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TABLE_UPCOMINGMOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TOPRATED_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.UPCOMING_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.POPULARTV_SHOWS;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.TABLE_POPULARTVSHOW;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.TABLE_TOPRATEDTVSHOW;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.TOPRATEDTV_SHOWS;

/**
 * Created by Abhilash Reddy on 6/3/2017.
 */

public class MyDataProvider extends ContentProvider {

    private static final String LOG_TAG = MyDataProvider.class.getSimpleName();
    DbHelper dbhelper;


    //uri matchers for matching particular uri
    public static final UriMatcher suriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        //setting uri matchers for the movie tables
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.Movies.TABLE_POPULARMOVIES, POPULAR_MOVIES);
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.Movies.TABLE_RECENTMOVIES, RECENT_MOVIES);
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.Movies.TABLE_TOPRATEDMOVIES, TOPRATED_MOVIES);
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.Movies.TABLE_UPCOMINGMOVIES, UPCOMING_MOVIES);

        //setting uri matchers for the tv shows tables
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.TVShows.TABLE_POPULARTVSHOW, POPULARTV_SHOWS);
        suriMatcher.addURI(CONTENT_AUTHORITY, CinemaBaseContract.TVShows.TABLE_TOPRATEDTVSHOW, TOPRATEDTV_SHOWS);
    }

    @Override
    public boolean onCreate() {
        //creating a reference to the database
        dbhelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int match = suriMatcher.match(uri);
        Cursor cursor;
        SQLiteDatabase readableDatabase = dbhelper.getReadableDatabase();

        switch (match) {
            case POPULAR_MOVIES:
                cursor = readableDatabase.query(TABLE_POPULARMOVIES, projection, selection, selectionArgs, null, null, null);
                break;
            case RECENT_MOVIES:
                cursor = readableDatabase.query(TABLE_RECENTMOVIES, projection, selection, selectionArgs, null, null, null);
                break;
            case UPCOMING_MOVIES:
                cursor = readableDatabase.query(TABLE_UPCOMINGMOVIES, projection, selection, selectionArgs, null, null, null);
                break;
            case TOPRATED_MOVIES:
                cursor = readableDatabase.query(TABLE_TOPRATEDMOVIES, projection, selection, selectionArgs, null, null, null);
                break;
            case POPULARTV_SHOWS:
                cursor = readableDatabase.query(TABLE_POPULARTVSHOW, projection, selection, selectionArgs, null, null, null);
                break;
            case TOPRATEDTV_SHOWS:
                cursor = readableDatabase.query(TABLE_TOPRATEDTVSHOW, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("cannot get the uri " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int match = suriMatcher.match(uri);
        switch (match) {
            case POPULAR_MOVIES:
                return insertData(uri, values, TABLE_POPULARMOVIES);
            case RECENT_MOVIES:
                return insertData(uri, values, TABLE_RECENTMOVIES);
            case UPCOMING_MOVIES:
                return insertData(uri, values, TABLE_UPCOMINGMOVIES);
            case TOPRATED_MOVIES:
                return insertData(uri, values, TABLE_TOPRATEDMOVIES);
            case POPULARTV_SHOWS:
                return insertData(uri, values, TABLE_POPULARTVSHOW);
            case TOPRATEDTV_SHOWS:
                return insertData(uri, values, TABLE_TOPRATEDTVSHOW);
            default:
                throw new IllegalArgumentException("insertion not supported for this uri");
        }

    }

    //method for inserting the values into the database
    private Uri insertData(Uri uri, ContentValues values, String tablemovie) {
        Cursor cursor;
        SQLiteDatabase writableDatabase = dbhelper.getWritableDatabase();
        try {
            long id = writableDatabase.insert(tablemovie, null, values);
            if (id == -1) {
                Log.e(LOG_TAG, "Failed to insert row for " + uri);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int match = suriMatcher.match(uri);
        switch (match) {
            case POPULAR_MOVIES:
                return deleteData(uri, TABLE_POPULARMOVIES);
            case RECENT_MOVIES:
                return deleteData(uri, TABLE_RECENTMOVIES);
            case UPCOMING_MOVIES:
                return deleteData(uri, TABLE_UPCOMINGMOVIES);
            case TOPRATED_MOVIES:
                return deleteData(uri, TABLE_TOPRATEDMOVIES);
            case POPULARTV_SHOWS:
                return deleteData(uri, TABLE_POPULARTVSHOW);
            case TOPRATEDTV_SHOWS:
                return deleteData(uri, TABLE_TOPRATEDTVSHOW);
            default:
                throw new IllegalArgumentException("insertion not supported for this uri");
        }

    }

    private int deleteData(Uri uri, String tablename) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        return database.delete(tablename, null, null);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        //no implementaiton
        return 0;
    }
}
