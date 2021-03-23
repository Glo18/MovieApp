package com.moringaschool.movieapp;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MoviesArrayAdapter extends ArrayAdapter {
    Context mContext ;
    String [] mMovies;
    String [] mGenres;

    public MoviesArrayAdapter(Context mContext, int resource, String[] movies, String[] genres) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMovies = movies;
        this.mGenres = genres;
    }

    @Override
    public Object getItem(int position) {
        String movie = mMovies[position];
        String genre = mGenres[position];
        return String.format("%s \n what a favorite: %s", movie, genre);
    }

    @Override
    public int getCount() {

        return mMovies.length;
    }
}
