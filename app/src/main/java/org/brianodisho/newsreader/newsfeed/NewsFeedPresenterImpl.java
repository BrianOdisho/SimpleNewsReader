package org.brianodisho.newsreader.newsfeed;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.newsreader.MainRouter;
import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.model.NewsSources;
import org.brianodisho.newsreader.model.source.remote.NewsApi;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedPresenter;
import org.brianodisho.newsreader.newsfeed.NewsFeedContract.NewsFeedView;
import org.brianodisho.newsreader.util.Formatter;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsFeedPresenterImpl extends MvpBasePresenter<NewsFeedView> implements NewsFeedPresenter {

    private final String newsCategory;
    private final MainRouter router;
    private final List<NewsFeed.Article> data;

    @Inject
    NewsApi newsApi;


    NewsFeedPresenterImpl(String newsCategory, MainRouter router) {
        this.newsCategory = newsCategory;
        this.router = router;
        data = new ArrayList<>();
    }

    @Override
    public void attachView(NewsFeedView view) {
        super.attachView(view);

        newsApi.getSources(newsCategory)
                .flatMap(new Function<NewsSources, ObservableSource<NewsSources.Source>>() {
                    @Override
                    public ObservableSource<NewsSources.Source> apply(NewsSources newsSources) throws Exception {
                        return Observable.fromIterable(newsSources.getSources());
                    }
                })
                .flatMap(new Function<NewsSources.Source, ObservableSource<NewsFeed>>() {
                    @Override
                    public ObservableSource<NewsFeed> apply(@NonNull NewsSources.Source source) throws Exception {
                        return newsApi.getArticles(source.getId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsFeed>() {
                    @Override
                    public void onNext(@NonNull NewsFeed newsFeed) {
                        data.addAll(newsFeed.getArticles());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // TODO handle error
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Collections.sort(data, new Comparator<NewsFeed.Article>() {
                            public int compare(NewsFeed.Article article1, NewsFeed.Article article2) {
                                long article1PublishedAt = Formatter.toUnixTimestamp(article1.getPublishedAt());
                                long article2PublishedAt = Formatter.toUnixTimestamp(article2.getPublishedAt());
                                return Long.valueOf(article2PublishedAt).compareTo(article1PublishedAt);
                            }
                        });
                        if (getView() != null) {
                            getView().setData(data);
                        }
                    }
                });
    }

    @Override
    public void onArticleClicked(NewsFeed.Article article) {
        router.showArticle(article.getUrl());
    }
}
