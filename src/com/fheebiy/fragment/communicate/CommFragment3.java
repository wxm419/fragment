package com.fheebiy.fragment.communicate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.fheebiy.R;
import com.fheebiy.activity.communicate.Communicate3Activity;
import com.fheebiy.model.Hero;

/**
 *
 * Created by bob zhou on 14-9-24.
 * Fragment Activity之间的通信,采用Broadcast方式
 * Fragment之间通信，无论采用Broadcast还是Interface方式都必须通过Activity中转，不可直接通信
 *
 */
public class CommFragment3 extends Fragment implements View.OnClickListener {

    private Button addBtn;

    private Button subBtn;

    private Button changeTextBtn;

    private Button switchBtn;

    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comm_fragment3, container, false);
        initViews(view);
        bindListener();
        return view;
    }

    private void initViews(View view) {
        addBtn = (Button) view.findViewById(R.id.add_one_btn);
        subBtn = (Button) view.findViewById(R.id.sub_one_btn);
        changeTextBtn = (Button) view.findViewById(R.id.change_text_btn);
        switchBtn = (Button) view.findViewById(R.id.switch_frag_btn);
        context = getActivity();
    }

    private void bindListener() {
        addBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        changeTextBtn.setOnClickListener(this);
        switchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_one_btn:
                Hero hero = new Hero();
                hero.setName("欧阳锋");
                hero.setSkill("蛤蟆功");
                hero.setFrom("射雕英雄传");
                sentBroadCastToActivity(Communicate3Activity.ACTION_ADD,hero);
                break;
            case R.id.sub_one_btn:
                sentBroadCastToActivity(Communicate3Activity.ACTION_SUB, "0");
                break;
            case R.id.change_text_btn:
                sentBroadCastToActivity(Communicate3Activity.ACTION_CHANGE, "Love you, xie");
                break;
            case R.id.switch_frag_btn:
                sentBroadCastToActivity(Communicate3Activity.ACTION_SWITCH, "3");
                break;
            default:
                break;
        }
    }


    public void sentBroadCastToActivity(String action, Hero hero) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("data", hero);
        context.sendBroadcast(intent);
    }


    public void sentBroadCastToActivity(String action, String data) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("data", data);
        context.sendBroadcast(intent);
    }

}
