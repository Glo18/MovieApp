package com.moringaschool.movieapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;

import java.net.HttpCookie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @BindView(R.id.FindMoviesButton)
    Button mFindMoviesButton;
    @BindView(R.id.GenresEdit)
    EditText mGenresEdit;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;
    private String mSearchedGenreReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
////                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindMoviesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if (v == mFindMoviesButton) {
                String genres = mGenresEdit.getText().toString();
                
                saveGenresToFirebase(genres);
                
//                if (!(genres).equals("")) {
//                    addToSharedPreferences(genres);
//                }

                Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        }
        public void saveGenresToFirebase(String genres) {
    }
    
    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }

    //    private void addToSharedPreferences(String genres) {
//        mEditor.putString(Constants.PREFERENCES_GENRES_KEY, genres).apply();
//    }
    }