package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.fheebiy.R;
import com.fheebiy.fragment.TabOneFragment;
import com.fheebiy.fragment.TabTwoFragment;

public class IndexActivity extends FragmentActivity implements View.OnClickListener {

    private Button btn1;

    private Button btn2;

    private TabOneFragment oneFragment;

    private TabTwoFragment twoFragment;

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;



    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                break;
            case R.id.btn2:

                break;
        }

    }
}
