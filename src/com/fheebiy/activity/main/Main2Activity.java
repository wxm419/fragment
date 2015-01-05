package com.fheebiy.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.fragment.TabFiveFragment;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;
import android.widget.TabHost.TabSpec;

/**
 * Created by bob zhou on 14-8-29.
 *
 * 首页的研究
 */
public class Main2Activity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    private LayoutInflater inflater;

    private int imags[] = {R.drawable.sy_selector,R.drawable.msg_selector,R.drawable.discovery_selector,R.drawable.my_selector};

    private String text[] = {"搜悦", "消息", "发现", "我的"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        inflater = LayoutInflater.from(this);
        TabSpec syTabSpec = mTabHost.newTabSpec("sy").setIndicator(getIndicatorView(0));
        TabSpec msgTabSpec = mTabHost.newTabSpec("msg").setIndicator(getIndicatorView(1));
        TabSpec disTabSpec = mTabHost.newTabSpec("discovery").setIndicator(getIndicatorView(2));
        TabSpec myTabSpec = mTabHost.newTabSpec("my").setIndicator(getIndicatorView(3));

        mTabHost.addTab(syTabSpec, TabTwoFragment.class, null);
        mTabHost.addTab(msgTabSpec, TabThreeFragment.class, null);
        mTabHost.addTab(disTabSpec, TabFourFragment.class, null);
        mTabHost.addTab(myTabSpec, TabFiveFragment.class, null);

    }


    private View getIndicatorView(int i){
        View view = inflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView)view.findViewById(R.id.indicator_img);
        TextView tv = (TextView)view.findViewById(R.id.indicator_text);
        img.setImageResource(imags[i]);
        tv.setText(text[i]);

        return view;
    }

}