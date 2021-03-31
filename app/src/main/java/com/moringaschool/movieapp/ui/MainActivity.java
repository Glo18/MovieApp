package com.moringaschool.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.FindMoviesButton)
    Button mFindMoviesButton;
    @BindView(R.id.GenresEdit)
    EditText mGenresEdit;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mFindMoviesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if (v == mFindMoviesButton) {
                String genres = mGenresEdit.getText().toString();
                if (!(genres).equals("")) {
                }

                Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        }

    }