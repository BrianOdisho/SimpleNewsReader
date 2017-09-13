package org.brianodisho.newsreader.latestnews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.newsfeed.NewsFeedFragment;
import org.brianodisho.newsreader.latestnews.LatestNewsContract.LatestNewsPresenter;
import org.brianodisho.newsreader.latestnews.LatestNewsContract.LatestNewsView;

/**
 * Implementation of the LatestNewsView
 */
public class LatestNewsFragment extends MvpFragment<LatestNewsView, LatestNewsPresenter> implements LatestNewsView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_news, container, false);

        LatestNewsPagerAdapter pagerAdapter = new LatestNewsPagerAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.latest_news_categories_array));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_latest_news);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_latest_news);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @NonNull
    @Override
    public LatestNewsPresenter createPresenter() {
        return new LatestNewsPresenterImpl();
    }


    private static class LatestNewsPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] _data;


        LatestNewsPagerAdapter(FragmentManager fragmentManager, String[] data) {
            super(fragmentManager);
            _data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return NewsFeedFragment.newInstance(_data[position]);
        }

        @Override
        public int getCount() {
            return _data.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return _data[position];
        }
    }
}
