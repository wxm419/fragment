package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.fheebiy.R;

/**
 * Created by Administrator on 14-8-11.
 */
public class MySlideView extends LinearLayout {

    private Context context;

    private LinearLayout content_view;

    private Scroller scroller;

    //试图容器的宽度，120dp
    private int mHolderWidth = 120;

    //上次滑动时 x坐标
    private int mLastX = 0;
    //上次滑动时 y坐标
    private int mLastY = 0;


    public MySlideView(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    public MySlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initUI();
    }

    public MySlideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initUI();
    }


    private void initUI() {
        setOrientation(LinearLayout.HORIZONTAL);
        View.inflate(context, R.layout.myslide, this);
        content_view = (LinearLayout) findViewById(R.id.view_content);

        scroller = new Scroller(context);

        mHolderWidth = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mHolderWidth, getResources()
                .getDisplayMetrics()));
    }

    public void setContentView(View view) {
        this.content_view.addView(view);
    }


    public void onRequireTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int scrollX = getScrollX();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }


    }

}
