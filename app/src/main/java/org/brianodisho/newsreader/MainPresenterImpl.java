package org.brianodisho.newsreader;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.model.source.NewsApi;

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
        // TODO Decide what view starts first
        router.showNewsView(NewsApi.BUSINESS);
    }

    @Override
    public void onBusinessSelected() {
        router.showNewsView(NewsApi.BUSINESS);
    }

    @Override
    public void onEntertainmentSelected() {
        router.showNewsView(NewsApi.ENTERTAINMENT);
    }

    @Override
    public void onGamingSelected() {
        router.showNewsView(NewsApi.GAMING);
    }

    @Override
    public void onGeneralSelected() {
        router.showNewsView(NewsApi.GENERAL);
    }

    @Override
    public void onMusicSelected() {
        router.showNewsView(NewsApi.MUSIC);
    }

    @Override
    public void onPoliticsSelected() {
        router.showNewsView(NewsApi.POLITICS);
    }

    @Override
    public void onScienceAndNatureSelected() {
        router.showNewsView(NewsApi.SCIENCE_AND_NATURE);
    }

    @Override
    public void onSportSelected() {
        router.showNewsView(NewsApi.SPORT);
    }

    @Override
    public void onTechnologySelected() {
        router.showNewsView(NewsApi.TECHNOLOGY);
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
