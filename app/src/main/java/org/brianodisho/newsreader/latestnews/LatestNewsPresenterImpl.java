package org.brianodisho.newsreader.latestnews;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the LatestNewsPresenter
 */
class LatestNewsPresenterImpl extends MvpBasePresenter<LatestNewsView> implements LatestNewsPresenter {

    @Override
    public void attachView(LatestNewsView view) {
        super.attachView(view);
        if (getView() != null) {
            // TODO get sources from api
            List<String> data = Arrays.asList("bloomberg", "cnn");
            getView().setData(data);
        }
    }
}
