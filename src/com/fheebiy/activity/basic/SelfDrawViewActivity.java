package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.Bundle;import com.fheebiy.R;

/**
 * Created by Lenovo on 15-2-9.
 *
 * 自定义View-----计数器-----自绘控件，具体请看CounterView
 *
 */
public class SelfDrawViewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_draw_view);
    }
}