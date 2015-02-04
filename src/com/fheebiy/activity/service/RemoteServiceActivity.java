package com.fheebiy.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.aidl.MyAIDLService;
import com.fheebiy.service.MyRemoteService;
import com.fheebiy.util.Log;

/**
 * Created by bob zhou on 15-1-29.
 * <p/>
 * 远程服务
 * 需要另外一个应用程序，已经在git仓库里了，叫做RemoteClient
 */
public class RemoteServiceActivity extends Activity implements View.OnClickListener {

    private AQuery aq;

    private MyAIDLService myAIDLService;

    public static final String TAG = "RemoteServiceActivity";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int n = myAIDLService.plus(12, 6);
                String str = myAIDLService.toUpperCase("i love you");
                Log.d(TAG, n);
                Log.d(TAG, str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_service);
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
                Intent startIntent = new Intent(this, MyRemoteService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyRemoteService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyRemoteService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

        }
    }

}
