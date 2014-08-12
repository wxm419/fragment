package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

    static final String TAG = "MySlideView";

    private Context context;

    private LinearLayout content_view;

    private Scroller scroller;

    //试图容器的宽度，120dp
    private int mHolderWidth = 120;

    //上次滑动时 x坐标
    private int mLastX = 0;
    //上次滑动时 y坐标
    private int mLastY = 0;

    public static final int TAN = 2;

    private OnSlideListener slideListener;


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
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                if (slideListener != null) {
                    slideListener.onSlide(this, OnSlideListener.SLIDE_STATUS_START_SCROLL);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {
                    break;
                }
                int newScrollX = scrollX - deltaX;
                if (deltaX != 0) {
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    }

                    if (newScrollX > mHolderWidth) {
                        newScrollX = mHolderWidth;
                    }

                    this.scrollTo(newScrollX, 0);
                }

                /**
                 *   int deltaX = x - mLastX;
                 int deltaY = y - mLastY;
                 if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {
                 break;
                 }

                 int newScrollX = scrollX - deltaX;
                 if (deltaX != 0) {
                 if (newScrollX < 0) {
                 newScrollX = 0;
                 } else if (newScrollX > mHolderWidth) {
                 newScrollX = mHolderWidth;
                 }
                 this.scrollTo(newScrollX, 0);
                 }
                 *
                 */
                break;
            case MotionEvent.ACTION_UP:
                int curScrollX = 0;
                if (scrollX > mHolderWidth * 0.75) {
                    curScrollX = mHolderWidth;
                }
                this.smoothScrollTo(curScrollX, 0);
                if (this.slideListener != null) {
                    slideListener.onSlide(this, curScrollX > 0 ? OnSlideListener.SLIDE_STATUS_ON : OnSlideListener.SLIDE_STATUS_OFF);
                }
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
    }


    public void setOnSlideListener(OnSlideListener slideListener) {
        this.slideListener = slideListener;
    }

    //为外部提供接口
    public interface OnSlideListener {
        public static final int SLIDE_STATUS_OFF = 0;
        public static final int SLIDE_STATUS_START_SCROLL = 1;
        public static final int SLIDE_STATUS_ON = 2;

        /**
         * @param view   current SlideView
         * @param status SLIDE_STATUS_ON or SLIDE_STATUS_OFF
         */
        public void onSlide(View view, int status);

    }

    private void smoothScrollTo(int destX, int destY) {
        // 缓慢滚动到指定位置
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        Log.v(TAG, "delta=" + delta);
        scroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    // 将当前状态置为关闭
    public void shrink() {
        if (getScrollX() != 0) {
            this.smoothScrollTo(0, 0);
        }
    }

}
