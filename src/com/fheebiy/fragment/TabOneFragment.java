package com.fheebiy.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-7-30.
 */
public class TabOneFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       /* View view = inflater.inflate(R.id.tab1,null);
        return view;*/
        return inflater.inflate(R.layout.tab1, container, false);
    }
}
