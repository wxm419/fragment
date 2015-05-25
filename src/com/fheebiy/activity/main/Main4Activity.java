package com.fheebiy.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabSixFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;
import com.fheebiy.view.FragmentTabHost;
import com.fheebiy.view.MyFragmentTabHost;
import com.fheebiy.view.MyTabIndicator;
import com.fheebiy.view.TabIndicator;

/**
 * Created by zhouwenbo on 15/5/24.
 * 比较完美的首页tab
 */
public class Main4Activity extends FragmentActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;

    private int imags[] = {R.drawable.sy_selector,R.drawable.msg_selector,R.drawable.discovery_selector,R.drawable.my_selector};

    private String mTextviewArray[] = {"搜悦", "消息", "发现", "我的"};

    private Class fragmentArray[] ={TabSixFragment.class, TabFourFragment.class, TabThreeFragment.class,TabTwoFragment.class};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        initView();
    }


    /**
     * 初始化底部选项卡
     */
    private void initTab() {


    }

    private void initView(){
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getIndicatorView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
           // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    private View getIndicatorView(int i){
        View view = layoutInflater.inflate(R.layout.my_tab_indicator,null);
        ImageView img = (ImageView)view.findViewById(R.id.indicator_img);
        TextView tv = (TextView)view.findViewById(R.id.indicator_text);
        img.setImageResource(imags[i]);
        tv.setText(mTextviewArray[i]);

        return view;
    }

}