package com.fheebiy.activity.communicate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.fheebiy.R;
import com.fheebiy.adapter.ViewPagerAdapter;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.communicate.CommFragment1;
import com.fheebiy.fragment.communicate.CommFragment2;
import com.fheebiy.model.Hero;
import com.fheebiy.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-9-29.
 *
 * 引入PagerSlidingTabStrip
 * 采用接口的方式实现Fragment Activity 之间通信
 * Fragment之间通信，无论采用Broadcast还是Interface方式都必须通过Activity中转，不可直接通信
 */
public class Communicate2Activity extends FragmentActivity implements CommFragment1.AddOrSubListViewListener,CommFragment1.ChangeTextListener,CommFragment1.SwitchFragListener{

    private PagerSlidingTabStrip strip;

    private ViewPagerAdapter adapter;

    private ViewPager viewPager;

    private List<Fragment> list = new ArrayList<Fragment>();

    private TabThreeFragment threeFragment;

    private CommFragment2 commFragment2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate2);
        strip =(PagerSlidingTabStrip)findViewById(R.id.psTabStrip);
        viewPager = (ViewPager)findViewById(R.id.comm_vp);
        init();
    }

    private void init(){
        threeFragment = new TabThreeFragment();
        commFragment2 = new CommFragment2();
        list.add(new CommFragment1());
        list.add(threeFragment);
        list.add(commFragment2);

        List<String> titles = new ArrayList<String>();
        titles.add("one");
        titles.add("two");
        titles.add("three");

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),list,titles,this);
        viewPager.setAdapter(adapter);
        strip.setViewPager(viewPager);
    }

    @Override
    public void sub(int position) {
        threeFragment.subOne(position);
    }

    @Override
    public void add(Hero hero) {
        threeFragment.addOne(hero);
    }

    @Override
    public void changeText(String text) {
        commFragment2.changeText(text);
    }

    @Override
    public void switchFrag(int position) {
        viewPager.setCurrentItem(position);
    }
}