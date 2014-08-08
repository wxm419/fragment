package com.fheebiy.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.view.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-4.
 */
public class TabThreeFragment extends Fragment {


    public static final String TAG = "TabThreeFragment";

    private ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        listView = (PullToRefreshListView)view.findViewById(R.id.listView);
        List<Hero> list = getInitData();
        HeroLvAdapter adapter = new HeroLvAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }


    public List<Hero> getInitData(){
        List<Hero> list = new ArrayList<Hero>();

        for(int i = 0; i< 10; i++){
            Hero hero = new Hero();
            hero.setName("杨过"+i);
            hero.setSkill("黯然销魂掌");
            hero.setFrom("神雕侠侣");
            list.add(hero);
        }

        return list;

    }
}
