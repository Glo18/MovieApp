package com.moringaschool.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;

public class MovieDetailFragment extends AppCompatActivity {

    public static Fragment newInstance(Result genres) {

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_fragment);
    }
}