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
import com.android.abhi.redeyes.cinemabase.model.DataModel;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.UPCOMING_MOVIES;


/**
 * Created by Abhilash Reddy on 5/31/2017.
 */

public class UpcomingMoviesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_movies,container,false);
        TextView emptyview = (TextView) view.findViewById(R.id.empty_view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_movies);
        if(DataModel.DBMovies.mupcomingMovies != null) {
            recyclerView.setVisibility(View.VISIBLE);
            emptyview.setVisibility(View.GONE);
            MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(getActivity(), UPCOMING_MOVIES);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(adapter);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            emptyview.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
