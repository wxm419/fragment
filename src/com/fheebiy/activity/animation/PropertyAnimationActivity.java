package com.fheebiy.activity.animation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-3-26.
 */
public class PropertyAnimationActivity extends Activity {

    private ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_anima);

        imageView = (ImageView)findViewById(R.id.imageview);
    }

    public void rotateyAnimRun(View view)
    {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

}