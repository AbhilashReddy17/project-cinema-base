package com.android.abhi.redeyes.cinemabase.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment;

import static com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment.MOVIES;


/**
 * Created by Abhilash Reddy on 5/31/2017.
 */

public class MoviesFragmentAdapter extends FragmentStatePagerAdapter {


    SingleInstaceMovieFragment mfragmentInstance;
    Context context;

    public MoviesFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mfragmentInstance = new SingleInstaceMovieFragment();
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case MOVIES:
                return mfragmentInstance.singleInstaceFragment(MOVIES);

            default:
                return mfragmentInstance.singleInstaceFragment(MOVIES);
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

        @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case MOVIES:
                return context.getResources().getString(R.string.Movies);
            default:
                return "";
        }
    }
}
