package org.brianodisho.newsreader;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {

    interface MainView extends MvpView {
    }

    interface MainPresenter extends MvpPresenter<MainView> {
        void onViewReady();
        void onLatestNewsSelected();
        void onBookmarkedSelected();
        void onPreferencesSelected();
    }
}
