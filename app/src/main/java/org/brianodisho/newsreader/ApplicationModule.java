package org.brianodisho.newsreader;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that is used to pass Application dependency to components.
 */
@Module
public final class ApplicationModule {

    private final Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }
}