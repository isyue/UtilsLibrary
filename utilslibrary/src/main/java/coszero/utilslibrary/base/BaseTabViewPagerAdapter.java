package coszero.utilslibrary.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yue on 2016/07/05.
 */
public class BaseTabViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;

    public BaseTabViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (titles != null && titles.length > 0) {
            return titles.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
