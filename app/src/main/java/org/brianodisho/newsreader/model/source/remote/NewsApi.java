package org.brianodisho.newsreader.model.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.model.NewsSources;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    String BASE_URL = "https://newsapi.org/v1/";
    String ARTICLES_ENDPOINT = "articles";
    String US_ENGLISH_SOURCES_ENDPOINT = "sources?language=en&country=us";
    String KEY = "20e45c20009a4838a6db1252d82ffdd7";

    @GET(ARTICLES_ENDPOINT)
    Observable<NewsFeed> getArticles(@Query("source") @NonNull String source);

    @GET(US_ENGLISH_SOURCES_ENDPOINT)
    Observable<NewsSources> getSources(@Query("category") @Nullable String category);
}
