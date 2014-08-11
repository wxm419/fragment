package com.fheebiy.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-8-7.
 */
public class PullToRefreshListView extends ListView {


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 12){
                goneRefreshLoading();
            }
        }
    };


    private Context ctx;


    /*下拉刷新*/

    private View headerView;

    private ImageView arrowIv;  //箭头

    private LinearLayout progressbar_layout;    //小菊花layout

    private LinearLayout arrow_layout;

    private TextView tipsTv;            //提示:释放理解刷新,正在获取刷新内容，下拉刷新

    private TextView lastUpdateTv;      //最后更新时间: 刚刚

    private RotateAnimation animation;          //箭头向上的旋转动画
    private RotateAnimation reverseAnimation;   //箭头恢复的旋转动画

    private int headerViewHeight;       //header的高度

    private float start_y;              //触摸到listView时初始的Y坐标

    private float distance_y;           //拖动的距离

    private boolean isRefreshing = false;

    private PullRefreshListener refreshListener;    //下拉刷新监听

    /*下拉刷新*/

    /*上拉加载更多*/
    private View loadMoreView;


    /*上拉加载更多*/


    //刷新加载的各种状态
    private int current_state;         //当前的状态
    private int last_state;            //上一个状态

    public static final int STATE_DONE = 0;             //完成状态
    public static final int STATE_PULL_REFRESH = 1;     //下拉中，显示下拉可以刷新的状态
    public static final int STATE_RELEASE_REFRESH = 2;  //下拉中，显示松开即可刷新的状态
    public static final int STATE_LOADING = 3;          //数据加载中的状态


    public PullToRefreshListView(Context context) {
        super(context);
        this.ctx = context;
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        init();
    }


    private void init() {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        headerView = inflater.inflate(R.layout.header, null);
        arrowIv = (ImageView) headerView.findViewById(R.id.head_arrowImageView);
        arrow_layout = (LinearLayout) headerView.findViewById(R.id.head_arrow_layout);

        arrowIv.setMinimumWidth(50);
        arrowIv.setMinimumHeight(50);

        progressbar_layout = (LinearLayout) headerView.findViewById(R.id.head_progressBar_layout);

        tipsTv = (TextView) headerView.findViewById(R.id.head_tipsTextView);
        lastUpdateTv = (TextView) headerView.findViewById(R.id.head_lastUpdatedTextView);

        animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);


        reverseAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(250);
        animation.setFillAfter(true);

        measureView(headerView);

        headerViewHeight = headerView.getMeasuredHeight();

        this.addHeaderView(headerView);

        current_state = STATE_DONE;
        last_state = STATE_DONE;

        headerView.setPadding(0, -headerViewHeight, 0, 0);

        loadMoreView = inflater.inflate(R.layout.ent_refresh_footer, null);


    }

    /**
     * 这个是用来设定view的尺寸的
     * 需要根据子view来设定父view尺寸
     * mode有三种情况，mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY, MeasureSpec.AT_MOST。
     * MeasureSpec.EXACTLY：父视图希望子视图的大小应该是specSize中指定的。 比如width = 50dp,height = fill_parent等，确切的值
     * MeasureSpec.AT_MOST：子视图的大小最多是specSize中指定的值，也就是说不建议子视图的大小超过specSize中给定的值。 width = warp_content
     * MeasureSpec.UNSPECIFIED：我们可以随意指定视图的大小。
     *
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childMeasuredWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int childMeasuredHeight;

        if (lp.height > 0) {
            childMeasuredHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
        } else {
            childMeasuredHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.UNSPECIFIED);
        }

        view.measure(childMeasuredWidth, childMeasuredHeight);
    }


    private void reset() {

        arrow_layout.setVisibility(View.VISIBLE);
        progressbar_layout.setVisibility(View.GONE);
        arrowIv.setImageResource(R.drawable.arrow);
        arrowIv.clearAnimation();
       // arrowIv.startAnimation(animation);
        tipsTv.setText("下拉刷新");
        tipsTv.setPadding(0, 0, 0, 0);

        isRefreshing = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                start_y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(current_state != STATE_LOADING){
                    distance_y = ev.getY() - start_y;
                    if (distance_y > 0 && distance_y < headerViewHeight) {        //此时状态显示"下拉即可刷新"
                        last_state = current_state;
                        current_state = STATE_PULL_REFRESH;
                    }
                    if (distance_y > headerViewHeight) {                        //拖动距离大于,则显示'松手刷新'
                        last_state = current_state;
                        current_state = STATE_RELEASE_REFRESH;
                    }
                    onHeaderViewStateChanged();
                }


                break;
            case MotionEvent.ACTION_UP:
                if(current_state == STATE_LOADING){
                    return super.onTouchEvent(ev);
                }

                if (distance_y >= headerViewHeight) {
                    last_state = current_state;
                    current_state = STATE_LOADING;
                    onHeaderViewStateChanged();
                    this.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    }, 500);
                } else {
                    last_state = current_state;
                    current_state = STATE_DONE;

                }
                onHeaderViewStateChanged();
                break;
        }

        return super.onTouchEvent(ev);
    }


    private void onHeaderViewStateChanged() {
        switch (current_state) {

            case STATE_PULL_REFRESH:    //下拉刷新
                headerView.setPadding(0, -headerViewHeight + (int) distance_y, 0, 0);
                pullToRefresh();
                break;

            case STATE_RELEASE_REFRESH:    //松手刷新
                headerView.setPadding(0, -headerViewHeight+(int)(distance_y*0.5), 0, 0);
                releaseToRefresh();
                break;

            case STATE_LOADING:     //正在加载
                headerView.setPadding(0, 0, 0, 0);
                refresh();
                break;

            case STATE_DONE:        //完成
                headerView.setPadding(0, -headerViewHeight, 0, 0);
                invalidate();
                break;
        }

    }


    //下拉即可刷新
    private void pullToRefresh() {
        progressbar_layout.setVisibility(View.GONE);
        tipsTv.setVisibility(View.VISIBLE);
        arrowIv.clearAnimation();
        arrow_layout.setVisibility(View.VISIBLE);
        tipsTv.setText("下拉即可刷新");

        if (last_state == STATE_RELEASE_REFRESH) {
            arrowIv.clearAnimation();
            arrowIv.startAnimation(reverseAnimation);
        }
        isRefreshing = false;
    }


    //松开即可刷新
    private void releaseToRefresh() {
        arrow_layout.setVisibility(View.VISIBLE);
        progressbar_layout.setVisibility(View.GONE);
        tipsTv.setVisibility(View.VISIBLE);
        tipsTv.setText("松手即可刷新");

        if (last_state == STATE_PULL_REFRESH) {
            arrowIv.clearAnimation();
            arrowIv.startAnimation(animation);
        }

        isRefreshing = false;
    }



    //loading...
    private void refresh(){
        progressbar_layout.setVisibility(View.VISIBLE);
        arrowIv.clearAnimation();
        arrow_layout.setVisibility(View.GONE);
        tipsTv.setText("正在刷新...");

        //如果上拉加载进行中，则取消下拉刷新
     /*   if (isLoadingMore) {
            isRefreshing = false;
            state = STATE_DONE;
            onHeaderViewStateChanged();
            return;
        }*/
/*
        if (refreshListener != null && !isRefreshing) {
            refreshListener.refresh();
      /*  }*//*
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(12);
            }
        }, 3000);*/

     /*   reset();*/

        if (refreshListener != null && !isRefreshing) {
            refreshListener.refresh();
        }

        isRefreshing = true;

    }




    /**
     * 取消加载状态
     */
    public void goneRefreshLoading(){
        reset();
        current_state = STATE_DONE;
        onHeaderViewStateChanged();
    }



    public boolean isRefreshing(){
        return isRefreshing;
    }


    //刷新监听
    public  interface PullRefreshListener {

        public void refresh();
    }


    /**
     * 设置下拉刷新监听
     * @param refreshListener
     */
    public void setRefreshListener(PullRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }


}
