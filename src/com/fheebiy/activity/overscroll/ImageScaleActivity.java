package com.fheebiy.activity.overscroll;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fheebiy.R;

/**
 * Created by zhouwenbo on 15/5/6.
 */
public class ImageScaleActivity extends Activity {

    private ImageView logoImg;

    private int initHeight;

    private float lastY;

    String TAG = "ImageScaleActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_scale);

        initHeight = 700;
        logoImg = (ImageView) findViewById(R.id.center_logoImg);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       /* if (this.detector.onTouchEvent(ev)) {
            return true;
        }*/
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float cy = ev.getY();
                scale((int)(cy-lastY));
                break;
            case MotionEvent.ACTION_UP:
                reset();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    public void scale(int y) {
        //logoImg.setScaleX(1 + scaleOfLayout);
        //logoImg.setScaleY(1 + scaleOfLayout);
        if(y>0){
            ViewGroup.LayoutParams  layoutParams = logoImg.getLayoutParams();
            // layoutParams.width =(int) (layoutParams.width*(1+scaleOfLayout));
            layoutParams.height = initHeight+y;
            float scale = (initHeight+y)/(float)initHeight;
            logoImg.setScaleX(scale);
            logoImg.setScaleY(scale);
            Log.d(TAG, "logoImg.getHeight() =" + logoImg.getHeight()+"scale="+scale);
            //logoImg.setMinimumHeight((int)(logoImg.getHeight()*(1+scaleOfLayout)));
            logoImg.setLayoutParams(layoutParams);
        }

    }

    public void reset() {
        ViewGroup.LayoutParams  layoutParams = logoImg.getLayoutParams();
        layoutParams.height = initHeight;
        logoImg.setLayoutParams(layoutParams);
        logoImg.setScaleX(1);
        logoImg.setScaleY(1);
        Log.d(TAG, "scale execut");
    }


}