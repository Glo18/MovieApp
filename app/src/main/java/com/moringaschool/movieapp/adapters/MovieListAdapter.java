package com.moringaschool.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.movieapp.Constants;
import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.ui.MovieDetailActivity;
import com.moringaschool.movieapp.ui.MovieDetailFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Result> mMovies;
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Result> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view, mMovies);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovies(mMovies.get(position));
    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.movie_name) TextView mMovie_name;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Result> mMovies = new ArrayList<>();

        public MovieViewHolder(@NonNull View itemView, ArrayList<Result> movies) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;

            mMovies = movies;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        private void createDetailFragment(int position) {
            MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(mMovies, position, " ");
            FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.movieDetailContainer, detailFragment);
            ft.commit();
        }

        public void bindMovies(Result result) {
            mMovie_name.setText(result.getOriginalTitle());
        }

//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();
//            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
//                createDetailFragment(itemPosition);
//            } else {
//                Intent intent = new Intent(mContext, MovieDetailActivity.class);
//                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
//                intent.putExtra(Constants.EXTRA_KEY_MOVIES, Parcels.wrap(mMovies));
//                mContext.startActivity(intent);
//            }
//        }
    }
}

