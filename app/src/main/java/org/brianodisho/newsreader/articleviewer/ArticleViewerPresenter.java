package org.brianodisho.newsreader.articleviewer;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

interface ArticleViewerPresenter extends MvpPresenter<ArticleViewerView> {
    void onViewReady();
}
