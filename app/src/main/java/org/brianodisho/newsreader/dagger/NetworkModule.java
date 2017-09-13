package org.brianodisho.newsreader.dagger;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.brianodisho.newsreader.model.source.remote.NewsApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Picasso providePicasso(Application application) {
        return new Picasso.Builder(application)
                .build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    NewsApi provideNewsApi(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(NewsApi.BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request interceptedRequest = chain.request();

                                HttpUrl newHttpUrl = interceptedRequest.url().newBuilder()
                                        .addQueryParameter("apiKey", NewsApi.KEY)
                                        .build();

                                Request request = interceptedRequest.newBuilder()
                                        .url(newHttpUrl)
                                        .build();

                                return chain.proceed(request);
                            }
                        })
                        .build())
                .build()
                .create(NewsApi.class);
    }
}
