package com.android.abhi.redeyes.cinemabase.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.abhi.redeyes.cinemabase.R;
import com.android.abhi.redeyes.cinemabase.model.DataModel;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;

import static android.R.attr.start;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.IMAGE_URL;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.POPULAR_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.RECENT_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.TOPRATED_MOVIES;
import static com.android.abhi.redeyes.cinemabase.model.CinemaBaseContract.Movies.UPCOMING_MOVIES;

/**
 * Created by Abhilash Reddy on 6/2/2017.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.HolderView> {
public static final String TAG ="moviesadapter";
    Context context;
    int from;
    List<MovieDb> movies;

    Dialog dialog;
   public MoviesRecyclerViewAdapter(Context context,int from){
       this.context = context;
       this.from = from;
       dialog = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_poster,parent,false);
        HolderView holder = new HolderView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {

        switch (from){
            case POPULAR_MOVIES :
                movies = DataModel.DBMovies.mpopularMovies;
                break;
            case RECENT_MOVIES:
                movies = DataModel.DBMovies.mrecentMovies;
                break;
            case UPCOMING_MOVIES:
                movies = DataModel.DBMovies.mupcomingMovies;
                break;
            case TOPRATED_MOVIES:
                movies = DataModel.DBMovies.mtopratedmovies;
                break;
        }

            String path = movies.get(position).getPosterPath();

            Log.d(TAG, "onBindViewHolder: " + path + "\n");
            Glide.with(context).load(IMAGE_URL + path).into(holder.moviewPoster);

            //clicking the poster showing the movie details
            holder.moviewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//                    dialog.setContentView(R.layout.recyclerview_item_movie_description);
//                    dialog.setTitle("");
//
//
//                    TextView title = (TextView) dialog.findViewById(R.id.movie_title);
//                    ImageView poster = (ImageView) dialog.findViewById(R.id.movie_full_poster);
//                    TextView description = (TextView) dialog.findViewById(R.id.movie_description);
//
//                    String path = movies.get(position).getPosterPath();
//                    title.setText(movies.get(position).getOriginalTitle());
//                    Glide.with(context).load(IMAGE_URL+ path).into(poster);
//                    description.setText(movies.get(position).getOverview());
//
//                    dialog.show();

                    Intent intent = new Intent(context,ShowDetails.class);
                    intent.putExtra("movies", movies.get(position));
                    context.startActivity(intent);

                }
            });
    }

    @Override
    public int getItemCount() {
        return DataModel.DBMovies.mpopularMovies.size();

    }

    public class HolderView extends RecyclerView.ViewHolder{
        ImageView moviewPoster;
        public HolderView(View itemView) {
            super(itemView);
moviewPoster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }

}
