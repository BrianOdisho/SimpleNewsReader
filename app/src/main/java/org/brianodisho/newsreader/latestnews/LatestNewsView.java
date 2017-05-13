package org.brianodisho.newsreader.latestnews;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Interface for the LatestNewsView
 */
interface LatestNewsView extends MvpView {
    void setData(List<String> data);
}
