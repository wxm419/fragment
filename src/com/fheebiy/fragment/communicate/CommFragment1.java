package com.fheebiy.fragment.communicate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.model.Hero;

/**
 * Created by bob zhou on 14-9-24.
 *
 * 采用接口的方式实现Fragment Activity 之间通信,采用Interface方式
 * Fragment之间通信，无论采用Broadcast还是Interface方式都必须通过Activity中转，不可直接通信
 *
 */
public class CommFragment1 extends Fragment implements View.OnClickListener {

    private Button addBtn;

    private Button subBtn;

    private Button changeTextBtn;

    private Button switchBtn;

    private AddOrSubListViewListener callBack;

    private ChangeTextListener changeTextListener;

    private SwitchFragListener switchFragListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comm_fragment1, container, false);
        initViews(view);
        bindListener();
        return view;
    }

    private void initViews(View view) {
        addBtn = (Button) view.findViewById(R.id.add_one_btn);
        subBtn = (Button) view.findViewById(R.id.sub_one_btn);
        changeTextBtn = (Button) view.findViewById(R.id.change_text_btn);
        switchBtn = (Button) view.findViewById(R.id.switch_frag_btn);
        callBack = (AddOrSubListViewListener) getActivity();
        changeTextListener = (ChangeTextListener)getActivity();
        switchFragListener = (SwitchFragListener)getActivity();
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
                callBack.add(hero);
                break;
            case R.id.sub_one_btn:
                callBack.sub(0);
                break;
            case R.id.change_text_btn:
                changeTextListener.changeText("love you, xie");
                break;
            case R.id.switch_frag_btn:
                switchFragListener.switchFrag(2);
                break;
            default:
                break;
        }
    }


    public interface AddOrSubListViewListener {

        public void sub(int position);

        public void add(Hero hero);
    }


    public interface  ChangeTextListener{
        public void changeText(String text);
    }

    public interface SwitchFragListener{
        public void switchFrag(int position);
    }

}
