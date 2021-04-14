package com.moringaschool.movieapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.adapters.MovieListAdapter;
import com.moringaschool.movieapp.models.MovieListResponse;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.network.MoviedbApi;
import com.moringaschool.movieapp.network.MoviedbClient;
import com.moringaschool.movieapp.util.OnMovieSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private MovieListAdapter mAdapter;
    public ArrayList<Result> mMovies = new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    private OnMovieSelectedListener mOnMovieSelectedListener;
    private SharedPreferences.Editor mEditor;

    private void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnMovieSelectedListener = (OnMovieSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    public MovieListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentAddress !=null) {
            getMovies(mRecentAddress);
        }
        return view;
    }

    public void getMovies(String genres) {
        MoviedbApi moviedbApi = MoviedbClient.getClient();
        retrofit2.Call<MovieListResponse> call = moviedbApi.getMovies("34873bd5e098d4b5e303a13ccac6a12d", "en-US", "popularity.desc", "true", "false", 1);
        call.enqueue(new retrofit2.Callback<MovieListResponse>() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call  call, Response response) {
                mMovies = moviedbApi.pr
                    @Override
                    public void run() {
                    mAdapter = new MovieListAdapter(getActivity(), mMovies);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                }
            });
                     }
    });
}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                addToSharedPreferences(s);
                getMovies(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String genres) {
        mEditor.putString(Constants.PREFERENCES_GENRES_KEY, genres).apply();
    }
}