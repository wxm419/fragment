package com.fheebiy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.fheebiy.R;
import com.fheebiy.activity.IndexActivity;
import com.fheebiy.activity.ThemeStyleActivity;
import com.fheebiy.activity.VpActivity;
import com.fheebiy.activity.VpComplexActivity;

/**
 * Created by bob zhou on 14-7-30.
 */
public class TabOneFragment extends Fragment {

    private Button btn1;

    private Button btn2;

    private Button btn3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       /* View view = inflater.inflate(R.id.tab1,null);
        return view;*/
        View view = inflater.inflate(R.layout.tab1, container, false);
        btn1 = (Button)view.findViewById(R.id.tb1_btn1);
        btn2 = (Button)view.findViewById(R.id.tb1_btn2);
        btn3 = (Button)view.findViewById(R.id.tb1_btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VpActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(getActivity(), ThemeStyleActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
