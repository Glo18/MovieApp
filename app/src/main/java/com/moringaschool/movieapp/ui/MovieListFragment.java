package com.moringaschool.movieapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentAddress !=null) {
            getMovies(mRecentAddress);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

//                Log.e(TAG, "onFailure:", t);
//                hideProgressBar();
//                showFailureMessage();


}