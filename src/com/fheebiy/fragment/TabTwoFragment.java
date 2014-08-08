package com.fheebiy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-7-30.
 */
public class TabTwoFragment extends Fragment {

    static final String TAG = "TabTwoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,motionEvent.getAction()+"");
                return false;
            }
        });


        return view;



    }




}
