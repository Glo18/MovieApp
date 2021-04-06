package com.moringaschool.movieapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;

import java.net.HttpCookie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.FindMoviesButton)
    Button mFindMoviesButton;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;
    @BindView(R.id.saveMovieButton)
    Button mSaveMovieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindMoviesButton.setOnClickListener(this);
        mSaveMovieButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindMoviesButton) {
            Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
            startActivity(intent);
        }

        if (v == mSaveMovieButton) {
            Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
            startActivity(intent);
        }
    }
}
