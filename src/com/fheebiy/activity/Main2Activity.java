package com.fheebiy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;
import com.fheebiy.R;
import com.fheebiy.fragment.TabFiveFragment;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;
import com.fheebiy.view.TabIndicator;
import android.widget.TabHost.TabSpec;

/**
 * Created by bob zhou on 14-8-29.
 */
public class Main2Activity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        Bundle b = new Bundle();
        b.putString("key", "sy");
        TabIndicator syIndicator = new TabIndicator(this);
        syIndicator.setIconRes(R.drawable.sy_selector);
        syIndicator.setTitle("搜悦");
        TabSpec syTabSpec = mTabHost.newTabSpec("sy");
        syTabSpec.setIndicator(syIndicator);


        b = new Bundle();
        b.putString("key", "msg");
        TabIndicator msgIndicator = new TabIndicator(this);
        syIndicator.setIconRes(R.drawable.msg_selector);
        syIndicator.setTitle("消息");
        TabSpec msgTabSpec = mTabHost.newTabSpec("msg");
        msgTabSpec.setIndicator(msgIndicator);

        mTabHost.addTab(syTabSpec, TabTwoFragment.class, b);
        mTabHost.addTab(msgTabSpec, TabThreeFragment.class, b);


      /*  b = new Bundle();
        b.putString("key", "discovery");
        TabIndicator disIndicator = new TabIndicator(this);
        syIndicator.setIconRes(R.drawable.discovery_selector);
        syIndicator.setTitle("发现");
        mTabHost.addTab(mTabHost.newTabSpec("discovery").setIndicator(disIndicator), TabFourFragment.class, b);


        b = new Bundle();
        b.putString("key", "my");
        TabIndicator myIndicator = new TabIndicator(this);
        syIndicator.setIconRes(R.drawable.my_selector);
        syIndicator.setTitle("我的");
        mTabHost.addTab(mTabHost.newTabSpec("my").setIndicator(myIndicator), TabFiveFragment.class, b);*/


    }
}