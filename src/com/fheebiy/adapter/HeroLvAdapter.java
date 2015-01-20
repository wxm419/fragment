package com.fheebiy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.model.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-8.
 */
public class HeroLvAdapter extends BaseAdapter{

    private Context context;


    private List<Hero> list = new ArrayList<Hero>();


    public HeroLvAdapter(Context context, List<Hero> list) {
        this.context = context;
        this.list = list;
    }

    public HeroLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.heroitem,null);
        TextView nameTv = (TextView)view.findViewById(R.id.hero_name_tv);
        TextView skillTv = (TextView)view.findViewById(R.id.hero_skill_tv);
        TextView fromTv = (TextView)view.findViewById(R.id.hero_from_tv);

        Hero hero = list.get(i);
        if(hero != null){
            nameTv.setText(hero.getName());
            skillTv.setText(hero.getSkill());
            fromTv.setText(hero.getFrom());
        }
        view.setBackgroundResource(R.drawable.list_item_selector);
        return view;
    }


    public void setList(List<Hero> list){       //第一次加载或者下拉刷新
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public void addList(List<Hero> list){       //加载更多
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
