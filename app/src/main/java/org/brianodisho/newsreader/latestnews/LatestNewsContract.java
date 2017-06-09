package org.brianodisho.newsreader.latestnews;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface LatestNewsContract {

    interface LatestNewsView extends MvpView {
    }

    interface LatestNewsPresenter extends MvpPresenter<LatestNewsView> {
    }
}
