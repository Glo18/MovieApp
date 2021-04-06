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

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private ValueEventListener mSearchedGenreReferenceListener;

    public DatabaseReference mSearchedGenreReference;

    @BindView(R.id.FindMoviesButton) Button mFindMoviesButton;
    @BindView(R.id.GenresEdit) EditText mGenresEdit;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.saveMovieButton) Button mSaveMovieButton;
//    private String mSearchedGenreReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mSearchedGenreReference = FirebaseDatabase
////                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_GENRE);

        mSearchedGenreReferenceListener = mSearchedGenreReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot genreSnapshot : dataSnapshot.getChildren()) {

                    String genre = genreSnapshot.getValue().toString();
                    Log.d("Genres updated", "genre" + genre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mSaveMovieButton.setOnClickListener(this);
        mFindMoviesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if (v == mFindMoviesButton) {
                String genre = mGenresEdit.getText().toString();

                saveGenreToFirebase(genre);

//                if (!(genres).equals("")) {
//                    addToSharedPreferences(genres);
//                }

                Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
//                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();

                if (v == mSaveMovieButton) {
                    Intent intent = new Intent(MainActivity.this, SavedMoviesListActivity.class);
                    startActivity(intent);
                }
            }

        public void saveGenreToFirebase(String genre) {
        mSearchedGenreReference.push().setValue(genre);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedGenreReference.removeEventListener(mSearchedGenreReferenceListener);
    }

    //    private void addToSharedPreferences(String genres) {
//        mEditor.putString(Constants.PREFERENCES_GENRES_KEY, genres).apply();
//    }
    }