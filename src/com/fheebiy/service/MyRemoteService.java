package com.fheebiy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.fheebiy.aidl.MyAIDLService;

/**
 * Created by bob zhou on 15-1-29.
 */
public class MyRemoteService extends Service {

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if(!TextUtils.isEmpty(str)){
                return str.toUpperCase();
            }
            return null;
        }
    };

    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
