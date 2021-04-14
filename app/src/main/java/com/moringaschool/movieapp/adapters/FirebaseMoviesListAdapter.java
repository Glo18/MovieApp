package com.moringaschool.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.ui.MovieDetailActivity;
import com.moringaschool.movieapp.ui.MovieDetailFragment;
import com.moringaschool.movieapp.util.ItemTouchHelperAdapter;
import com.moringaschool.movieapp.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseMoviesListAdapter extends FirebaseRecyclerAdapter<Result, FirebaseMoviesViewHolder> implements ItemTouchHelperAdapter {
    private Query mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Result> mMovies = new ArrayList<>();

    public FirebaseMoviesListAdapter(FirebaseRecyclerOptions<Result> options,
                                     Query ref,
                                     OnStartDragListener onStartDragListener,
                                     Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mMovies.add(dataSnapshot.getValue(Result.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseMoviesViewHolder firebaseMoviesViewHolder, int position, @NonNull Result movies) {
        firebaseMoviesViewHolder.bindMovies(movies);

        mOrientation = firebaseMoviesViewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

    firebaseMoviesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = firebaseMoviesViewHolder.getAdapterPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
                mContext.startActivity(intent);
            }
        }
    });
    }

    private void createDetailFragment(int position) {
        MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(mMovies, position, Constants.SOURCE_SAVED);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.movieDetailContainer, detailFragment);
        ft.commit();
    }

    @NonNull
    @Override
    public FirebaseMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item_drag, parent, false);
        return new FirebaseMoviesViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mMovies, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mMovies.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Result result : mMovies) {
            int index = mMovies.indexOf(result);
            DatabaseReference ref = getRef(index);
            result.setIndex(Integer.toString(index));
            ref.setValue(result);
        }
    }

    @Override
    public void stopListening() {
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }
}
