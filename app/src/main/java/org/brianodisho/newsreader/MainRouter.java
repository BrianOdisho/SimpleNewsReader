package org.brianodisho.newsreader;

import org.brianodisho.newsreader.model.NewsFeed;

/**
 * Interface for the MainRouter
 */
public interface MainRouter {
    void showLatestNewsView();
    void showBookmarkedView();
    void showPreferencesView();
    void showArticle(NewsFeed.Article article);
    void showShareArticleDialog(NewsFeed.Article article);
}
