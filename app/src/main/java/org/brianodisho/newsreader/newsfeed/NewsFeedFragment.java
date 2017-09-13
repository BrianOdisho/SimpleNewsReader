package org.brianodisho.newsreader.newsfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;

import org.brianodisho.newsreader.MainRouter;
import org.brianodisho.newsreader.NewsReaderApplication;
import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.newsfeed.NewsFeedArticleAdapter.NewsFeedArticleHolder;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedPresenter;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedView;

import java.util.List;

/**
 * Implementation of the NewsFeedView
 */
public class NewsFeedFragment extends MvpLceFragment<SwipeRefreshLayout, List<NewsFeed.Article>, NewsFeedView, NewsFeedPresenter>
        implements NewsFeedView, NewsFeedArticleHolder.OnArticleClickListener, SwipeRefreshLayout.OnRefreshListener {

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
        contentView.setOnRefreshListener(this);

        adapter = new NewsFeedArticleAdapter(getContext(), this);
        ((NewsReaderApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        loadData(false);
    }

    @NonNull
    @Override
    public NewsFeedPresenter createPresenter() {
        NewsFeedPresenterImpl presenter = new NewsFeedPresenterImpl(getArguments().getString(EXTRA_NEWS_FEED_CATEGORY), (MainRouter) getActivity());
        ((NewsReaderApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void onArticleClick(int position) {
        presenter.onArticleClicked(adapter.getItem(position));
    }

    @Override
    public void onShareClick(int position) {
        presenter.onShareClicked(adapter.getItem(position));
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void setData(@NonNull List<NewsFeed.Article> data) {
        adapter.setData(data);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (pullToRefresh) {
            return getString(R.string.error_news_feed);
        } else {
            return getString(R.string.error_news_feed_retry);
        }
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }
}
