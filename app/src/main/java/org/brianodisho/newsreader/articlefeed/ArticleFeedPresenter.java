package org.brianodisho.newsreader.articlefeed;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.brianodisho.newsreader.model.ArticlesResponse;

interface ArticleFeedPresenter extends MvpPresenter<ArticleFeedView> {
    void onArticleClicked(ArticlesResponse.Article article);
}
