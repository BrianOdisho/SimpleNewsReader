package org.brianodisho.newsreader.newsfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import org.brianodisho.newsreader.MainRouter;
import org.brianodisho.newsreader.NewsReaderApplication;
import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.newsfeed.NewsFeedArticleAdapter.NewsFeedArticleHolder;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedPresenter;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedView;

import java.util.List;

public class NewsFeedFragment extends MvpFragment<NewsFeedView, NewsFeedPresenter> implements NewsFeedView, NewsFeedArticleHolder.OnArticleClickListener {
    private static final String EXTRA_NEWS_FEED_CATEGORY = "EXTRA_NEWS_FEED_CATEGORY";

    private NewsFeedArticleAdapter adapter;


    public static NewsFeedFragment newInstance(String newsFeedCategory) {
        NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(EXTRA_NEWS_FEED_CATEGORY, newsFeedCategory);
        newsFeedFragment.setArguments(bundle);
        return newsFeedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NewsFeedArticleAdapter(getContext(), this);
        ((NewsReaderApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_news_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public NewsFeedPresenter createPresenter() {
        NewsFeedPresenterImpl presenter = new NewsFeedPresenterImpl(getArguments().getString(EXTRA_NEWS_FEED_CATEGORY), (MainRouter) getActivity());
        ((NewsReaderApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void setData(@NonNull List<NewsFeed.Article> data) {
        adapter.setData(data);
    }

    @Override
    public void onArticleClick(int position) {
        presenter.onArticleClicked(adapter.getItem(position));
    }
}
