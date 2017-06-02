package com.android.abhi.redeyes.cinemabase.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.abhi.redeyes.cinemabase.R;

/**
 * Created by Abhilash Reddy on 5/31/2017.
 */

public class TopRatedTVShowsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topratedtvshows,container,false);

        return view;
    }
}
