package com.android.abhi.redeyes.cinemabase.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstanceTVShowFragment;

/**
 * Created by Abhilash Reddy on 6/1/2017.
 */

public class TVShowsFragmentAdapter extends FragmentStatePagerAdapter {

    public static final int POPULARTV_SHOWS =0;
    public static final int TOPRATED_TV_SHOWS =1;
    SingleInstanceTVShowFragment mfragmentInstance;
    Context context;

    public TVShowsFragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
        mfragmentInstance = new SingleInstanceTVShowFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case POPULARTV_SHOWS:
                return mfragmentInstance.singleInstaceFragment(POPULARTV_SHOWS);
            case TOPRATED_TV_SHOWS:
                return mfragmentInstance.singleInstaceFragment(TOPRATED_TV_SHOWS);
            default:
                return mfragmentInstance.singleInstaceFragment(POPULARTV_SHOWS);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case POPULARTV_SHOWS:
                return context.getResources().getString(R.string.PopularTvShows);
            case TOPRATED_TV_SHOWS:
                return context.getResources().getString(R.string.TopRatedTvShows);
            default:
                return context.getResources().getString(R.string.PopularTvShows);
        }
    }
}
