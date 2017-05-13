package org.brianodisho.newsreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourcesResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("sources")
    public List<Source> sources;


    public static class Source {

        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("description")
        public String description;

        @SerializedName("url")
        public String url;

        @SerializedName("category")
        public String category;

        @SerializedName("language")
        public String language;

        @SerializedName("country")
        public String country;
    }
}
