package com.android.abhi.redeyes.cinemabase.UI.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Abhilash Reddy on 5/31/2017.
 */
public class SingleInstaceMovieFragment {

    public static final String TAG = SingleInstaceMovieFragment.class.getSimpleName();
    public static final int MOVIES =0;
    public static final int RECENT_MOVIES =1;
    public static final int POPULAR_MOVIES =2;
    public static final int UPCOMING_MOVIES =3;
    public static final int TOPRATED_MOVIES =4;

    static MoviesFragment mmovies;
    static RecentMoviesFragment mrecentmovies;
    static PopularMoviesFragment mpopularmovies;
    static TopRatedMoviesFragment mtopratedmovies;
    static UpcomingMoviesFragment mupcomingmovies;

    //returning the partiular fragment according to the viewpager position.

    public  Fragment singleInstaceFragment(int fragmentPosition){
        switch(fragmentPosition){
            case MOVIES:
                if(mmovies ==null)
                    mmovies = new MoviesFragment();
                return mmovies;
            case RECENT_MOVIES:
                Log.d(TAG, "singleInstaceFragment: "+RECENT_MOVIES);
                if(mrecentmovies ==null)
                    mrecentmovies = new RecentMoviesFragment();
                return mrecentmovies;

            case UPCOMING_MOVIES:
                if(mupcomingmovies ==null)
                    mupcomingmovies = new UpcomingMoviesFragment();
                return mupcomingmovies;

            case TOPRATED_MOVIES:
                if(mtopratedmovies == null)
                    mtopratedmovies = new TopRatedMoviesFragment();
                return  mtopratedmovies;

            case POPULAR_MOVIES:
                if(mpopularmovies == null)
                    mpopularmovies = new PopularMoviesFragment();
                return  mpopularmovies;

            default:
                if(mmovies ==null)
                    mmovies = new MoviesFragment();
                return mmovies;

        }
    }
}
