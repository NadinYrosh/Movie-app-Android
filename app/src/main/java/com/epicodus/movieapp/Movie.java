package com.epicodus.movieapp;

import android.util.Log;

public class Movie {
    private String mName;
    private String mPoster;
    private String mOverview;

    public Movie(String name, String poster, String overview) {
        this.mName = name;
        this.mPoster = poster;
        this.mOverview = overview;

        Log.d("url", poster);
    }

    public String getName() {
        return mName;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getOverview() {
        return mOverview;
    }
}
