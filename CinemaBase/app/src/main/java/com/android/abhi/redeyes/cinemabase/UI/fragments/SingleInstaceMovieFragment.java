package com.android.abhi.redeyes.cinemabase.UI.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.POPULAR_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.RECENT_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TOPRATED_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.UPCOMING_MOVIES;


/**
 * Created by Abhilash Reddy on 5/31/2017.
 */
public class SingleInstaceMovieFragment {

    public static final String TAG = SingleInstaceMovieFragment.class.getSimpleName();


    static MoviesFragment mmovies;
    static RecentMoviesFragment mrecentmovies;
    static PopularMoviesFragment mpopularmovies;
    static TopRatedMoviesFragment mtopratedmovies;
    static UpcomingMoviesFragment mupcomingmovies;

    //returning the partiular fragment according to the viewpager position.

    public Fragment singleInstaceFragment(int fragmentPosition) {
        switch (fragmentPosition) {
            case MOVIES:
                if (mmovies == null)
                    mmovies = new MoviesFragment();
                return mmovies;
            case RECENT_MOVIES:
                Log.d(TAG, "singleInstaceFragment: " + RECENT_MOVIES);
                if (mrecentmovies == null)
                    mrecentmovies = new RecentMoviesFragment();
                return mrecentmovies;

            case UPCOMING_MOVIES:
                if (mupcomingmovies == null)
                    mupcomingmovies = new UpcomingMoviesFragment();
                return mupcomingmovies;

            case TOPRATED_MOVIES:
                if (mtopratedmovies == null)
                    mtopratedmovies = new TopRatedMoviesFragment();
                return mtopratedmovies;

            case POPULAR_MOVIES:
                if (mpopularmovies == null)
                    mpopularmovies = new PopularMoviesFragment();
                return mpopularmovies;

            default:
                if (mmovies == null)
                    mmovies = new MoviesFragment();
                return mmovies;

        }
    }
}
