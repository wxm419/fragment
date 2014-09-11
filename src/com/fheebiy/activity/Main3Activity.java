package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.FrameLayout;
import com.fheebiy.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.fheebiy.fragment.TabFiveFragment;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;


/**
 * Created by bob zhou on 14-9-11.
 */
public class Main3Activity extends FragmentActivity {
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {TabTwoFragment.class,TabThreeFragment.class,TabFourFragment.class,TabFiveFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.sy_selector,R.drawable.msg_selector,R.drawable.discovery_selector,
            R.drawable.my_selector};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"搜悦", "消息", "发现", "我的"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        initView();
    }

    /**
     * 初始化组件
     */
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
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.background_tab);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }


}