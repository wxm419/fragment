package com.fheebiy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by bob zhou on 14-8-4.
 * 最基础的ViewPager adapter
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    private List<String> titles;

    private Context ctx;

   /* public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }*/

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list,List<String> titles, Context ctx) {
        super(fm);
        this.list = list;
        this.titles = titles;
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
