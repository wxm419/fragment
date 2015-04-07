package com.fheebiy.activity.animation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.fheebiy.R;
import com.fheebiy.view.CommLoadingView;

/**
 * Created by Lenovo on 15-3-26.
 */
public class PropertyAnimationActivity extends Activity {

    private ImageView imageView;

    private CommLoadingView loadingView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_anima);

        imageView = (ImageView) findViewById(R.id.imageview);
        loadingView = (CommLoadingView)findViewById(R.id.loading_view);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.showNoData();
            }
        },3000);
    }

    public void rotateAnimRun(View view) {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

}