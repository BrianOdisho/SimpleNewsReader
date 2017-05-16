package org.brianodisho.newsreader;

/**
 * Interface for the MainRouter
 */
public interface MainRouter {
    void showNewsView(String newsCategory);
    void showBookmarkedView();
    void showPreferencesView();
    void showArticle(String url);
}
