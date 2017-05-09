package org.brianodisho.newsreader.articlefeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import org.brianodisho.newsreader.NewsReaderApplication;
import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.ArticlesResponse;

import java.util.List;

public class ArticleFeedFragment extends MvpFragment<ArticleFeedView, ArticleFeedPresenter> implements ArticleFeedView {
    private static final String EXTRA_ARTICLE_FEED_SOURCE = "EXTRA_ARTICLE_FEED_SOURCE";

    private ArticleFeedAdapter adapter;

    public static ArticleFeedFragment newInstance(String articleFeedSource) {
        ArticleFeedFragment articleFeedFragment = new ArticleFeedFragment();
        Bundle args = new Bundle(1);
        args.putString(EXTRA_ARTICLE_FEED_SOURCE, articleFeedSource);
        articleFeedFragment.setArguments(args);
        return articleFeedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArticleFeedAdapter(getContext());
        ((NewsReaderApplication) getActivity().getApplication()).getNetworkComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_article_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public ArticleFeedPresenter createPresenter() {
        ArticleFeedPresenterImpl presenter = new ArticleFeedPresenterImpl(getArguments().getString(EXTRA_ARTICLE_FEED_SOURCE));
        ((NewsReaderApplication) getActivity().getApplication()).getNetworkComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void setData(@NonNull List<ArticlesResponse.Article> data) {
        adapter.setData(data);
    }
}
