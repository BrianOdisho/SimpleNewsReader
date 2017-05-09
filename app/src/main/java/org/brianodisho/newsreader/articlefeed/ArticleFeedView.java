package org.brianodisho.newsreader.articlefeed;


import com.hannesdorfmann.mosby.mvp.MvpView;

import org.brianodisho.newsreader.model.ArticlesResponse;

import java.util.List;

interface ArticleFeedView extends MvpView {
    void setData(List<ArticlesResponse.Article> data);
}
