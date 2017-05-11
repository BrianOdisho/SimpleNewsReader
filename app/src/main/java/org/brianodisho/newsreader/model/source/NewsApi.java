package org.brianodisho.newsreader.model.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.brianodisho.newsreader.model.Constants;
import org.brianodisho.newsreader.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET(Constants.NEWSAPI_ARTICLES_ENDPOINT)
    Call<ArticlesResponse> getArticles(@Query("source") @NonNull String source, @Query("sortBy") @Nullable String sortBy);
}
