package com.fheebiy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Lenovo on 15-3-24.
 */
public class PushService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new MyThread().start();
        Log.d(CommonUtil.LOG_TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class MyThread extends Thread{

        @Override
        public void run() {
            try {
              /*  Socket socket = new Socket("192.168.31.94", 9998);
                socket.setSoTimeout(5000);*/
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.31.94", 9998),10000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                String str = reader.readLine();
                Log.d(CommonUtil.LOG_TAG, "str=" + str);
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
