package org.brianodisho.newsreader.model.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.brianodisho.newsreader.model.ArticlesResponse;
import org.brianodisho.newsreader.model.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    String BASE_URL = "https://newsapi.org/v1/";
    String ARTICLES_ENDPOINT = "articles";
    String SOURCES_ENDPOINT = "sources";
    String KEY = "20e45c20009a4838a6db1252d82ffdd7";
    String ENGLISH = "en";
    String GERMAN = "de";
    String FRENCH = "fr";
    String BUSINESS = "business";
    String ENTERTAINMENT = "entertainment";
    String GAMING = "gaming";
    String MUSIC = "music";
    String POLITICS = "politics";
    String SCIENCE_AND_NATURE = "science-and-nature";
    String SPORT = "sport";
    String TECHNOLOGY = "technology";

    @GET(ARTICLES_ENDPOINT)
    Call<ArticlesResponse> getArticles(@Query("source") @NonNull String source, @Query("sortBy") @Nullable String sortBy);

    @GET(SOURCES_ENDPOINT)
    Call<SourcesResponse> getSources(@Query("category") @Nullable String category, @Query("language") @Nullable String language);
}
