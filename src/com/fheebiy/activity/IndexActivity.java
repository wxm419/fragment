package com.fheebiy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.fheebiy.R;


/**
 * created by bob zhou 2014.08.01
 *
 * 用于测试fragment
 */
public class IndexActivity extends FragmentActivity {

  /*  private Button btn1;

    private Button btn2;

    private TabOneFragment oneFragment;

    private TabTwoFragment twoFragment;

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
    }


}
