package com.fheebiy.activity.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import com.fheebiy.R;
import com.fheebiy.activity.basic.RightSlideBackActivity;
import com.fheebiy.broadcast.SmsBroadcastReceiver;

/**
 * Created by Lenovo on 15-3-12.
 * LocalBroadcastManager所发送的广播action，只能与注册到LocalBroadcastManager中BroadcastReceiver产生互动。
 * LocalBroadcastManager只适用于代码间的注册和发送，在xml中配置的广播接收器是收不到LocalBroadcastManager发送的广播的
 */
public class BroadcastActivity extends RightSlideBackActivity implements View.OnClickListener{

    private LocalBroadcastManager localBroadcastManager;

    private Button btn;
    private Button btn1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        setCanClose(true);
        btn = (Button)findViewById(R.id.sendBtn);
        btn1 = (Button)findViewById(R.id.sendBtn1);

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
/*
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.fheebiy.receiver.msg.coming");
        localBroadcastManager.registerReceiver(new SmsBroadcastReceiver(),filter);*/

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sendBtn){
            Intent intent = new Intent("com.fheebiy.receiver.msg.coming");
            intent.putExtra("msg", "msg from local");
            localBroadcastManager.sendBroadcast(intent);
        }
        if(v.getId() == R.id.sendBtn1){
            Intent intent = new Intent("com.fheebiy.receiver.msg.go");
            intent.putExtra("msg", "msg from local");
            sendOrderedBroadcast(intent,null);
        }
    }
}