package org.brianodisho.newsreader;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Interface for the MainPresenter
 */
interface MainPresenter extends MvpPresenter<MainView> {
    void onViewReady();
    void onBusinessSelected();
    void onEntertainmentSelected();
    void onGamingSelected();
    void onGeneralSelected();
    void onMusicSelected();
    void onPoliticsSelected();
    void onScienceAndNatureSelected();
    void onSportSelected();
    void onTechnologySelected();
    void onBookmarkedSelected();
    void onPreferencesSelected();
}
