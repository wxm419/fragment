package com.fheebiy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.ExpandLinearLayout;

/**
 * Created by zhouwenbo on 15/5/23.
 */
public class TabSevenFragment extends Fragment {

    private ListView listView;
    private HeroLvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab7, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.listview_layout);
        adapter = new HeroLvAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
    }

    public ListView getListView() {
        return listView;
    }
}
