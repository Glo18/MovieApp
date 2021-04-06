package com.moringaschool.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.adapters.MovieListAdapter;
import com.moringaschool.movieapp.models.MovieListResponse;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.network.MoviedbApi;
import com.moringaschool.movieapp.network.MoviedbClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class MoviesListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    public static final String TAG = MoviesListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private MovieListAdapter mAdapter;
    public List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        Log.d("Shared Pref Location", mRecentAddress);

        String location = mRecentAddress;
    }

//        MoviedbApi moviedbApi = MoviedbClient.getClient();
//
//
//        retrofit2.Call<MovieListResponse> call = moviedbApi.getMovies("34873bd5e098d4b5e303a13ccac6a12d", "en-US", "popularity.desc", "true", "false", 1);
//
//        call.enqueue(new retrofit2.Callback <MovieListResponse>() {
//
//
//            public void onResponse(retrofit2.Call <MovieListResponse> call, retrofit2.Response <MovieListResponse> response) {
//                Log.d("On Response", "Got Response");
//                if(response.isSuccessful()) {
//                    hideProgressBar();
//                    results = response.body().getResults();
//                    mAdapter = new MovieListAdapter(MoviesListActivity.this, results);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//                    showResults();
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }

//            @Override
//            public void onFailure(Call<MovieListResponse> call, Throwable t) {
//                Log.e(TAG, "onFailure:", t);
//                hideProgressBar();
//                showFailureMessage();
//            }
//
//
//
//
//        });
//
//    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showResults() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}