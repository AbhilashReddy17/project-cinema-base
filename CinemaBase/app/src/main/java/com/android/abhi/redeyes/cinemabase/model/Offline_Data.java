package com.android.abhi.redeyes.cinemabase.model;

/**
 * Created by Abhilash Reddy on 6/5/2017.
 */

public class Offline_Data {
    String title;
    String poster_path;
    String Overview;

    public Offline_Data(String title, String image_path, String overview) {
        this.title = title;
        this.poster_path = image_path;
        Overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }
}
