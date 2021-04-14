package com.moringaschool.movieapp;

import com.moringaschool.movieapp.BuildConfig;

public class Constants {
    public static final String TMDb_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String TMDb_API_KEY = BuildConfig.TMDb_API_KEY;
    public static final String FIREBASE_CHILD_MOVIES = "movies";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_SEARCHED_GENRE = "searchedGenre";
    public static String PREFERENCES_GENRES_KEY;
    public static String PREFERENCES_LOCATION_KEY;
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_MOVIES = "movies";
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}