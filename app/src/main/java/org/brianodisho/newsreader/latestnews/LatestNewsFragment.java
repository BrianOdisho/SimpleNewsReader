package org.brianodisho.newsreader.latestnews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import org.brianodisho.newsreader.R;

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
        ((ViewPager) view.findViewById(R.id.viewpager)).setAdapter(pagerAdapter);

        return view;
    }

    @NonNull
    @Override
    public LatestNewsPresenter createPresenter() {
        return new LatestNewsPresenterImpl();
    }

    @Override
    public void setData(List<String> data) {
        pagerAdapter.setData(data);
    }
}
