package com.android.abhi.redeyes.cinemabase.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.type;
import static android.R.attr.version;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.TV_SHOWS;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.*;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.*;
/**
 * Created by Abhilash Reddy on 6/4/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String databaseName = "CinemaBase.db";
    static int mversion =1;

    public DbHelper(Context context) {
        super(context,databaseName,null,mversion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating table popular movies
                String SQL_CREATE_MOVIES_TABLE_POPULARMOVIES=  "create table "+ CinemaBaseContract.Movies.TABLE_POPULARMOVIES + "("
                        +CinemaBaseContract.Movies.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +CinemaBaseContract.Movies.COL_TITLE+" TEXT NOT NULL,"
                        +CinemaBaseContract.Movies.COL_POSTER_PATH+" TEXT,"
                        +CinemaBaseContract.Movies.COL_OVERVIEW+" INTEGER NOT NULL);";

                db.execSQL(SQL_CREATE_MOVIES_TABLE_POPULARMOVIES);

        //creating table recent movies
        String SQL_CREATE_MOVIES_TABLE_RECENTMOVIES=  "create table "+ CinemaBaseContract.Movies.TABLE_RECENTMOVIES + "("
                +CinemaBaseContract.Movies.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CinemaBaseContract.Movies.COL_TITLE+" TEXT NOT NULL,"
                +CinemaBaseContract.Movies.COL_POSTER_PATH+" TEXT,"
                +CinemaBaseContract.Movies.COL_OVERVIEW+" INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MOVIES_TABLE_RECENTMOVIES);

        //creating table upcoming movies
        String SQL_CREATE_MOVIES_TABLE_UPCOMINGMOVIES=  "create table "+ CinemaBaseContract.Movies.TABLE_UPCOMINGMOVIES + "("
                +CinemaBaseContract.Movies.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CinemaBaseContract.Movies.COL_TITLE+" TEXT NOT NULL,"
                +CinemaBaseContract.Movies.COL_POSTER_PATH+" TEXT,"
                +CinemaBaseContract.Movies.COL_OVERVIEW+" INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MOVIES_TABLE_UPCOMINGMOVIES);

        //creating table toprated movies

        String SQL_CREATE_MOVIES_TABLE_TOPRATEDMOVIES=  "create table "+ CinemaBaseContract.Movies.TABLE_TOPRATEDMOVIES + "("
                +CinemaBaseContract.Movies.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CinemaBaseContract.Movies.COL_TITLE+" TEXT NOT NULL,"
                +CinemaBaseContract.Movies.COL_POSTER_PATH+" TEXT,"
                +CinemaBaseContract.Movies.COL_OVERVIEW+" INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MOVIES_TABLE_TOPRATEDMOVIES);


        //creating tables for table popular tv shows
                String SQL_CREATE_TABLE_POPULARTVSHOWS=  "create table "+ CinemaBaseContract.TVShows.TABLE_POPULARTVSHOW + "("
                        + CinemaBaseContract.TVShows.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +CinemaBaseContract.TVShows.COL_TITLE+" TEXT NOT NULL,"
                        +CinemaBaseContract.TVShows.COL_POSTER_PATH+" TEXT,"
                        +CinemaBaseContract.TVShows.COL_OVERVIEW+" INTEGER NOT NULL);";

                db.execSQL(SQL_CREATE_TABLE_POPULARTVSHOWS);

        //creating the table toprated tv shows
        String SQL_CREATE_TABLE_TOPRATEDTVSHOWS=  "create table "+ CinemaBaseContract.TVShows.TABLE_TOPRATEDTVSHOW + "("
                + CinemaBaseContract.TVShows.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CinemaBaseContract.TVShows.COL_TITLE+" TEXT NOT NULL,"
                +CinemaBaseContract.TVShows.COL_POSTER_PATH+" TEXT,"
                +CinemaBaseContract.TVShows.COL_OVERVIEW+" INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_TABLE_TOPRATEDTVSHOWS);


   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //implement the onUpgrade later

    }

}
