package org.brianodisho.newsreader.articleviewer;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ArticleViewerContract {

    interface ArticleViewerView extends MvpView {
        void loadArticle(String url);
    }

    interface ArticleViewerPresenter extends MvpPresenter<ArticleViewerView> {
        void onViewReady();
    }
}
