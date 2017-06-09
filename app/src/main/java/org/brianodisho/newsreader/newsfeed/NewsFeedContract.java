package org.brianodisho.newsreader.newsfeed;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import org.brianodisho.newsreader.model.NewsFeed;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface NewsFeedContract {

    interface NewsFeedView extends MvpView {
        void setData(List<NewsFeed.Article> data);
    }

    interface NewsFeedPresenter extends MvpPresenter<NewsFeedView> {
        void onArticleClicked(NewsFeed.Article article);
    }
}
