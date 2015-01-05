package com.fheebiy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.androidquery.AQuery;
import com.fheebiy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 14-12-26.
 */
public class AQueryAdapter extends BaseAdapter {

    private Context context;

    private List<String> list = new ArrayList<String>();

    private AQuery aQuery;


    public AQueryAdapter(Context context) {
        this.context = context;

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
            convertView = View.inflate(context, R.layout.aquery_item, null);
        }
        aQuery = new AQuery(convertView);
        aQuery.id(R.id.aq_item_name).text(list.get(position));
        return convertView;
    }


    public void setList(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<String> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
