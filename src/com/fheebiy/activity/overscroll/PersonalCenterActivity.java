package com.fheebiy.activity.overscroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.BounceListView;
import com.fheebiy.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-11-8.
 *
 * 中搜搜悦项目，个人中心demo，暂时未用，也不成熟
 */
public class PersonalCenterActivity extends Activity {


    private ListView listView;

    List<Hero> list = new ArrayList<Hero>();

    private LinearLayout headerView;

    private ImageView bgImg;

    LayoutInflater inflater;

    HeroLvAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percenter);

        listView = (ListView)findViewById(R.id.listView);
        inflater= LayoutInflater.from(this);
        headerView = (LinearLayout)inflater.inflate(R.layout.percenter_headerview,null);
        listView.addHeaderView(headerView);
        list = getInitData();

        adapter = new HeroLvAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(list);
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