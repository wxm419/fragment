package com.fheebiy.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.fheebiy.R;
import com.fheebiy.adapter.ComplexVpAdapter;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;
import com.fheebiy.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-8-13.
 */
public class VpStripActivity extends FragmentActivity{


    private ViewPager viewPager;

    private PagerSlidingTabStrip pagerSlidingTabStrip;

    private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpstrip);
        viewPager = (ViewPager)findViewById(R.id.vpstrip_vp);
        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.vpstrips_strips);
        pagerSlidingTabStrip.setIndicatorColorResource(R.color.strip_color);
        pagerSlidingTabStrip.setTextSize(40);
        //pagerSlidingTabStrip.setTabPaddingLeftRight(40);
        pagerSlidingTabStrip.setIndicatorHeight(10);
        pagerSlidingTabStrip.setShouldExpand(true);
        initUI();
        pagerSlidingTabStrip.setViewPager(viewPager);
    }



    private void initUI(){

        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();
        Fragment fourFragment0 = new TabFourFragment();
        Fragment fourFragment1 = new TabFourFragment();
        Fragment fourFragment2 = new TabFourFragment();
        Fragment fourFragment3 = new TabFourFragment();
        Fragment fourFragment4 = new TabFourFragment();

        list.add(twoFragment);
        list.add(threeFragment);
        list.add(fourFragment);
        list.add(fourFragment0);
        list.add(fourFragment1);
        list.add(fourFragment2);
        list.add(fourFragment3);
        list.add(fourFragment4);


        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),list,this);

        viewPager.setAdapter(adapter);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {


        private List<Fragment> list;

        private Context ctx;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> list, Context ctx) {
            super(fm);
            this.list = list;
            this.ctx = ctx;
        }


        private final String[] TITLES = { "精华区", "圈吧", "图片搜索","论坛搜索","价格行情","求购信息","相关网页","求购信息"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

    }

}
