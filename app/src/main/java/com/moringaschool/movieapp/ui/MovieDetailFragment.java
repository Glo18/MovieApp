package com.moringaschool.movieapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.movieImageView) ImageView mImageLabel;
    @BindView(R.id.movieNameTextView) TextView mNameLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.saveMovieButton) TextView mSaveMovieButton;

    private Result mMovies;

    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(Result genres) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movies", Parcels.wrap(movies));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mMovies = Parcels.unwrap(getArguments().getParcelable("restaurant"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mMovies.getImageUrl()).into(mImageLabel);

        List<String> genres = new ArrayList<>();

        for (Genres genres: mMovies.getGenreIds()) {
            genres.add(genres.getTitle());
        }

        mNameLabel.setText(mMovies.getName());
        mGenresLabel.setText(android.text.TextUtils.join(", ", genres));
        mRatingLabel.setText(Double.toString(mMovies.getRating()) + "/5");
        mPhoneLabel.setText(mMovies.getPhone());

        mPhoneLabel.setOnClickListener(this);

        mSaveMovieButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveMovieButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference movieRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MOVIES);

            DatabaseReference pushRef = movieRef.push();
            String pushId = pushRef.getKey();
            mMovies.setPushId(pushId);
            pushRef.setValue(mMovies);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}