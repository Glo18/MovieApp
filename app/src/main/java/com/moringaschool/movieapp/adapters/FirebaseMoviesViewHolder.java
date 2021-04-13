package com.moringaschool.movieapp.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.ui.MovieDetailActivity;
import com.moringaschool.movieapp.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMoviesViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseMoviesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindMovies(Result movies) {
        TextView titleTextView = (TextView) mView.findViewById(R.id.titleTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView overviewTextView = (TextView) mView.findViewById(R.id.overviewTextView);


        titleTextView.setText(movies.getTitle());
        ratingTextView.setText("Rating: " + movies.getRating() + "/5");
        overviewTextView.setText(movies.getOverview());

    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
