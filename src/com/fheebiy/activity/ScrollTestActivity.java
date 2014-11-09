package com.fheebiy.activity;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.OverScrollView;
import com.fheebiy.widget.LinearLayoutForListView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 14-11-8.
 */
public class ScrollTestActivity extends Activity {

    private OverScrollView scrollView;

    public static final String TAG = "ScrollTestActivity";

    private LinearLayoutForListView listView;

    private List<Hero> list;

    private HeroLvAdapter adapter;

    private boolean isLoading;

    private boolean isRefreshing;

    private LayoutInflater inflater;

    private RelativeLayout footer;

    private ImageView refreshImg;

    private float mRotationPivotX, mRotationPivotY;

    private  Matrix mHeaderImageMatrix;

    private RotateAnimation rotateAnimation;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 12){
                list.addAll(CommonUtil.getInitData());
               // adapter.notifyDataSetChanged();
                listView.notifyDataSetChanged();
                isLoading = false;
                footer.setVisibility(View.GONE);

            }

            if(msg.what == 11){
                list = CommonUtil.getInitData();
               // adapter.notifyDataSetChanged();
                listView.removeAllViews();
                adapter = new HeroLvAdapter(ScrollTestActivity.this,list);
                listView.setAdapter(adapter);
                isRefreshing = false;
                refreshImg.clearAnimation();
            }
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolltest);
        inflater = LayoutInflater.from(this);
        listView = (LinearLayoutForListView)findViewById(R.id.listview_layout);
        refreshImg = (ImageView)findViewById(R.id.percenter_refresh_img);

        refreshImg.setScaleType(ImageView.ScaleType.MATRIX);
        mHeaderImageMatrix = new Matrix();
        refreshImg.setImageMatrix(mHeaderImageMatrix);
        onLoadingDrawableSet(refreshImg.getDrawable());

        rotateAnimation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(600);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        View footerView = inflater.inflate(R.layout.ent_refresh_footer,null);
        footer = (RelativeLayout)findViewById(R.id.footerview);
        footer.setVisibility(View.GONE);
       // listView.addFooterView(footerView);
        list = CommonUtil.getInitData();
        adapter = new HeroLvAdapter(this,list);
        listView.setAdapter(adapter);
        scrollView = (OverScrollView)findViewById(R.id.scrollview);
        scrollView.setOnScrollChangedListener(new OverScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                Log.d(TAG,"y === " + y+" |||||,&oldy === "+oldy);
                if(oldy < 1500 && y< 1500){
                    if(oldy > y){       //下拉

                    }

                    if(oldy < y){       //回弹

                    }
                    float scale = Math.abs(oldy-1500) / (float) 160;
                    onPullImpl(scale);
                }

                if(scrollView.isBottom()){
                   // Log.d(TAG,"BOTTOM,BOTTOM,BOTTOM,BOTTOM,BOTTOM,");
                    if(!isLoading){
                       /* new Thread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(CommonUtil.getInitData());
                                handler.postDelayed(this,2000);
                            }
                        }).start();*/
                        footer.setVisibility(View.VISIBLE);
                        isLoading = true;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(12);
                            }
                        },3000);
                    }
                }
            }

            @Override
            public void refresh() {
                Toast.makeText(ScrollTestActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
                //Log.d(TAG,"刷新,刷新,刷新,刷新");
                if(!isRefreshing){
                    refreshImg.startAnimation(rotateAnimation);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(11);
                        }
                    },3000);
                }


            }

            @Override
            public void loadMore(){

            }
        });


    }


    protected void onPullImpl(float scaleOfLayout) {
        float angle;

            angle = scaleOfLayout * 90f;

       // Log.d(MYTAG,"scaleOfLayout ="+scaleOfLayout+", and angle="+angle+"and mRotateDrawableWhilePulling="+ mRotateDrawableWhilePulling);
        Log.d(TAG,"angle="+angle);
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