package org.brianodisho.newsreader.latestnews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.brianodisho.newsreader.articlefeed.ArticleFeedFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class LatestNewsPagerAdapter extends FragmentPagerAdapter {

//    private List<String> data = Arrays.asList("bloomberg", "cnn", "fortune");
    private final List<String> data = new ArrayList<>();


    LatestNewsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFeedFragment.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).toUpperCase(Locale.getDefault());
    }


    public void setData(List<String> data) {
        if (!this.data.isEmpty()) {
            this.data.clear();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
