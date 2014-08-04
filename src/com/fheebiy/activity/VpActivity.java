package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import com.fheebiy.R;
import com.fheebiy.adapter.ViewPagerAdapter;
import com.fheebiy.fragment.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-4.
 *
 * 用于测试viewPager
 *
 */
public class VpActivity extends FragmentActivity {

    private ViewPager vp;

    private PagerTabStrip pagerTabStrip;

    List<Fragment> list = new ArrayList<Fragment>();

    List<String> titles = new ArrayList<String>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vp);
        vp = (ViewPager)findViewById(R.id.viewpager);
        pagerTabStrip = (PagerTabStrip)findViewById(R.id.pagertab);

       // pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicator));
        pagerTabStrip.setTabIndicatorColorResource(R.color.indicator);
        pagerTabStrip.setTextColor(getResources().getColor(R.color.white));
        //pagerTabStrip.setTextSpacing(20);
        Fragment oneFragment = new TabOneFragment();
        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();
        Fragment fiveFragment = new TabFiveFragment();

        list.add(twoFragment);
        list.add(threeFragment);
        list.add(fourFragment);
        list.add(fiveFragment);

        titles.add("one");
        titles.add("two");
        titles.add("three");
        titles.add("four");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),list,titles, this);
        vp.setAdapter(adapter);
    }
}