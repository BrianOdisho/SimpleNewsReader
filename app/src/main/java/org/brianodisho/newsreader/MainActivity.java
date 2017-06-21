package org.brianodisho.newsreader;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.brianodisho.newsreader.MainContract.MainPresenter;
import org.brianodisho.newsreader.MainContract.MainView;
import org.brianodisho.newsreader.articleviewer.ArticleViewerActivity;
import org.brianodisho.newsreader.latestnews.LatestNewsFragment;
import org.brianodisho.newsreader.model.NewsFeed;

/**
 * Implementation of the MainView
 */
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, MainRouter, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Menu navigationMenu;

    private boolean _clearingBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationMenu = navigationView.getMenu();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    if (_clearingBackStack)
                        _clearingBackStack = false;
                    else
                        navigationMenu.findItem(R.id.nav_latest_news).setChecked(true);
                }
            }
        });
        presenter.onViewReady();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_latest_news:
                presenter.onLatestNewsSelected();
                break;
            case R.id.nav_bookmarked:
                presenter.onBookmarkedSelected();
                break;
            case R.id.nav_preferences:
                presenter.onPreferencesSelected();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showLatestNewsView() {
        showContentFragment(new LatestNewsFragment(), false);
        navigationMenu.findItem(R.id.nav_latest_news).setChecked(true);
        setTitle(R.string.navigation_drawer_latest_news);
    }

    @Override
    public void showBookmarkedView() {
//        showContentFragment(new BookmarkedFragment(), true);
        navigationMenu.findItem(R.id.nav_bookmarked).setChecked(true);
        setTitle(R.string.navigation_drawer_bookmarked);
    }

    @Override
    public void showPreferencesView() {
//        showContentFragment(new PreferencesFragment(), true);
        navigationMenu.findItem(R.id.nav_preferences).setChecked(true);
        setTitle(R.string.navigation_drawer_preferences);
    }

    @Override
    public void showArticle(NewsFeed.Article article) {
        ArticleViewerActivity.start(this, article.getUrl());
    }

    @Override
    public void showShareArticleDialog(NewsFeed.Article article) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, article.getTitle() + " " + article.getUrl());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share Article"));
    }


    private void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            _clearingBackStack = true;
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStackImmediate(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    private void showContentFragment(Fragment fragment, boolean addToBackstack) {
        clearBackStack();
        if (addToBackstack)
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }
}
