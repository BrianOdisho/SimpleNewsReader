package org.brianodisho.newsreader.articlefeed;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.model.ArticlesResponse;
import org.brianodisho.newsreader.model.source.NewsApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleFeedPresenterImpl extends MvpBasePresenter<ArticleFeedView> implements ArticleFeedPresenter {

    private final String articleFeedSource;

    private Call<ArticlesResponse> articlesCall;

    @Inject
    NewsApi newsApi;

    ArticleFeedPresenterImpl(String articleFeedSource) {
        this.articleFeedSource = articleFeedSource;
    }

    @Override
    public void attachView(ArticleFeedView view) {
        super.attachView(view);
        articlesCall = newsApi.getArticles(articleFeedSource, "top");
        articlesCall.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                articlesCall = null;
                if (response.isSuccessful()) {
                    List<ArticlesResponse.Article> articles = response.body().articles;
                    if (getView() != null) {
                        if (articles != null) {
                            getView().setData(articles);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    articlesCall = null;
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (articlesCall != null) {
            articlesCall.cancel();
            articlesCall = null;
        }
    }
}
