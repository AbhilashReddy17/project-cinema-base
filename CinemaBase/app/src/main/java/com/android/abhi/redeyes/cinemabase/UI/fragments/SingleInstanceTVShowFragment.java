package com.android.abhi.redeyes.cinemabase.UI.fragments;

import android.support.v4.app.Fragment;

import com.android.abhi.redeyes.cinemabase.UI.TVShowsFragmentAdapter;


/**
 * Created by Abhilash Reddy on 6/1/2017.
 */

public class SingleInstanceTVShowFragment {

    static PopularTVShowsFragment mpopulartvshows;
    static TopRatedTVShowsFragment mtopratedtvshows;

    //returning the partiular fragment according to the viewpager position.

    public Fragment singleInstaceFragment(int fragmentPosition) {
        switch (fragmentPosition) {

            case TVShowsFragmentAdapter.POPULARTV_SHOWS:
                if (mpopulartvshows == null)
                    mpopulartvshows = new PopularTVShowsFragment();
                return mpopulartvshows;

            case TVShowsFragmentAdapter.TOPRATED_TV_SHOWS:
                if (mtopratedtvshows == null)
                    mtopratedtvshows = new TopRatedTVShowsFragment();
                return mtopratedtvshows;


            default:
                if (mpopulartvshows == null)
                    mpopulartvshows = new PopularTVShowsFragment();
                return mpopulartvshows;

        }
    }
}
