package org.brianodisho.newsreader.articleviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.articleviewer.ArticleViewerContract.ArticleViewerPresenter;
import org.brianodisho.newsreader.articleviewer.ArticleViewerContract.ArticleViewerView;

/**
 * Implementation of the ArticleViewerView
 */
public class ArticleViewerActivity extends MvpActivity<ArticleViewerView, ArticleViewerPresenter> implements ArticleViewerView {
    private static final String EXTRA_ARTICLE_VIEWER_URL = "EXTRA_ARTICLE_VIEWER_URL";

    private WebView webView;


    public static void start(@NonNull Context context, String url) {
        Intent starter = new Intent(context, ArticleViewerActivity.class);
        starter.putExtra(EXTRA_ARTICLE_VIEWER_URL, url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_viewer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        webView = (WebView) findViewById(R.id.webView_article_viewer);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        presenter.onViewReady();
    }

    @NonNull
    @Override
    public ArticleViewerPresenter createPresenter() {
        return new ArticleViewerPresenterImpl(getIntent().getStringExtra(EXTRA_ARTICLE_VIEWER_URL));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadArticle(String url) {
        webView.loadUrl(url);
    }
}
