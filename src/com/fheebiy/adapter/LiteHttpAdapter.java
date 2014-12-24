package com.fheebiy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.model.LiteHttpUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-12-24.
 */
public class LiteHttpAdapter extends BaseAdapter {

    private Context ctx;

    private List<LiteHttpUrl> list = new ArrayList<LiteHttpUrl>();


    public LiteHttpAdapter(Context ctx) {
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


    public void setList(List<LiteHttpUrl> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<LiteHttpUrl> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.litehttp_item, null);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.nameTv = (TextView)convertView.findViewById(R.id.lite_http_name);
        viewHolder.urlTv = (TextView)convertView.findViewById(R.id.lite_http_url);

        LiteHttpUrl  liteHttpUrl = list.get(position);

        viewHolder.nameTv.setText(liteHttpUrl.getName());
        viewHolder.urlTv.setText(liteHttpUrl.getUrl());
        convertView.setBackgroundResource(R.drawable.list_item_selector);
        return convertView;
    }



    class ViewHolder{

        public TextView nameTv;

        public TextView urlTv;

    }


}
