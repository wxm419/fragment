package com.fheebiy.activity.basic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;
import com.fheebiy.view.TitleView;

import java.net.URI;

/**
 * Created by Lenovo on 15-2-9.
 * 组合控件 非常简单的一个demo，看看就行
 * 此Activity在xml中配置了，可以远程调用,在RemoteClient中成功调用了此Activity
 */
public class CombinationViewActivity extends Activity implements View.OnClickListener{

    private TitleView titleView;

    private AQuery aq;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combination_view);
        aq = new AQuery(this);
        titleView = (TitleView)findViewById(R.id.title_view);
        bindListener();
        getDataFromRemoteClient();
    }


    private void bindListener(){
        aq.id(R.id.btn1).clicked(this);
        aq.id(R.id.btn2).clicked(this);
        aq.id(R.id.btn3).clicked(this);
        aq.id(R.id.btn4).clicked(this);

        titleView.setMenuClickListener(new TitleView.MenuClickListener() {
            @Override
            public void onMenuClick(View view) {
                Log.d(CommonUtil.LOG_TAG,"titleView setMenuClickListener");
            }
        });
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.btn1:
                 titleView.setTitleText("gogogo");
                 break;
             case R.id.btn2:
                 titleView.setTitleTextColor(Color.parseColor("#FFFFFF"));
                 break;
             case R.id.btn3:
                 titleView.setTitleTextSize(30f);
                 break;
             case R.id.btn4:
                 titleView.showMenu(false);
                 break;

         }
    }

    private void getDataFromRemoteClient(){
        Intent intent = getIntent();
        if(intent != null){
            Uri uri = intent.getData();
            String msg = intent.getStringExtra("msg");
            Log.d(CommonUtil.LOG_TAG, msg);
        }

    }


}