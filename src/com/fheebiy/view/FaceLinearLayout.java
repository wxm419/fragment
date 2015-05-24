package com.fheebiy.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by lvqiang on 14-10-14.
 */
public class FaceLinearLayout extends LinearLayout {
    private static final int INVALID_POINTER = -1;
    public static final int ACTION_TITLE_STOP=0;

    public static final int ACTION_TITLE_MOVE=1;

    static final int STATE_NONE=0;
    static final int STATE_MOVE_Y=1;
    static final int STATE_MOVE_X=2;
    static final int STATE_TITLE=3;

    static final boolean TITLE_DEBUG=true;

    private float mCurScrollY;
    private float mCurScrollX;
    private float mPreScroll;
    private float mInitScrollX;
    private float mInitScrollY;
    private int mState;
    private boolean mTitleScroll;
    private boolean mIsCanScroll;
    private float mTitleHeight;

    private int mMoveOffset;
    private int mActivePointerId = INVALID_POINTER;
    private onTitleLensiter mTitle;

    public FaceLinearLayout(Context context) {
        super(context);
        init(context);
    }
    public FaceLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public FaceLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    void init(Context context){
        final ViewConfiguration configuration = ViewConfiguration.get(context);

        mMoveOffset = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    public void setOnTitleLensiter(onTitleLensiter _listern){
        mTitle = _listern;
    }


    public void setTopHeight(float _titleheight){
        mTitleHeight = _titleheight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            final int action = ev.getAction();
            int offset = 0;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mIsCanScroll = true;
                    mState = STATE_NONE;

                    mInitScrollY = mPreScroll = mCurScrollY = ev.getY();
                    mInitScrollX = mCurScrollX = ev.getX();
                    mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                    if (mInitScrollY <= mTitleHeight) {
                        mState = STATE_TITLE;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    final int activePointerId = mActivePointerId;
                    if (activePointerId == INVALID_POINTER) {
                        // If we don't have a valid id, the touch down wasn't on content.
                        break;
                    }
                    final int pointerIndex = MotionEventCompat.findPointerIndex(ev, activePointerId);
                    if (pointerIndex == INVALID_POINTER) {
                        break;
                    }
                    mCurScrollX = MotionEventCompat.getX(ev, pointerIndex);
                    final float dx = mCurScrollX - mInitScrollX;
                    final float xDiff = Math.abs(dx);
                    mCurScrollY = MotionEventCompat.getY(ev, pointerIndex);
                    final float yDiff = Math.abs(mCurScrollY - mInitScrollY);
//                mCurScrollY = ev.getY();
//                mCurScrollX = ev.getX();
                    if (mState == STATE_NONE) {
                        if (yDiff > mMoveOffset) {
                            mState = STATE_MOVE_Y;
                        } else if (xDiff > mMoveOffset) {
                            mState = STATE_MOVE_X;
                        }
                    }
                    if (mState == STATE_MOVE_Y) {
                        offset = (int) (mCurScrollY - mPreScroll);
                        mIsCanScroll = mTitle.scrollTo(offset, ACTION_TITLE_MOVE);
                        mPreScroll = mCurScrollY;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (mState == STATE_MOVE_Y) {
                        offset = (int) (mCurScrollY - mPreScroll);
                        mIsCanScroll = mTitle.scrollTo(offset, ACTION_TITLE_STOP);
                        mPreScroll = mCurScrollY;
                    }
                    onSecondaryPointerUp(ev);
                    break;
            }
        }catch (Exception e){

        }
        return super.dispatchTouchEvent(ev);
    }
    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = MotionEventCompat.getActionIndex(ev);
        final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
        if (pointerId == mActivePointerId) {
            mActivePointerId = INVALID_POINTER;
        }
    }
    public interface onTitleLensiter{
        public boolean scrollTo(float offsetY, int state);
    }

}
