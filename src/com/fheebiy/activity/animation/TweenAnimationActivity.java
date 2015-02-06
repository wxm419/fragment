package com.fheebiy.activity.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-2-6.
 */
public class TweenAnimationActivity extends Activity {

    private ImageView arrowIv;

    private ImageView purpleIv;

    private ImageView greenIv;

    private ImageView redIv;

    private RelativeLayout arrowLayout;

    private Animation rotateAni;

    private Animation scaleAni;

    private Animation alphaAni;

    private Animation transAni;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween_animation);

        arrowIv = (ImageView) findViewById(R.id.arrow_iv);
        arrowLayout = (RelativeLayout) findViewById(R.id.arrow_layout);

        purpleIv = (ImageView) findViewById(R.id.purple_iv);
        greenIv = (ImageView) findViewById(R.id.green_iv);
        redIv = (ImageView) findViewById(R.id.red_iv);


        rotateAni = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);
        alphaAni = AnimationUtils.loadAnimation(this, R.anim.test_alpha);
        scaleAni = AnimationUtils.loadAnimation(this, R.anim.test_scale);
        transAni = AnimationUtils.loadAnimation(this, R.anim.test_tanslate);

        rotateAni.setFillAfter(true);       //很有意思的是，你再xml配置此属性，貌似没有其作用，充满疑问!

        bindListener();

    }


    private void bindListener() {

        arrowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowIv.startAnimation(rotateAni);
            }
        });

        purpleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpleIv.startAnimation(alphaAni);
            }
        });

        greenIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenIv.startAnimation(transAni);
            }
        });

        redIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redIv.startAnimation(scaleAni);
            }
        });


    }
}