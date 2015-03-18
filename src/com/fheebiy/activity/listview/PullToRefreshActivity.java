package com.fheebiy.activity.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.RefreshableView;

/**
 * Created by bob zhou on 15-3-17.
 *
 * 比较完美的pull to refresh list view
 */
public class PullToRefreshActivity extends Activity { 


    private ListView listView;

    private HeroLvAdapter adapter;

    RefreshableView refreshableView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullto_refresh);

        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        listView = (ListView)findViewById(R.id.list_view);
        adapter = new HeroLvAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 100);
    }
}