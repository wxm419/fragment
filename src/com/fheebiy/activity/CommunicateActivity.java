package com.fheebiy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.fheebiy.R;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.communicate.CommFragment1;
import com.fheebiy.fragment.communicate.CommFragment2;
import com.fheebiy.model.Hero;
import android.support.v4.app.FragmentManager;
/**
 *
 * Activity fragment 之间的通信
 * Created by bob zhou on 14-9-24.
 */
public class CommunicateActivity extends FragmentActivity implements CommFragment1.AddOrSubListViewListener,CommFragment1.ChangeTextListener {

    CommFragment2 fragment2;

    TabThreeFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate);
        init();
    }


    @Override
    public void sub(int position) {
        fragment3.subOne(position);
    }

    @Override
    public void add(Hero hero) {
        fragment3.addOne(hero);
    }

    private void init(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment2 =  (CommFragment2)fragmentManager.findFragmentById(R.id.fragment2);
        fragment3 =  (TabThreeFragment)fragmentManager.findFragmentById(R.id.fragment3);
    }

    @Override
    public void changeText(String text) {
        fragment2.changeText(text);
    }
}
