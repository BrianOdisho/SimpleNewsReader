package org.brianodisho.newsreader;

import org.brianodisho.newsreader.newsfeed.NewsFeedArticleAdapter;
import org.brianodisho.newsreader.newsfeed.NewsFeedPresenterImpl;
import org.brianodisho.newsreader.dagger.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(NewsFeedPresenterImpl articleFeedPresenterImpl);
    void inject(NewsFeedArticleAdapter newsFeedArticleAdapter);
}
