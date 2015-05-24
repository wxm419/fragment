package com.fheebiy.activity.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.fheebiy.R;
import com.fheebiy.view.CommLoadingView;

/**
 * Created by Lenovo on 15-3-26.
 */
public class PropertyAnimationActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;

    private ImageView heartImg;

    private CommLoadingView loadingView;

    String TAG = "PropertyAnimationActivity";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_anima);
        heartImg = (ImageView) findViewById(R.id.heart_img);
        heartImg.setOnClickListener(this);
        String str = "abc122";
        int x = Integer.parseInt(str);

        imageView = (ImageView) findViewById(R.id.imageview);
        loadingView = (CommLoadingView) findViewById(R.id.loading_view);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.goneLoading();
            }
        }, 3000);
    }

    public void rotateAnimRun(View view) {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }


    /**
     * 抛物线
     *
     * @param
     */
    public void paowuxian() {
        final float initX = imageView.getPivotX();
        final float initY = imageView.getPivotY();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                Log.e(TAG, fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = initX + 200 * fraction * 3;
                point.y = initY - 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                heartImg.setX(point.x);
                heartImg.setY(point.y);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.heart_img) {
            paowuxian();
        }
    }
}