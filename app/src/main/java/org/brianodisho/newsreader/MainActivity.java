package org.brianodisho.newsreader;

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

import org.brianodisho.newsreader.latestnews.LatestNewsFragment;

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_settings:
                presenter.onLatestNewsSelected();
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

    @Override
    public void showLatestNewsView() {
        showContentFragment(new LatestNewsFragment(), false);
        navigationMenu.findItem(R.id.nav_latest_news).setChecked(true);
    }

    @Override
    public void showBookmarkedView() {
        // TODO show bookmarked view
//        showContentFragment(new BookmarkedFragment(), true);
        navigationMenu.findItem(R.id.nav_bookmarked).setChecked(true);
    }

    @Override
    public void showPreferencesView() {
        // TODO show preference view
//        showContentFragment(new PreferencesFragment(), true);
        navigationMenu.findItem(R.id.nav_preferences).setChecked(true);
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
