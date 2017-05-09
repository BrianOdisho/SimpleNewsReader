package org.brianodisho.newsreader;

import android.app.Application;

import org.brianodisho.newsreader.dagger.ApplicationModule;
import org.brianodisho.newsreader.dagger.DaggerNetworkComponent;
import org.brianodisho.newsreader.dagger.NetworkModule;
import org.brianodisho.newsreader.dagger.NetworkComponent;
import org.brianodisho.newsreader.model.Constants;


public class NewsReaderApplication extends Application {
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
