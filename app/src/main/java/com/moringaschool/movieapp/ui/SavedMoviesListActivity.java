package com.moringaschool.movieapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.adapters.FirebaseMoviesViewHolder;
import com.moringaschool.movieapp.models.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMoviesListActivity extends AppCompatActivity {
    private DatabaseReference mMoviesReference;
    private FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mMoviesReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
        setUpFirebaseAdapter();
        hideProgressBar();
        showMovies();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Result> options =
                new FirebaseRecyclerOptions.Builder< >()
                .setQuery(mMoviesReference, Result.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseMoviesViewHolder holder, int position, @NonNull Result movies) {
                FirebaseMoviesViewHolder.bindMovies(movies);
            }

            @NonNull
            @Override
            public FirebaseMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_list_item, parent, false);
                return new FirebaseMoviesViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showMovies() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}