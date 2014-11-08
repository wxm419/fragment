package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroAdapter;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.MyLinearLayoutForListView;
import com.fheebiy.view.OverScrollView;
import com.fheebiy.widget.LinearLayoutForListView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 14-11-8.
 */
public class ScrollTestActivity extends Activity {

    private OverScrollView scrollView;

    public static final String TAG = "ScrollTestActivity";

    private LinearLayoutForListView listView;

    private List<Hero> list;

    private HeroLvAdapter adapter;

    private boolean isLoading;

    private LayoutInflater inflater;

    private RelativeLayout footer;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 12){
                list.addAll(CommonUtil.getInitData());
                listView.notifyDataSetChanged();
                isLoading = false;
                footer.setVisibility(View.GONE);
            }
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolltest);
        inflater = LayoutInflater.from(this);
        listView = (LinearLayoutForListView)findViewById(R.id.listview_layout);
        View footerView = inflater.inflate(R.layout.ent_refresh_footer,null);
        footer = (RelativeLayout)findViewById(R.id.footerview);
        footer.setVisibility(View.GONE);
       // listView.addFooterView(footerView);
        list = CommonUtil.getInitData();
        adapter = new HeroLvAdapter(this,list);
        listView.setAdapter(adapter);
        scrollView = (OverScrollView)findViewById(R.id.scrollview);
        scrollView.setOnScrollChangedListener(new OverScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                if(scrollView.isBottom()){
                    Log.d(TAG,"BOTTOM,BOTTOM,BOTTOM,BOTTOM,BOTTOM,");
                    if(!isLoading){
                       /* new Thread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(CommonUtil.getInitData());
                                handler.postDelayed(this,2000);
                            }
                        }).start();*/
                        footer.setVisibility(View.VISIBLE);
                        isLoading = true;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(12);
                            }
                        },3000);
                    }
                }
            }

            @Override
            public void refresh() {
                Toast.makeText(ScrollTestActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
                //Log.d(TAG,"刷新,刷新,刷新,刷新");
            }

            @Override
            public void loadMore(){

            }
        });


    }
}