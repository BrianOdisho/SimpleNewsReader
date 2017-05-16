package org.brianodisho.newsreader.latestnews;

import com.hannesdorfmann.mosby.mvp.MvpView;

import org.brianodisho.newsreader.model.SourcesResponse;

import java.util.List;

/**
 * Interface for the NewsView
 */
interface NewsView extends MvpView {
    void setData(List<SourcesResponse.Source> data);
}
