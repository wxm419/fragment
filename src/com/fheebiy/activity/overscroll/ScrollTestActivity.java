package com.fheebiy.activity.overscroll;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.ExpandLinearLayout;
import com.fheebiy.view.OverScrollView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bob zhou on 14-11-8.
 * <p/>
 * 中搜搜悦项目，个人中心demo，基本成熟
 * <p>
 * 1.下拉，顺时针，松手逆时针旋转效果
 * 2.用LinearLayout作为ListView
 * 3.OverScrollView，回弹效果
 * 4.下拉刷新，上拉加载更多实现
 * </p>
 */
public class ScrollTestActivity extends Activity {

    private OverScrollView scrollView;

    public static final String TAG = "ScrollTestActivity";

    private ExpandLinearLayout listView;

    private HeroLvAdapter adapter;

    private boolean isLoading;

    private boolean isRefreshing;

    private LayoutInflater inflater;

    private ImageView refreshImg;

    private ImageView logoImg;

    private float mRotationPivotX, mRotationPivotY;

    private Matrix mHeaderImageMatrix;

    private Matrix mLogoImageMatrix;

    private RotateAnimation rotateAnimation;

    private int initHeight;

    private float lastY;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 12) {     //上拉加载更多
                adapter.addList(CommonUtil.getHeroListData());
                adapter.notifyDataSetChanged();
                isLoading = false;
            }

            if (msg.what == 11) {     //刷新
                adapter.setList(CommonUtil.getHeroListData());
                isRefreshing = false;
                refreshImg.clearAnimation();
            }
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolltest);
        inflater = LayoutInflater.from(this);
        listView = (ExpandLinearLayout) findViewById(R.id.listview_layout);
        refreshImg = (ImageView) findViewById(R.id.percenter_refresh_img);
        logoImg = (ImageView) findViewById(R.id.center_logoImg);
        initHeight = 700;
        initAnimation();

        adapter = new HeroLvAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
        scrollView = (OverScrollView) findViewById(R.id.scrollview);
        scrollView.setOnScrollChangedListener(new OverScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                Log.d(TAG, "y === " + y + " |||||,&oldy === " + oldy);
                if (oldy < 1500 && y < 1500) {
                    if (oldy > y) {       //下拉

                    }

                    if (oldy < y) {       //回弹

                    }
                    float scale = Math.abs(oldy - 1500) / (float) 160;
                    onPullImpl(scale, Math.abs(oldy-1500));
                }

                if (scrollView.isBottom()) {
                    if (!isLoading) {
                        isLoading = true;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(12);
                            }
                        }, 3000);
                    }
                }
            }

            @Override
            public void refresh() {
                Toast.makeText(ScrollTestActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                if (!isRefreshing) {
                    refreshImg.startAnimation(rotateAnimation);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(11);
                        }
                    }, 3000);
                }
            }

            @Override
            public void loadMore() {

            }

          /*  @Override
            public void scale(int y) {
                //logoImg.setScaleX(1 + scaleOfLayout);
                //logoImg.setScaleY(1 + scaleOfLayout);
                if(y>0){
                    ViewGroup.LayoutParams  layoutParams = logoImg.getLayoutParams();
                    // layoutParams.width =(int) (layoutParams.width*(1+scaleOfLayout));
                    layoutParams.height = initHeight+y;

                    Log.d(TAG, "logoImg.getHeight() =" + logoImg.getHeight());
                    //logoImg.setMinimumHeight((int)(logoImg.getHeight()*(1+scaleOfLayout)));
                    logoImg.setLayoutParams(layoutParams);
                }

            }

            @Override
            public void reset() {
                ViewGroup.LayoutParams  layoutParams = logoImg.getLayoutParams();
                layoutParams.height = initHeight;
                logoImg.setLayoutParams(layoutParams);
                Log.d(TAG, "scale execut");
            }*/
        });


    }

    private void initAnimation() {
        refreshImg.setScaleType(ImageView.ScaleType.MATRIX);
       // logoImg.setScaleType(ImageView.ScaleType.MATRIX);
        mHeaderImageMatrix = new Matrix();
        mLogoImageMatrix = new Matrix();
        refreshImg.setImageMatrix(mHeaderImageMatrix);
        logoImg.setImageMatrix(mLogoImageMatrix);

        onLoadingDrawableSet(refreshImg.getDrawable());

        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(600);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
    }


    protected void onPullImpl(float scaleOfLayout, int y) {
        float angle = scaleOfLayout * 90f;
        Log.d(TAG, "angle=" + angle);
        mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
        mLogoImageMatrix.setScale(1 + scaleOfLayout, 1 + scaleOfLayout);
        refreshImg.setImageMatrix(mHeaderImageMatrix);
       /* logoImg.setScaleX(1 + scaleOfLayout);
        logoImg.setScaleY(1 + scaleOfLayout);
        ViewGroup.LayoutParams  layoutParams = logoImg.getLayoutParams();
       // layoutParams.width =(int) (layoutParams.width*(1+scaleOfLayout));
        layoutParams.height = initHeight+y;

        Log.d(TAG, "logoImg.getHeight() =" + logoImg.getHeight());
        //logoImg.setMinimumHeight((int)(logoImg.getHeight()*(1+scaleOfLayout)));
        logoImg.setLayoutParams(layoutParams);*/
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
        if (null != imageDrawable) {
            mRotationPivotX = imageDrawable.getIntrinsicWidth() / 2f;
            mRotationPivotY = imageDrawable.getIntrinsicHeight() / 2f;
        }
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