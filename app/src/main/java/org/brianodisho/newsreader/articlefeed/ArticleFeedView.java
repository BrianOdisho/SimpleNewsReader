package org.brianodisho.newsreader.articlefeed;

import com.hannesdorfmann.mosby.mvp.MvpView;

import org.brianodisho.newsreader.model.Articles;

import java.util.List;

interface ArticleFeedView extends MvpView {
    void setData(List<Articles.Article> data);
}
