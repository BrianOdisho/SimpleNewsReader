package org.brianodisho.newsreader.dagger;

import org.brianodisho.newsreader.articlefeed.ArticleFeedAdapter;
import org.brianodisho.newsreader.articlefeed.ArticleFeedPresenterImpl;
import org.brianodisho.newsreader.latestnews.LatestNewsPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(ArticleFeedPresenterImpl articleFeedPresenterImpl);
    void inject(ArticleFeedAdapter articleFeedAdapter);
    void inject(LatestNewsPresenterImpl latestNewsPresenterImpl);
}
