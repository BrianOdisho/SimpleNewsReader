package org.brianodisho.newsreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("source")
    public String source;

    @SerializedName("sortBy")
    public String sortBy;

    @SerializedName("articles")
    public List<Article> articles;


    public static class Article {

        @SerializedName("author")
        public String author;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("url")
        public String url;

        @SerializedName("urlToImage")
        public String urlToImage;

        @SerializedName("publishedAt")
        public String publishedAt;
    }
}
