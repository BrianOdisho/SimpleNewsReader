package org.brianodisho.newsreader.dagger;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.brianodisho.newsreader.model.Constants;
import org.brianodisho.newsreader.model.source.NewsApi;

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
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request interceptedRequest = chain.request();

                        HttpUrl interceptedHttpUrl = interceptedRequest.url();
                        HttpUrl newHttpUrl = interceptedHttpUrl.newBuilder()
                                .addQueryParameter("apiKey", Constants.NEWSAPI_KEY)
                                .build();

                        Request request = interceptedRequest.newBuilder()
                                .url(newHttpUrl)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application application) {
        return new Picasso.Builder(application)
                .build();
    }

    @Provides
    @Singleton
    NewsApi provideNewsApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.NEWSAPI_BASE_URL)
                .client(okHttpClient)
                .build()
                .create(NewsApi.class);
    }
}
