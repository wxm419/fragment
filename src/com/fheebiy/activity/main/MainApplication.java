package com.fheebiy.activity.main;

import android.app.Application;
import android.content.Intent;
import com.fheebiy.service.PushService;

/**
 * Created by bob zhou on 15-3-24.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, PushService.class);
        startService(intent);
    }
}
