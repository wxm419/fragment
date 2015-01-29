package com.fheebiy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.fheebiy.util.Log;

/**
 * Created by bob zhou on 15-1-5.
 * <p/>
 * 回调中onStart 已经过时，不再继续使用，故没有必要纠结了
 */
public class MyService extends Service {

    public static final String TAG = "MyService";

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload");
        }
    }

}
