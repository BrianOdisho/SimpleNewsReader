package org.brianodisho.newsreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsSources {

    @SerializedName("sources")
    private List<Source> sources;


    public List<Source> getSources() {
        return sources;
    }


    public static class Source {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("url")
        private String url;

        @SerializedName("category")
        private String category;

        @SerializedName("language")
        private String language;

        @SerializedName("country")
        private String country;


        public String getId() {
            return id;
        }


        public String getName() {
            return name;
        }


        public String getDescription() {
            return description;
        }


        public String getUrl() {
            return url;
        }


        public String getCategory() {
            return category;
        }


        public String getLanguage() {
            return language;
        }


        public String getCountry() {
            return country;
        }
    }
}
