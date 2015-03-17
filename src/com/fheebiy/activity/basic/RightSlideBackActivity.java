package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;

/**
 * Created by Lenovo on 15-3-17.
 */
public class RightSlideBackActivity extends Activity implements GestureDetector.OnGestureListener {

    private float lastX;

    private boolean isCanClose;

    private GestureDetector detector;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new GestureDetector(this,this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.detector.onTouchEvent(ev)) {
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float cx = ev.getX();
                if ((cx - lastX) > getWindow().getDecorView().getMeasuredWidth() / 3 && isCanClose) {
                    finish();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                lastX = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isCanClose() {
        return isCanClose;
    }

    public void setCanClose(boolean isCanClose) {
        this.isCanClose = isCanClose;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(CommonUtil.LOG_TAG, "velocityX=" + velocityX + ",velocityY=" + velocityY);
        float angle = Math.abs(velocityY / velocityX);
        if (velocityX > 100 && angle > 0.3 && isCanClose) {
            finish();
        }
        return false;
    }
}