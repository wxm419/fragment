package com.fheebiy.activity.vp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.fheebiy.R;
import com.fheebiy.adapter.ComplexVpAdapter;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;
import com.fheebiy.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-13.
 *
 * 用开源空间pagerSlidingTabStrip结合ViewPager使用
 */
public class VpStripActivity extends FragmentActivity{
    static final String TAG = "VpStripActivity";

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
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.d(TAG,"i====="+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


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
