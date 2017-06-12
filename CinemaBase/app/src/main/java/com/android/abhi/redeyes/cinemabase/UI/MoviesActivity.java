package com.android.abhi.redeyes.cinemabase.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.UI.fragments.SingleInstaceMovieFragment;
import com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract;

import static com.android.abhi.redeyes.cinemabase.R.id.selectCategory;

public class MoviesActivity extends AppCompatActivity {
    ImageView selectCategory;
    AlertDialog.Builder dialog;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
      // mpresentFragment = savedInstanceState.getInt("state");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        configureViews();
        ViewPager pager = (ViewPager) findViewById(R.id.moviesctivity_viewpager);
        FragmentManager manager = getSupportFragmentManager();
        MoviesFragmentAdapter adapter = new MoviesFragmentAdapter(manager,this);
        pager.setAdapter(adapter);
        //SingleInstaceMovieFragment moviesfragment = new SingleInstaceMovieFragment();

       //getSupportFragmentManager().beginTransaction().add(R.id.moviesactivity,
             //  moviesfragment.singleInstaceFragment(CinemaBaseContract.Movies.MOVIES),"movies").commit();




        //implementing the onclick listners for the respective categorys
        selectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] categorys = {"Movies", "TV Shows"};
                dialog.setItems(categorys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:

//                                Intent intentmovies = new Intent(MoviesActivity.this,MoviesActivity.class);
//                                startActivity(intentmovies);
                                break;
                            case 1:

                                Intent intenttvshows = new Intent(MoviesActivity.this,TVShowsActivity.class);
                                intenttvshows.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intenttvshows.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intenttvshows);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    private void configureViews() {
        selectCategory = (ImageView) findViewById(R.id.selectCategory);
        dialog = new AlertDialog.Builder(this);
    }
}
