package com.android.abhi.redeyes.cinemabase.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.FragmentHolder;


/**
 * Created by Abhilash Reddy on 5/31/2017.
 */

public class MoviesFragment extends Fragment{

    static MoviesFragment mnowplayingmovies;
    FragmentManager manager;
    SingleInstaceMovieFragment movieFragment;
    FragmentHolder mfragmentHolder;

    CardView mrecentmovies,mpopularmovies,mupcomingmovies,mtopratedmovies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies,container,false);

        //configuring required views
        configureViews(view);

        mrecentmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(SingleInstaceMovieFragment.RECENT_MOVIES);

            }
        });

        mpopularmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(SingleInstaceMovieFragment.POPULAR_MOVIES);            }
        });

        mupcomingmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(SingleInstaceMovieFragment.UPCOMING_MOVIES);
            }
        });

        mtopratedmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(SingleInstaceMovieFragment.TOPRATED_MOVIES);
            }
        });

        return view;
    }

    private void showFragment(int fragment) {
        Intent intent = new Intent(getActivity(),FragmentHolder.class);
        intent.putExtra("fragment",fragment);
        startActivity(intent);
    }

    private void configureViews(View view) {
        mrecentmovies = (CardView) view.findViewById(R.id.cardview_RecentMovies);
        mpopularmovies = (CardView) view.findViewById(R.id.cardview_PopularMovies);
        mupcomingmovies = (CardView) view.findViewById(R.id.cardview_UpComingMovies);
        mtopratedmovies = (CardView) view.findViewById(R.id.cardview_TopRatedMovies);
        manager = getFragmentManager();
        movieFragment = new SingleInstaceMovieFragment();
        mfragmentHolder = new FragmentHolder();
    }

    public MoviesFragment singleInstanceFragment(){
    if(mnowplayingmovies==null)
        mnowplayingmovies = new MoviesFragment();
    return mnowplayingmovies;
}
}
