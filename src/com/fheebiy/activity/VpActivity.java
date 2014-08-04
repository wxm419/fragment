package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

    List<Fragment> list = new ArrayList<Fragment>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vp);
        vp = (ViewPager)findViewById(R.id.viewpager);

        Fragment oneFragment = new TabOneFragment();
        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();
        Fragment fiveFragment = new TabFiveFragment();

        list.add(twoFragment);
        list.add(threeFragment);
        list.add(fourFragment);
        list.add(fiveFragment);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),list, this);
        vp.setAdapter(adapter);
    }
}