package com.android.abhi.redeyes.cinemabase.model;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Created by Abhilash Reddy on 6/2/2017.
 */

public class DataModel {
    public static class DBMovies {
        public static List<MovieDb> mpopularMovies;
        public static List<MovieDb> mtopratedmovies;
        public static List<MovieDb> mupcomingMovies;
        public static List<MovieDb> mrecentMovies;

    }

    public static class TVShows {
        public static List<TvSeries> mpopularTvShows;
        public static List<TvSeries> mtopRatedTvShows;
    }

    public static class DBMovies_Offline {
        public static List<Offline_Data> mpopularMovies_offlinedata;
        public static List<Offline_Data> mtopratedmovies_offlinedata;
        public static List<Offline_Data> mupcomingMovies_offlinedata;
        public static List<Offline_Data> mrecentMovies_offlinedata;

    }

    public static class TVShows_Offline {
        public static List<Offline_Data> mpopularTvShows_offlinedata;
        public static List<Offline_Data> mtopRatedTvShows_offlinedata;
    }
}
