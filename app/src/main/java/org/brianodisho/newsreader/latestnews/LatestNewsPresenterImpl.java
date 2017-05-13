package org.brianodisho.newsreader.latestnews;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.model.SourcesResponse;
import org.brianodisho.newsreader.model.source.NewsApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the LatestNewsPresenter
 */
public class LatestNewsPresenterImpl extends MvpBasePresenter<LatestNewsView> implements LatestNewsPresenter {

    private Call<SourcesResponse> sourcesCall;

    @Inject
    NewsApi newsApi;

    @Override
    public void attachView(LatestNewsView view) {
        super.attachView(view);
        sourcesCall = newsApi.getSources(null, NewsApi.ENGLISH);
        sourcesCall.enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                sourcesCall = null;
                if (response.isSuccessful()) {
                    List<SourcesResponse.Source> sources = response.body().sources;
                    if (getView() != null) {
                        if (sources != null) {
                            getView().setData(sources);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SourcesResponse> call, Throwable t) {
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
