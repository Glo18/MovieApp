package com.moringaschool.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.adapters.MovieListAdapter;
import com.moringaschool.movieapp.models.MovieListResponse;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.network.MoviedbApi;
import com.moringaschool.movieapp.network.MoviedbClient;
import com.moringaschool.movieapp.util.OnMovieSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListActivity extends AppCompatActivity implements OnMovieSelectedListener {
    private Integer mPosition;
    ArrayList<Result> mMovies;
    String mSource;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentGenres;
//
//    public static final String TAG = MoviesListActivity.class.getSimpleName();
//    private MovieListAdapter mAdapter;
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//    public List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        if (savedInstanceState != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mMovies = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_MOVIES));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mMovies != null) {
                    Intent intent = new Intent(this, MovieDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mMovies != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }
    @Override
    public void onMovieSelected(Integer position, ArrayList<Result> movies, String source) {
        mPosition = position;
        mMovies = movies;
        mSource = source;
    }

    @Override
    public void onMovieSelected(int itemPosition, ArrayList<Result> mMovies) {

    }
}
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentGenres = mSharedPreferences.getString(Constants.PREFERENCES_GENRES_KEY, null);
//        if (mRecentGenres != null) {
//            fetchMovies(mRecentGenres);

//        @Override
//        public boolean onCreateOptionsMenu (Menu menu){
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_search, menu);
//            ButterKnife.bind(this);
//
//            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            mEditor = mSharedPreferences.edit();
//
//            MenuItem menuItem = menu.findItem(R.id.action_search);
//            SearchView searchView = (SearchView) menuItem.getActionView();
//
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String genres) {
//                    addToSharedPreferences(genres);
//                    fetchMovies(genres);
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String genres) {
//                    return false;
//                }
//            });
//
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected (MenuItem item){
//            return super.onOptionsItemSelected(item);
//        }
//
//
//        private void showFailureMessage () {
//            mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//            mErrorTextView.setVisibility(View.VISIBLE);
//        }
//
//        private void showUnsuccessfulMessage () {
//            mErrorTextView.setText("Something went wrong. Please try again later");
//            mErrorTextView.setVisibility(View.VISIBLE);
//        }
//
//        private void showMovies () {
//            mRecyclerView.setVisibility(View.VISIBLE);
//        }
//
//        private void hideProgressBar () {
//            mProgressBar.setVisibility(View.GONE);
//        }
//
//        private void addToSharedPreferences (String genres){
//            mEditor.putString(Constants.PREFERENCES_GENRES_KEY, genres).apply();
//        }
//
//    private void fetchMovies(String genres){
//        MoviedbApi moviedbApi = MoviedbClient.getClient();
//        retrofit2.Call<MovieListResponse> call = moviedbApi.getMovies("34873bd5e098d4b5e303a13ccac6a12d", "en-US", "popularity.desc", "true", "false", 1);
//        call.enqueue(new retrofit2.Callback<MovieListResponse>() {
//            @Override
//            public void onResponse(retrofit2.Call<MovieListResponse> call, retrofit2.Response<MovieListResponse> response) {
//                hideProgressBar();
////                Log.d("On Response", "Got Response");
//                if (response.isSuccessful()) {
//                    results = response.body().getResults();
//                    mAdapter = new MovieListAdapter(MoviesListActivity.this, results);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MoviesListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//                    showMovies();
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieListResponse> call, Throwable t) {
//                Log.e(TAG, "onFailure:", t);
//                hideProgressBar();
//                showFailureMessage();
//            }
//        });
//    }
//    }
