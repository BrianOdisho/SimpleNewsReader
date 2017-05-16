package org.brianodisho.newsreader.latestnews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import org.brianodisho.newsreader.NewsReaderApplication;
import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.SourcesResponse;

import java.util.List;

/**
 * Implementation of the NewsView
 */
public class NewsFragment extends MvpFragment<NewsView, NewsPresenter> implements NewsView {
    private static final String EXTRA_NEWS_SOURCE_CATEGORY = "EXTRA_NEWS_SOURCE_CATEGORY";

    private NewsPagerAdapter pagerAdapter;


    public static NewsFragment newInstance(String newsCategory) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle(1);
        args.putString(EXTRA_NEWS_SOURCE_CATEGORY, newsCategory);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        pagerAdapter = new NewsPagerAdapter(getChildFragmentManager());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_news);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_news);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @NonNull
    @Override
    public NewsPresenter createPresenter() {
        NewsPresenterImpl presenter = new NewsPresenterImpl(getArguments().getString(EXTRA_NEWS_SOURCE_CATEGORY));
        ((NewsReaderApplication) getActivity().getApplication()).getNetworkComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void setData(List<SourcesResponse.Source> data) {
        pagerAdapter.setData(data);
    }
}
