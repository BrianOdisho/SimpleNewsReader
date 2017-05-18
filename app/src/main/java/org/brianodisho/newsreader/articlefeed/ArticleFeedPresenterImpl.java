package org.brianodisho.newsreader.articlefeed;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.MainRouter;
import org.brianodisho.newsreader.model.Articles;
import org.brianodisho.newsreader.model.source.NewsApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleFeedPresenterImpl extends MvpBasePresenter<ArticleFeedView> implements ArticleFeedPresenter {

    private final String articleFeedSource;
    private final MainRouter router;

    private Call<Articles> articlesCall;

    @Inject
    NewsApi newsApi;

    ArticleFeedPresenterImpl(String articleFeedSource, MainRouter router) {
        this.articleFeedSource = articleFeedSource;
        this.router = router;
    }

    @Override
    public void attachView(ArticleFeedView view) {
        super.attachView(view);
        articlesCall = newsApi.getArticles(articleFeedSource, "top");
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                articlesCall = null;
                if (response.isSuccessful()) {
                    List<Articles.Article> articles = response.body().articles;
                    if (getView() != null) {
                        if (articles != null) {
                            getView().setData(articles);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
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

    @Override
    public void onArticleClicked(Articles.Article article) {
        router.showArticle(article.url);
    }
}
