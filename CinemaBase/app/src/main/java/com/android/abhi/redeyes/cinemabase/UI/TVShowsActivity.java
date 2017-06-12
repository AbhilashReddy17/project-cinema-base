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

import static com.android.abhi.redeyes.cinemabase.R.id.selectCategory;

public class TVShowsActivity extends AppCompatActivity {
    ImageView selectCategory;
    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);

        configureViews();

        ViewPager pager = (ViewPager) findViewById(R.id.tvshowsactivity_viewpager);
        FragmentManager manager = getSupportFragmentManager();
        TVShowsFragmentAdapter adapter = new TVShowsFragmentAdapter(manager,this);
        pager.setAdapter(adapter);

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

                                Intent intentmovies = new Intent(TVShowsActivity.this,MoviesActivity.class);
                                intentmovies.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intentmovies.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentmovies);
                                break;
                            case 1:

//                                Intent intenttvshows = new Intent(TVShowsActivity.this,TVShowsActivity.class);
//                                startActivity(intenttvshows);
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
