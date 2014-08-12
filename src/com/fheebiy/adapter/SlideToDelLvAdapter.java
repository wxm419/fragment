package com.fheebiy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.model.Hero;
import com.fheebiy.view.MySlideView;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by bob zhou on 14-8-11.
 */
public class SlideToDelLvAdapter extends BaseAdapter {

    private List<Hero> list;

    private Context context;

    private MySlideView mLastSlideViewWithStatusOn;

    public SlideToDelLvAdapter(List<Hero> list, Context context) {
        this.list = list;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MySlideView slideView = (MySlideView) convertView;
        ViewHolder viewHolder;
        if (slideView == null) {
            slideView = new MySlideView(context);
            View itemView = inflater.inflate(R.layout.heroitem, null);
            slideView.setContentView(itemView);
            viewHolder = new ViewHolder(slideView);
            slideView.setOnSlideListener(new MySlideView.OnSlideListener() {
                @Override
                public void onSlide(View view, int status) {
                    if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
                        mLastSlideViewWithStatusOn.shrink();
                    }

                    if (status == SLIDE_STATUS_ON) {
                        mLastSlideViewWithStatusOn = (MySlideView) view;
                    }
                }
            });
            slideView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) slideView.getTag();
        }

        Hero hero = list.get(i);
        hero.setMySlideView(slideView);
        if(hero != null){
            viewHolder.nameTv.setText(hero.getName());
            viewHolder.fromTv.setText(hero.getFrom());
            viewHolder.skillTv.setText(hero.getSkill());
        }

        return slideView;
    }


    private class ViewHolder {

        public TextView nameTv;
        public TextView skillTv;
        public TextView fromTv;

        public MySlideView mySlideView;

        public ViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.hero_name_tv);
            skillTv = (TextView) view.findViewById(R.id.hero_skill_tv);
            fromTv = (TextView) view.findViewById(R.id.hero_from_tv);
        }

    }
}
