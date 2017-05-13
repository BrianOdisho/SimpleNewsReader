package org.brianodisho.newsreader;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Interface for the MainPresenter
 */
interface MainPresenter extends MvpPresenter<MainView> {
    void onViewReady();
    void onLatestNewsSelected();
    void onBookmarkedSelected();
    void onPreferencesSelected();
}
