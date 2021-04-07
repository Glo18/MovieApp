package com.moringaschool.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
>>>>>>> week3
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

<<<<<<< HEAD
    @BindView(R.id.FindMoviesButton)
    Button mFindMoviesButton;
    @BindView(R.id.GenresEdit)
    EditText mGenresEdit;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;
=======
    @BindView(R.id.FindMoviesButton) Button mFindMoviesButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.saveMovieButton) Button mSaveMovieButton;
>>>>>>> week3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

<<<<<<< HEAD
=======
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {
                    //display welcome message
                }
            }
        };
>>>>>>> week3

        mFindMoviesButton.setOnClickListener(this);
        mSaveMovieButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
<<<<<<< HEAD
            if (v == mFindMoviesButton) {
                String genres = mGenresEdit.getText().toString();
                if (!(genres).equals("")) {
                }
=======
        if (v == mFindMoviesButton) {
            Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
            startActivity(intent);
        }
>>>>>>> week3

        if (v == mSaveMovieButton) {
            Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

<<<<<<< HEAD
    }
=======
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
>>>>>>> week3
