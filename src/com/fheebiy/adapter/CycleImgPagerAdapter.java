package com.fheebiy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by zhouwenbo on 15/5/23.
 */
public class CycleImgPagerAdapter extends PagerAdapter {

    private Context       context;
    private List<Integer> imageIdList;

    private int           size;
    private boolean       isInfiniteLoop;


    private List<Integer> imgList;

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

}
