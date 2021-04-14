package com.moringaschool.movieapp.util;

import com.moringaschool.movieapp.models.Result;

import java.util.ArrayList;

public interface OnMovieSelectedListener {
    public void onMovieSelected(Integer position, ArrayList<Result> movies, String source);

    void onMovieSelected(int itemPosition, ArrayList<Result> mMovies);
}
