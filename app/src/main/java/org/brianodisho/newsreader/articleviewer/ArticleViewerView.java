package org.brianodisho.newsreader.articleviewer;

import com.hannesdorfmann.mosby.mvp.MvpView;

interface ArticleViewerView extends MvpView {
    void loadArticle(String url);
}
