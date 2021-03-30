//package com.moringaschool.movieapp.adapters;

//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import com.moringaschool.movieapp.model.Result;
//import com.moringaschool.movieapp.ui.MovieDetailFragment;
//
//import java.util.List;
//
//public class MoviePageAdapter extends FragmentPagerAdapter {
//
//    private List<Result> mResults;
//
//    public MoviePageAdapter(FragmentManager fm, int behavior, List<Result> results) {
//        super(fm, behavior);
//        mResults = results;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return MovieDetailFragment.newInstance(mResults.get(position));
//    }
//
//    @Override
//    public int getCount() {
//        return mResults.size();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mResults.get(position).getTitle();
//    }
//}
