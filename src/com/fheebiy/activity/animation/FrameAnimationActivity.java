package com.fheebiy.activity.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-2-6.
 */
public class FrameAnimationActivity extends Activity implements View.OnClickListener{

    private Animation prizeAni;

    private ImageView img;

    private Button btn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation);

        prizeAni = AnimationUtils.loadAnimation(this, R.anim.addone);
        prizeAni.setFillAfter(true);
        img = (ImageView)findViewById(R.id.heart_img);
        btn = (Button)findViewById(R.id.start_btn);
        btn.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.heart_img){
            img.startAnimation(prizeAni);
        }

        if(view.getId() == R.id.start_btn){
            prizeAni.reset();
            img.startAnimation(prizeAni);
        }
    }
}