package org.brianodisho.newsreader.newsfeed;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.MainRouter;
import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.model.NewsSources;
import org.brianodisho.newsreader.model.source.remote.NewsApi;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedPresenter;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedPresenterImpl extends MvpBasePresenter<NewsFeedView> implements NewsFeedPresenter {

    private final String newsCategory;
    private final MainRouter router;

    private Call<NewsSources> sourcesCall;
    private Call<NewsFeed> articlesCall;

    @Inject
    NewsApi newsApi;

    NewsFeedPresenterImpl(String newsCategory, MainRouter router) {
        this.newsCategory = newsCategory;
        this.router = router;
    }

    @Override
    public void attachView(NewsFeedView view) {
        super.attachView(view);
        sourcesCall = newsApi.getSources(newsCategory);
        sourcesCall.enqueue(new Callback<NewsSources>() {
            @Override
            public void onResponse(Call<NewsSources> call, Response<NewsSources> response) {
                sourcesCall = null;
                if (response.isSuccessful()) {
                    List<NewsSources.Source> sources = response.body().getSources();
                    if (sources != null) {
                        for (NewsSources.Source source : sources) {
                            getArticles(source);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<NewsSources> call, Throwable t) {
                if (!call.isCanceled()) {
                    sourcesCall = null;
                }
            }
        });

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (sourcesCall != null) {
            sourcesCall.cancel();
            sourcesCall = null;
        }
        if (articlesCall != null) {
            articlesCall.cancel();
            articlesCall = null;
        }
    }

    @Override
    public void onArticleClicked(NewsFeed.Article article) {
        router.showArticle(article.getUrl());
    }


    private void getArticles(NewsSources.Source source) {
        articlesCall = newsApi.getArticles(source.getId());
        articlesCall.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                articlesCall = null;
                if (response.isSuccessful()) {
                    List<NewsFeed.Article> articles = response.body().getArticles();
                    if (getView() != null) {
                        if (articles != null) {
                            getView().setData(articles);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                if (!call.isCanceled()) {
                    articlesCall = null;
                }
            }
        });
    }
}
