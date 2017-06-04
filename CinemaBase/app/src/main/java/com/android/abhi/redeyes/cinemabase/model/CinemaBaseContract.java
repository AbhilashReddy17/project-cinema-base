package com.android.abhi.redeyes.cinemabase.model;

/**
 * Created by Abhilash Reddy on 6/3/2017.
 */

public class CinemaBaseContract {


    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";


    public static class Movies{
        public static final int MOVIES =0;
        public static final int RECENT_MOVIES =1;
        public static final int POPULAR_MOVIES =2;
        public static final int UPCOMING_MOVIES =3;
        public static final int TOPRATED_MOVIES =4;


    }
    public static class TVShows{
        public static final int TOPRATEDTV_SHOWS =5;
        public static final int POPULARTV_SHOWS =6;
        public static final int TV_SHOWS =7;

    }
}
