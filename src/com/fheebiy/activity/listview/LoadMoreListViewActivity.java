package com.fheebiy.activity.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.util.CommonUtil;

/**
 * Created by Lenovo on 15-3-23.
 */
public class LoadMoreListViewActivity extends Activity {

    private ListView listView;

    private Button button;

    private HeroLvAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_load_more);
        listView = (ListView)findViewById(R.id.listView);
        button = (Button)findViewById(R.id.btn);

        adapter = new HeroLvAdapter(this);

        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
    }



}