package com.moringaschool.movieapp.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.movieapp.R;
import com.moringaschool.movieapp.models.Result;
import com.moringaschool.movieapp.ui.MovieDetailFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private List<Result> mResults;
    private Context mContext;

    public MovieListAdapter(Context context, List<Result> results) {
        mContext = context;
        mResults = results;
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movies_list, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovieResults(mResults.get(position));
    }

    @Override
    public int getItemCount() {

        return mResults.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_name) TextView mMovie_name;

        private Context mContext;
        private int mOrientation;
        private void createDetailFragment(int position) {
            MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(mMoviies, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.movieName, detailFragment);
            ft.commit();
        }

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        public void bindMovieResults(Result result) {
            mMovie_name.setText(result.getOriginalTitle());
        }
    }
}

