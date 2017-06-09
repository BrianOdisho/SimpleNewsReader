package org.brianodisho.newsreader;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.MainContract.MainPresenter;
import org.brianodisho.newsreader.MainContract.MainView;

/**
 * Implementation of the MainPresenter
 */
class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter {

    private final MainRouter router;


    MainPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void onViewReady() {
        router.showLatestNewsView();
    }

    @Override
    public void onLatestNewsSelected() {
        router.showLatestNewsView();
    }

    @Override
    public void onBookmarkedSelected() {
        router.showBookmarkedView();
    }

    @Override
    public void onPreferencesSelected() {
        router.showPreferencesView();
    }
}
