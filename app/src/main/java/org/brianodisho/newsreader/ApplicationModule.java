package org.brianodisho.newsreader;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that is used to pass Application dependency to components.
 */
@Module
final class ApplicationModule {

    private final Application application;


    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}