package org.brianodisho.newsreader.news;

import com.hannesdorfmann.mosby.mvp.MvpView;

import org.brianodisho.newsreader.model.Sources;

import java.util.List;

/**
 * Interface for the NewsView
 */
interface NewsView extends MvpView {
    void setData(List<Sources.Source> data);
}
