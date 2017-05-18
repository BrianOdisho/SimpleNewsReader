package org.brianodisho.newsreader.articleviewer;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

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
