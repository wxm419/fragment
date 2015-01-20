package com.fheebiy.activity.other;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;

/**
 * 做个人中心效果时的demo
 * 采用最新的support v4 jar包才有此API
 *
 */
public class SwipeRefreshLayoutActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeLayout;
    private ListView listView;
    HeroLvAdapter adapter;

    List<Hero> list = new ArrayList<Hero>();
    private boolean isRefresh = false;//是否刷新中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiperefresh);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        //加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
        swipeLayout.setColorScheme(android.R.color.white,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        listView = (ListView)findViewById(R.id.listView);
        list = getInitData();
        adapter = new HeroLvAdapter(this, list);
        listView.setAdapter(adapter);
    }

    public void onRefresh() { if(!isRefresh){ isRefresh = true;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);

                adapter.notifyDataSetChanged(); isRefresh= false;
            }
        }, 3000); }
    }

    public List<Hero> getInitData() {
        List<Hero> heros = new ArrayList<Hero>();

        for (int i = 0; i < 10; i++) {
            Hero hero = new Hero();
            hero.setName("杨过" + i);
            hero.setSkill("黯然销魂掌");
            hero.setFrom("神雕侠侣");
            heros.add(hero);
        }

        return heros;
    }
}
