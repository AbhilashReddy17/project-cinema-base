package com.android.abhi.redeyes.cinemabase.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.MoviesRecyclerViewAdapter;
import com.android.abhi.redeyes.cinemabase.UI.Offlinedata_recyclerviewAdapter;
import com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract;
import com.android.abhi.redeyes.cinemabase.model.DataModel;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.RECENT_MOVIES;

/**
 * Created by Abhilash Reddy on 6/1/2017.
 */

public class RecentMoviesFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("state", CinemaBaseContract.Movies.RECENT_MOVIES);
        super.onSaveInstanceState(outState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_movies, container, false);
        TextView categoryname = (TextView) view.findViewById(R.id.categorymoviesname);
        categoryname.setText(R.string.RecentMovies);
        TextView emptyview = (TextView) view.findViewById(R.id.empty_view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_movies);
        if (DataModel.DBMovies.mrecentMovies != null) {
            recyclerView.setVisibility(View.VISIBLE);
            emptyview.setVisibility(View.GONE);
            MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(getActivity(), RECENT_MOVIES);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(adapter);
        } else {
            if (DataModel.DBMovies_Offline.mrecentMovies_offlinedata != null) {
                recyclerView.setVisibility(View.VISIBLE);
                emptyview.setVisibility(View.GONE);
                Offlinedata_recyclerviewAdapter adapter = new Offlinedata_recyclerviewAdapter(getActivity(), DataModel.DBMovies_Offline.mrecentMovies_offlinedata);
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
