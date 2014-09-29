package com.fheebiy.fragment.communicate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-9-24.
 */
public class CommFragment2 extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comm_fragment2, container, false);
        textView = (TextView) view.findViewById(R.id.change_tv);
        return view;
    }

    public void changeText(String text){
        if(textView != null){
            textView.setText(text);
        }
    }
}