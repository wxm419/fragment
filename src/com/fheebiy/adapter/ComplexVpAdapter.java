package com.fheebiy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by bob zhou on 14-8-4.
 */
public class ComplexVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    private Context ctx;

    public ComplexVpAdapter(FragmentManager fm, List<Fragment> list, Context ctx) {
        super(fm);
        this.list = list;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(position >4){
            super.destroyItem(container, position, object);
        }
    }


}
