package com.fheebiy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.activity.aquery.AQueryActivity;
import com.fheebiy.activity.basic.PopupWindowActivity;
import com.fheebiy.activity.communicate.Communicate2Activity;
import com.fheebiy.activity.communicate.Communicate3Activity;
import com.fheebiy.activity.communicate.CommunicateActivity;
import com.fheebiy.activity.lite.LiteHttpActivity;
import com.fheebiy.activity.listview.SwipeRefreshLayoutActivity;
import com.fheebiy.activity.main.*;
import com.fheebiy.activity.listview.SlideToDelLvActivity;
import com.fheebiy.activity.other.ThemeStyleActivity;
import com.fheebiy.activity.overscroll.ScrollTestActivity;
import com.fheebiy.activity.pulltorefresh.PullMainActivity;
import com.fheebiy.activity.vp.VpAnimationActivity;
import com.fheebiy.activity.vp.VpComplexActivity;
import com.fheebiy.activity.vp.VpStripActivity;

/**
 * Created by bob zhou on 14-7-30.
 */
public class TabOneFragment extends Fragment {

    private Button btn1;

    private Button btn2;

    private Button btn3;

    private Button btn4;

    private Button btn5;

    private Button btn6;

    private Button btn7;

    private Button btn8;

    private Button btn9;

    private Button btn10;

    private Button btn11;

    private Button btn12;

    private Button btn13;

    private Button btn14;

    private Button btn15;

    private Button btn16;

    private Button btn17;

    private Button btn18;

    private AQuery aq;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
       // btn1 = (Button)view.findViewById(R.id.tb1_btn1);
        aq = new AQuery(view);
        btn2 = (Button)view.findViewById(R.id.tb1_btn2);
        btn3 = (Button)view.findViewById(R.id.tb1_btn3);
        btn4 = (Button)view.findViewById(R.id.tb1_btn4);
        btn5 = (Button)view.findViewById(R.id.tb1_btn5);
        btn6 = (Button)view.findViewById(R.id.tb1_btn6);
        btn7 = (Button)view.findViewById(R.id.tb1_btn7);
        btn8 = (Button)view.findViewById(R.id.tb1_btn8);
        btn9 = (Button)view.findViewById(R.id.tb1_btn9);
        btn10 = (Button)view.findViewById(R.id.tb1_btn10);
        btn11 = (Button)view.findViewById(R.id.tb1_btn11);
        btn12 = (Button)view.findViewById(R.id.tb1_btn12);
        btn13 = (Button)view.findViewById(R.id.tb1_btn13);
        btn14 = (Button)view.findViewById(R.id.tb1_btn14);
        btn15 = (Button)view.findViewById(R.id.tb1_btn15);
        btn16 = (Button)view.findViewById(R.id.tb1_btn16);
        btn17 = (Button)view.findViewById(R.id.tb1_btn17);
        //btn18 = (Button)view.findViewById(R.id.tb1_btn18);
        btn18 = aq.id(R.id.tb1_btn18).getButton();

    /*    btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalCenterActivity.class);
                startActivity(intent);
            }
        });*/

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VpComplexActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VpAnimationActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemeStyleActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SlideToDelLvActivity.class);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VpStripActivity.class);
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main3Activity.class);
                startActivity(intent);
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommunicateActivity.class);
                startActivity(intent);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Communicate2Activity.class);
                startActivity(intent);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Communicate3Activity.class);
                startActivity(intent);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SwipeRefreshLayoutActivity.class);
                startActivity(intent);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScrollTestActivity.class);
                startActivity(intent);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PullMainActivity.class);
                startActivity(intent);
            }
        });


        aq.id(R.id.tb1_btn17).clicked(this, "gotoLiteHttpActivity");


        aq.id(R.id.tb1_btn18).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AQueryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    public void gotoLiteHttpActivity(){
        Intent intent = new Intent(getActivity(), LiteHttpActivity.class);
        startActivity(intent);
    }

}
