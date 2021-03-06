package com.android.abhi.redeyes.cinemabase.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.Offlinedata_recyclerviewAdapter;
import com.android.abhi.redeyes.cinemabase.UI.TVShowsRecyclerViewAdapter;
import com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract;
import com.android.abhi.redeyes.cinemabase.model.DataModel;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.POPULARTV_SHOWS;


/**
 * Created by Abhilash Reddy on 5/31/2017.
 */

public class PopularTVShowsFragment extends Fragment {

    public static final String TAG = PopularTVShowsFragment.class.getSimpleName();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putInt("state", CinemaBaseContract.TVShows.TV_SHOWS);
        super.onSaveInstanceState(outState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_tvshows, container, false);

        TextView emptyview = (TextView) view.findViewById(R.id.emptyview_tvshows);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_tvshows);

        if (DataModel.TVShows.mpopularTvShows != null) {
            emptyview.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            TVShowsRecyclerViewAdapter adapter = new TVShowsRecyclerViewAdapter(getActivity(), POPULARTV_SHOWS);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(adapter);

        } else {
            if (DataModel.TVShows_Offline.mpopularTvShows_offlinedata != null) {
                recyclerView.setVisibility(View.VISIBLE);
                emptyview.setVisibility(View.GONE);
                Offlinedata_recyclerviewAdapter adapter = new Offlinedata_recyclerviewAdapter(getActivity(), DataModel.TVShows_Offline.mpopularTvShows_offlinedata);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                emptyview.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }
}
