package com.moringaschool.movieapp.network;

import com.moringaschool.movieapp.models.YelpMoviesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("businesses/search")
    Call<YelpMoviesSearchResponse> getRestaurants(
            @Query("location") String location,
            @Query("term") String term
    );
}
