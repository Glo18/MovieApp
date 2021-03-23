package com.moringaschool.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MoviesActivity extends AppCompatActivity {

    private String[] movies = new String[]{
            "Insidious", "Equalizer",
            "War Room", "Bay Watch", "Lucy", "Love and Lust",
            "The mechanic resurrection", "Transporter"
    };

    private String[] genres = new String[]{
            "Fantasy", "War", "Romance",
            "Action", "Horror", "Drama", "Comedy", "Thriller"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        MoviesArrayAdapter adapter = new MoviesArrayAdapter(this, android.R.layout.simple_list_item_1, movies, genres);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movies = ((TextView) view).getText().toString();
                Toast.makeText(MoviesActivity.this, movies, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String genres = intent.getStringExtra("genres");
        mGenreTextView.setText("Available genres: " + genres);
    }
}