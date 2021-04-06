package com.moringaschool.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseMoviesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMovies(Result movies) {
        ImageView movieImageView = (ImageView) mView.findViewById(R.id.movieImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.MovieNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.get().load(movies.getImageUrl()).into(movieImageView);

        nameTextView.setText(movies.getName());
        categoryTextView.setText(movies.getCategories().get(0).getTitle());
        ratingTextView.setText("Rating: " + movies.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Result> movies = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    movies.add(snapshot.getValue(Result.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("movies", Parcels.wrap(movies));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
