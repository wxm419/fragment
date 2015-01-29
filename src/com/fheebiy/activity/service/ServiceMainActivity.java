package com.fheebiy.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.service.MyService;

/**
 * Created by bob zhou on 15-1-5.
 * <p/>
 * Service demo
 */
public class ServiceMainActivity extends Activity implements View.OnClickListener {

    private AQuery aq;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main);
        aq = new AQuery(this);
        bindListener();

    }

    public void bindListener() {
        aq.id(R.id.start_service).clicked(this);
        aq.id(R.id.stop_service).clicked(this);
        aq.id(R.id.bind_service).clicked(this);
        aq.id(R.id.unbind_service).clicked(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

        }
    }
}