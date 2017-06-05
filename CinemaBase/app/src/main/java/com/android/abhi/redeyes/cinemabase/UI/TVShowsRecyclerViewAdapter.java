package com.android.abhi.redeyes.cinemabase.UI;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.model.DataModel;
import com.bumptech.glide.Glide;

import java.util.List;

import info.movito.themoviedbapi.model.tv.TvSeries;

import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.IMAGE_URL;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.POPULARTV_SHOWS;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.TVShows.TOPRATEDTV_SHOWS;

/**
 * Created by Abhilash Reddy on 6/4/2017.
 */

public class TVShowsRecyclerViewAdapter extends RecyclerView.Adapter<TVShowsRecyclerViewAdapter.TvShowHolder> {

    private static final String TAG = TVShowsRecyclerViewAdapter.class.getSimpleName();
    Context context;
    int from;
    List<TvSeries> series;

    public TVShowsRecyclerViewAdapter(Context context, int from) {
        this.context = context;
        this.from = from;
    }

    @Override
    public TvShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_poster, parent, false);
        TvShowHolder holder = new TvShowHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TvShowHolder holder, final int position) {

        switch (from) {
            case POPULARTV_SHOWS:
                series = DataModel.TVShows.mpopularTvShows;
                break;
            case TOPRATEDTV_SHOWS:
                series = DataModel.TVShows.mtopRatedTvShows;
                break;
            default:
                series = DataModel.TVShows.mpopularTvShows;
                break;
        }

        String posterpath = series.get(position).getPosterPath();
        Glide.with(context).load(IMAGE_URL + posterpath).into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.recyclerview_tvshows_item_description);
                dialog.setTitle("");


                TextView title = (TextView) dialog.findViewById(R.id.tvseries_title);
                ImageView poster = (ImageView) dialog.findViewById(R.id.tvseries_full_poster);
                TextView description = (TextView) dialog.findViewById(R.id.tvseries_description);

                String path = series.get(position).getPosterPath();
                title.setText(series.get(position).getOriginalName());
                Glide.with(context).load(IMAGE_URL + path).into(poster);
                description.setText(series.get(position).getOverview());

                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        switch (from) {
            case POPULARTV_SHOWS:
                return DataModel.TVShows.mpopularTvShows.size();
            case TOPRATEDTV_SHOWS:
                return DataModel.TVShows.mtopRatedTvShows.size();
            default:
                return DataModel.TVShows.mpopularTvShows.size();
        }
    }

    //view holder

    public class TvShowHolder extends RecyclerView.ViewHolder {

        ImageView poster;

        public TvShowHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);

        }
    }
}
