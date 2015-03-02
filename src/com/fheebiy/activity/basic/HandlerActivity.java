package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-3-2.
 */
public class HandlerActivity extends Activity {

    private Button btn;

    private TextView tv;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    private static Handler handler2 = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.textview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doPost();
                    }
                }).start();
            }
        });
    }

    private void doPost(){
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
               tv.setText("Handler用post(Runnable r)方式，实现异步消息处理机制");
            }
        },1000);
    }


}