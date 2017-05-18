package org.brianodisho.newsreader.articlefeed;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.brianodisho.newsreader.model.Articles;

interface ArticleFeedPresenter extends MvpPresenter<ArticleFeedView> {
    void onArticleClicked(Articles.Article article);
}
