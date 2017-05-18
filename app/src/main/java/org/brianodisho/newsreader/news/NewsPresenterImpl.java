package org.brianodisho.newsreader.news;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.model.Sources;
import org.brianodisho.newsreader.model.source.NewsApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the NewsPresenter
 */
public class NewsPresenterImpl extends MvpBasePresenter<NewsView> implements NewsPresenter {

    private Call<Sources> sourcesCall;

    private String newsCategory;

    @Inject
    NewsApi newsApi;

    NewsPresenterImpl(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    @Override
    public void attachView(NewsView view) {
        super.attachView(view);
        sourcesCall = newsApi.getSources(newsCategory, NewsApi.ENGLISH);
        sourcesCall.enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                sourcesCall = null;
                if (response.isSuccessful()) {
                    List<Sources.Source> sources = response.body().sources;
                    if (getView() != null) {
                        if (sources != null) {
                            getView().setData(sources);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {
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
    }
}
