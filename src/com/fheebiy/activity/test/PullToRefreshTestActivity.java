package com.fheebiy.activity.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.test.MyRefreshableView;
import com.fheebiy.util.CommonUtil;

/**
 * Created by bob zhou on 15-3-18.
 */
public class PullToRefreshTestActivity extends Activity {


    private ListView listView;

    private HeroLvAdapter adapter;

    MyRefreshableView refreshableView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullto_refresh_test);

        refreshableView = (MyRefreshableView) findViewById(R.id.refreshable_view);
        listView = (ListView)findViewById(R.id.list_view);
        adapter = new HeroLvAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
        refreshableView.setRefreshListener(new MyRefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);
    }
}