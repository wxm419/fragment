package com.fheebiy.activity;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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
 */
public class ScrollTestActivity extends Activity {

    private OverScrollView scrollView;

    public static final String TAG = "ScrollTestActivity";

    private ExpandLinearLayout listView;

    private List<Hero> list;

    private HeroLvAdapter adapter;

    private boolean isLoading;

    private boolean isRefreshing;

    private LayoutInflater inflater;

    private ImageView refreshImg;

    private float mRotationPivotX, mRotationPivotY;

    private Matrix mHeaderImageMatrix;

    private RotateAnimation rotateAnimation;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 12) {     //上拉加载更多
                list.addAll(CommonUtil.getInitData());
                adapter.notifyDataSetChanged();
                isLoading = false;
            }

            if (msg.what == 11) {     //刷新
                list.clear();
                list.addAll(CommonUtil.getInitData());
                adapter.notifyDataSetChanged();
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

        initAnimation();

        list = CommonUtil.getInitData();
        adapter = new HeroLvAdapter(this, list);
        listView.setAdapter(adapter);
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
                    onPullImpl(scale);
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
        });


    }

    private void initAnimation() {
        refreshImg.setScaleType(ImageView.ScaleType.MATRIX);
        mHeaderImageMatrix = new Matrix();
        refreshImg.setImageMatrix(mHeaderImageMatrix);
        onLoadingDrawableSet(refreshImg.getDrawable());

        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(600);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
    }


    protected void onPullImpl(float scaleOfLayout) {
        float angle = scaleOfLayout * 90f;
        Log.d(TAG, "angle=" + angle);
        mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
        refreshImg.setImageMatrix(mHeaderImageMatrix);
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
        if (null != imageDrawable) {
            mRotationPivotX = imageDrawable.getIntrinsicWidth() / 2f;
            mRotationPivotY = imageDrawable.getIntrinsicHeight() / 2f;
        }
    }

}