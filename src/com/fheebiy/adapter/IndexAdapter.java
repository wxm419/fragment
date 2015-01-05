package com.fheebiy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.model.UIModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 15-1-5.
 */
public class IndexAdapter extends BaseAdapter {

    private Context ctx;

    private List<UIModel> list = new ArrayList<UIModel>();

    public IndexAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.index_item, null);
        }
        AQuery aq = new AQuery(convertView);
        aq.id(R.id.index_item_name).text(list.get(position).getName());
        convertView.setBackgroundResource(R.drawable.list_item_selector);
        return convertView;
    }


    public void setList(List<UIModel> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public void addList(List<UIModel> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
