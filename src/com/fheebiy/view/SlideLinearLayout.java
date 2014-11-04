package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by bob zhou on 14-10-27.
 * <p/>
 * 模仿ios，弹性的LinearLayout
 * <p/>
 * 自定义view
 */
public class SlideLinearLayout extends LinearLayout {

    public static final String TAG = "SlideLinearLayout";

    protected float start_y;


    public SlideLinearLayout(Context context) {
        super(context);
    }

    public SlideLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"onInterceptTouchEvent action:ACTION_DOWN");
//           return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"onInterceptTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"onInterceptTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d(TAG, "MotionEvent :" + event.getAction());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            start_y = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float cY = event.getY();
            float disY = cY - start_y;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            if (disY > 0 && disY < 50) {
                params.setMargins(0, (int) disY, 0, 0);
            }
            if (disY >= 50) {
                params.setMargins(0, 50, 0, 0);
            }

            this.setLayoutParams(params);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.setMargins(0, 0, 0, 0);
            this.setLayoutParams(params);
            start_y = 0;
        }


        return super.onTouchEvent(event);
    }
}
