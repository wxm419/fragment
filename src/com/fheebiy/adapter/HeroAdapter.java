package com.fheebiy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.model.Hero;

import java.util.List;

/**
 * Created by Administrator on 14-11-9.
 */
public class HeroAdapter extends LinearLayoutBaseAdapter {

    private Context ctx;

    private List<Hero> list;


    public HeroAdapter(Context context, List<Hero> list) {
        super(context, list);
        this.ctx = context;
        this.list = list;
    }

    @Override
    public View getView(int position) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.heroitem,null);
        TextView nameTv = (TextView)view.findViewById(R.id.hero_name_tv);
        TextView skillTv = (TextView)view.findViewById(R.id.hero_skill_tv);
        TextView fromTv = (TextView)view.findViewById(R.id.hero_from_tv);
        Hero hero = list.get(position);
        if(hero != null){
            nameTv.setText(hero.getName());
            skillTv.setText(hero.getSkill());
            fromTv.setText(hero.getFrom());
        }
        return view;
    }
}
