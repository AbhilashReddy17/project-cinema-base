package com.android.abhi.redeyes.cinemabase.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment;

import static com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment.POPULAR_MOVIES;
import static com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment.RECENT_MOVIES;
import static com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment.TOPRATED_MOVIES;
import static com.android.abhi.redeyes.cinemabase.UI.fragment.SingleInstaceMovieFragment.UPCOMING_MOVIES;

public class FragmentHolder extends AppCompatActivity {

    private static final String TAG = FragmentHolder.class.getSimpleName();
    SingleInstaceMovieFragment movieFragment;
    Fragment fragment;
    ImageView selectCategory;
    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        selectCategory = (ImageView) findViewById(R.id.selectCategory);
        dialog = new AlertDialog.Builder(this);
        movieFragment = new SingleInstaceMovieFragment();
        int whichfragment = getIntent().getIntExtra("fragment",1);

        selectFragment(whichfragment);


        selectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] categorys = {"Recent Movies","Popular movies","Upcoming Movies","Top Rated Movies"};
                dialog.setItems(categorys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case 0:
                                selectFragment(which+1);
                                break;
                            case 1:
                                selectFragment(which+1);
                                break;
                            case 2:
                                selectFragment(which+1);
                                break;
                            case 3:
                                selectFragment(which+1);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });



    }

    private void selectFragment(int whichfragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (whichfragment){
            case RECENT_MOVIES:
                Log.d(TAG, "onCreate: "+whichfragment);
                fragment = null;
               fragment= movieFragment.singleInstaceFragment(RECENT_MOVIES);
                transaction.replace(R.id.moviefragment_container,fragment,"recentmovies");
                transaction.commit();
                break;
            case UPCOMING_MOVIES:
                fragment = null;
                fragment= movieFragment.singleInstaceFragment(UPCOMING_MOVIES);
                transaction.replace(R.id.moviefragment_container,fragment,"upcomingmovies");
                transaction.commit();
                break;
            case POPULAR_MOVIES:
                fragment = null;
                fragment = movieFragment.singleInstaceFragment(POPULAR_MOVIES);
                transaction.replace(R.id.moviefragment_container,fragment,"popularmovies");
                transaction.commit();
                break;
            case TOPRATED_MOVIES:
                fragment = null;
                fragment = movieFragment.singleInstaceFragment(TOPRATED_MOVIES);
                transaction.replace(R.id.moviefragment_container,fragment,"topratedmovies");
                transaction.commit();
                break;
        }
    }
}
