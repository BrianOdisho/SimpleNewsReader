package org.brianodisho.newsreader.articleviewer;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.articleviewer.ArticleViewerContract.ArticleViewerPresenter;
import org.brianodisho.newsreader.articleviewer.ArticleViewerContract.ArticleViewerView;

/**
 * Implementation of the ArticleViewerView
 */
class ArticleViewerPresenterImpl extends MvpBasePresenter<ArticleViewerView> implements ArticleViewerPresenter {

    private final String url;


    ArticleViewerPresenterImpl(String url) {
        this.url = url;
    }

    @Override
    public void onViewReady() {
        if (getView() != null) {
            getView().loadArticle(url);
        }
    }
}
