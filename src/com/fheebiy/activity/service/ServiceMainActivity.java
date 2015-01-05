package com.fheebiy.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
                break;
            case R.id.unbind_service:
                break;

        }
    }
}