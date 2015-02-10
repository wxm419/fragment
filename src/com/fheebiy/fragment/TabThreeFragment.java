package com.fheebiy.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.PullToRefreshListView;

/**
 * Created by bob zhou on 14-8-4.
 *
 * 可以在LOG中查看Fragment的完整生命周期
 */
public class TabThreeFragment extends Fragment {

    private PullToRefreshListView listView;

    private HeroLvAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(CommonUtil.LOG_TAG, "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        init(view);
        Log.d(CommonUtil.LOG_TAG, "onCreateView");
        return view;
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.d(CommonUtil.LOG_TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(CommonUtil.LOG_TAG, "onResume");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(CommonUtil.LOG_TAG, "onStop");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(CommonUtil.LOG_TAG, "onStart");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(CommonUtil.LOG_TAG, "onDestroy");

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(CommonUtil.LOG_TAG, "onActivityCreated");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(CommonUtil.LOG_TAG, "onAttach");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(CommonUtil.LOG_TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(CommonUtil.LOG_TAG, "onDetach");
    }

    private void init(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.listView);
        adapter = new HeroLvAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
    }

    public void addOne(Hero hero) {
        adapter.addOne(hero);
    }

    public void subOne(int position) {
        adapter.subOne(position);
    }
}
