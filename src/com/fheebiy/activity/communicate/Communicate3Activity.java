package com.fheebiy.activity.communicate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.fheebiy.R;
import com.fheebiy.adapter.ViewPagerAdapter;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.communicate.CommFragment2;
import com.fheebiy.fragment.communicate.CommFragment3;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;
import com.fheebiy.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-9-29.
 * Fragment Activity之间的通信,采用Broadcast方式
 * Fragment之间通信，无论采用Broadcast还是Interface方式都必须通过Activity中转，不可直接通信
 *
 * log标示，Activity的生命周期
 */
public class Communicate3Activity extends FragmentActivity {

    private PagerSlidingTabStrip strip;

    private ViewPagerAdapter adapter;

    private ViewPager viewPager;

    private List<Fragment> list = new ArrayList<Fragment>();

    private TabThreeFragment threeFragment;

    private CommFragment2 commFragment2;

    private MyBroadCastReceiver receiver;

    public static final String ACTION_SUB = "ACTION_SUB";
    public static final String ACTION_ADD = "ACTION_ADD";
    public static final String ACTION_CHANGE = "ACTION_CHANGE";
    public static final String ACTION_SWITCH = "ACTION_SWITCH";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate3);
        strip = (PagerSlidingTabStrip) findViewById(R.id.psTabStrip);
        viewPager = (ViewPager) findViewById(R.id.comm_vp);
        init();
        Log.d(CommonUtil.LOG_TAG_S, "onCreate");
    }

    private void init() {
        threeFragment = new TabThreeFragment();
        commFragment2 = new CommFragment2();
        list.add(new CommFragment3());
        list.add(threeFragment);
        list.add(commFragment2);

        List<String> titles = new ArrayList<String>();
        titles.add("one");
        titles.add("two");
        titles.add("three");

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list, titles, this);
        viewPager.setAdapter(adapter);
        strip.setViewPager(viewPager);
        registerReceiver();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ADD);
        filter.addAction(ACTION_SUB);
        filter.addAction(ACTION_CHANGE);
        filter.addAction(ACTION_SWITCH);
        receiver = new MyBroadCastReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.d(CommonUtil.LOG_TAG_S, "onDestroy");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(CommonUtil.LOG_TAG_S, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(CommonUtil.LOG_TAG_S, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(CommonUtil.LOG_TAG_S, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(CommonUtil.LOG_TAG_S, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(CommonUtil.LOG_TAG_S, "onRestart");
    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_SUB.equals(action)) {
                int index = Integer.parseInt(intent.getStringExtra("data"));
                sub(index);
            } else if (ACTION_ADD.equals(action)) {
                Hero hero = (Hero) intent.getSerializableExtra("data");
                add(hero);
            } else if (ACTION_CHANGE.equals(action)) {
                String text = intent.getStringExtra("data");
                changeText(text);
            } else if (ACTION_SWITCH.equals(action)) {
                int index = Integer.parseInt(intent.getStringExtra("data"));
                switchFrag(index);
            }


        }
    }


    public void sub(int position) {
        threeFragment.subOne(position);
    }

    public void add(Hero hero) {
        threeFragment.addOne(hero);
    }

    public void changeText(String text) {
        commFragment2.changeText(text);
    }

    public void switchFrag(int position) {
        viewPager.setCurrentItem(position);
    }

}