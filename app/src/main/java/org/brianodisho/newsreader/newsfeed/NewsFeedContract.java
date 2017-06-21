package org.brianodisho.newsreader.newsfeed;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.newsreader.model.NewsFeed;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface NewsFeedContract {

    interface NewsFeedView extends MvpLceView<List<NewsFeed.Article>> {
    }

    interface NewsFeedPresenter extends MvpPresenter<NewsFeedView> {
        void loadData(boolean pullToRefresh);
        void onArticleClicked(NewsFeed.Article article);
        void onShareClicked(NewsFeed.Article article);
    }
}
