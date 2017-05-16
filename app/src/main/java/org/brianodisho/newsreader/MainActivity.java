package org.brianodisho.newsreader;

import android.content.Intent;
import android.net.Uri;
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

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import org.brianodisho.newsreader.latestnews.NewsFragment;

/**
 * Implementation of the MainView
 */
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements NavigationView.OnNavigationItemSelectedListener, MainView, MainRouter {

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
                        navigationMenu.findItem(R.id.nav_business).setChecked(true);
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
            case R.id.nav_business:
                presenter.onBusinessSelected();
                navigationMenu.findItem(R.id.nav_business).setChecked(true);
                break;
            case R.id.nav_entertainment:
                presenter.onEntertainmentSelected();
                navigationMenu.findItem(R.id.nav_entertainment).setChecked(true);
                break;
            case R.id.nav_gaming:
                presenter.onGamingSelected();
                navigationMenu.findItem(R.id.nav_gaming).setChecked(true);
                break;
            case R.id.nav_general:
                presenter.onGeneralSelected();
                navigationMenu.findItem(R.id.nav_general).setChecked(true);
                break;
            case R.id.nav_music:
                presenter.onMusicSelected();
                navigationMenu.findItem(R.id.nav_music).setChecked(true);
                break;
            case R.id.nav_politics:
                presenter.onPoliticsSelected();
                navigationMenu.findItem(R.id.nav_politics).setChecked(true);
                break;
            case R.id.nav_science_and_nature:
                presenter.onScienceAndNatureSelected();
                navigationMenu.findItem(R.id.nav_science_and_nature).setChecked(true);
                break;
            case R.id.nav_sport:
                presenter.onSportSelected();
                navigationMenu.findItem(R.id.nav_sport).setChecked(true);
                break;
            case R.id.nav_technology:
                presenter.onTechnologySelected();
                navigationMenu.findItem(R.id.nav_technology).setChecked(true);
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

    @Override
    public void showNewsView(String newsCategory) {
        showContentFragment(NewsFragment.newInstance(newsCategory), false);
        setTitle(newsCategory);
    }

    @Override
    public void showBookmarkedView() {
        // TODO show bookmarked view
//        showContentFragment(new BookmarkedFragment(), true);
        navigationMenu.findItem(R.id.nav_bookmarked).setChecked(true);
        setTitle(getString(R.string.navigation_drawer_bookmarked));
    }

    @Override
    public void showPreferencesView() {
        // TODO show preference view
//        showContentFragment(new PreferencesFragment(), true);
        navigationMenu.findItem(R.id.nav_preferences).setChecked(true);
        setTitle(getString(R.string.navigation_drawer_preferences));
    }

    @Override
    public void showArticle(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
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
