package org.brianodisho.newsreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeed {

    @SerializedName("source")
    private String source;

    @SerializedName("articles")
    private List<Article> articles;


    public String getSource() {
        return source;
    }


    public List<Article> getArticles() {
        return articles;
    }


    public static class Article {

        @SerializedName("author")
        private String author;

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("url")
        private String url;

        @SerializedName("urlToImage")
        private String urlToImage;

        @SerializedName("publishedAt")
        private String publishedAt;


        public String getAuthor() {
            return author;
        }


        public String getTitle() {
            return title;
        }


        public String getDescription() {
            return description;
        }


        public String getUrl() {
            return url;
        }


        public String getUrlToImage() {
            return urlToImage;
        }


        public String getPublishedAt() {
            return publishedAt;
        }
    }
}
