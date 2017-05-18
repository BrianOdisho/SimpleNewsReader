package org.brianodisho.newsreader.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.brianodisho.newsreader.articlefeed.ArticleFeedFragment;
import org.brianodisho.newsreader.model.Sources;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Sources.Source> data = new ArrayList<>();


    NewsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFeedFragment.newInstance(data.get(position).id);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).name;
    }


    public void setData(List<Sources.Source> data) {
        if (!this.data.isEmpty()) {
            this.data.clear();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
