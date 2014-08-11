package com.fheebiy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.adapter.SlideToDelLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.view.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-11.
 */
public class SlideToDelLvActivity extends FragmentActivity {

    private PullToRefreshListView listView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidetodellv);
        listView = (PullToRefreshListView)findViewById(R.id.del_listView);
        List<Hero> list = getInitData();
        SlideToDelLvAdapter adapter = new SlideToDelLvAdapter(list,this);
        listView.setAdapter(adapter);
        listView.requestDisallowInterceptTouchEvent(true);
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