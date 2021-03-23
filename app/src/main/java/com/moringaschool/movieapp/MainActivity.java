package com.moringaschool.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.bind;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.FindMoviesButton) Button mFindMoviesButton;
    @BindView(R.id.GenresEdit) EditText mGenresEdit;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mFindMoviesButton.setOnClickListener((View.OnClickListener) this);
    }
}