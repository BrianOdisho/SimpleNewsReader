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
 * Implementation of the LatestNewsView
 */
public class LatestNewsFragment extends MvpFragment<LatestNewsView, LatestNewsPresenter> implements LatestNewsView {

    private LatestNewsPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_news, container, false);

        pagerAdapter = new LatestNewsPagerAdapter(getChildFragmentManager());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.latest_news_viewPager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.latest_news_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @NonNull
    @Override
    public LatestNewsPresenter createPresenter() {
        LatestNewsPresenterImpl presenter = new LatestNewsPresenterImpl();
        ((NewsReaderApplication) getActivity().getApplication()).getNetworkComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void setData(List<SourcesResponse.Source> data) {
        pagerAdapter.setData(data);
    }
}
