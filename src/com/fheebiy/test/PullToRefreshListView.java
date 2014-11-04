package com.fheebiy.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.*;
import com.fheebiy.R;

/**
 * Created by wlong on 14-5-21.
 */
public class PullToRefreshListView extends ListView implements AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener{

    //-------------------下拉刷新--------------------------------------------------

    private byte state;  //当前的状态
    private byte last_state; //上一个状态

    public static final byte STATE_DONE = 0;            //完成状态
    public static final byte STATE_PULL_REFRESH = 1;    //下拉中，显示下拉可以刷新的状态
    public static final byte STATE_RELESE_REFRESH = 2;  //下拉中，显示松开即可刷新的状态
    public static final byte STATE_LOADING = 3;         //数据加载中的状态

    private Context context;

    private View headerView;            //下拉刷新的View
    private ImageView arrowIv;          //箭头
    private TextView tipsTv,            //提示信息
            lastUpdateTv;       //最后更新时间
    private LinearLayout progressLl,    //进度条
            arrowLl;         //箭头
    private RotateAnimation animation,  //
            reverseAnimation;   //

    private int headerViewHeight;

    private float startY,   //开始滑动时候的起始Y坐标
            distanceY;  //在Y轴上滑动的距离，相对于startY

    private boolean canPull;    //当前状态是否可以下拉headerView
    //只有当前屏幕显示的第一个item是ListView的第一个item

    private boolean canRefresh = true;    //下拉刷新功能的开关，由使用者设置
    private boolean isRefreshing;
    private PullRefreshListener refreshListener;    //下拉刷新监听


    //------------------------上拉加载更多--------------------------------------

    private View loadMoreView;
    private LoadMoreListener loadMoreListener;
    private int visibleLast;
    private boolean isLoadingMore;
    private int visibleCount;

    private boolean canLoad;

    private int currentVisiableCount;

    //---------------------------------------
    private OnItemClickListener onItemClickListener;

    public PullToRefreshListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init(){
        headerView = View.inflate(context, R.layout.header, null);
        arrowIv = (ImageView) headerView.findViewById(R.id.head_arrowImageView);
        arrowIv.setMinimumWidth(50);
        arrowIv.setMinimumHeight(50);
        progressLl = (LinearLayout) headerView.findViewById(R.id.head_progressBar_layout);
        tipsTv = (TextView) headerView.findViewById(R.id.head_tipsTextView);
        lastUpdateTv = (TextView) headerView.findViewById(R.id.head_lastUpdatedTextView);
        arrowLl = (LinearLayout) headerView.findViewById(R.id.head_arrow_layout);

        animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(250);
        reverseAnimation.setFillAfter(true);

        mesureView(headerView);
        headerViewHeight = headerView.getMeasuredHeight();

        this.addHeaderView(headerView);
        setOnScrollListener(this);

        //初始的headerView状态
        state = STATE_DONE;
        last_state = STATE_DONE;
        onHeaderViewStateChanged();

        loadMoreView = View.inflate(context, R.layout.ent_refresh_footer, null);
        //addFooterView(loadMoreView);

        //    setOnItemClickListener(this);
    }

    //headerView状态改变时调用的方法，更新headerView的高度和显示子控件
    private void onHeaderViewStateChanged(){
        switch (state){
            case STATE_DONE:
                headerView.setPadding(0, -headerViewHeight, 0, 0);
                reset();
                invalidate();
                break;
            case STATE_LOADING:
                headerView.setPadding(0, -headerViewHeight+(int)distanceY, 0, 0);
                refresh();
                invalidate();
                break;
            case STATE_PULL_REFRESH:
                headerView.setPadding(0, -headerViewHeight+(int)distanceY, 0, 0);
                pullToRefresh();
                invalidate();
                break;
            case STATE_RELESE_REFRESH:
                headerView.setPadding(0, -headerViewHeight+(int)distanceY, 0, 0);
                releaseToRefresh();
                invalidate();
                break;
        }
    }

    private void mesureView(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null)
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

        int childMesuredWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int childMesuredHeight;

        if (lp.height > 0) {
            childMesuredHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
        } else {
            childMesuredHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.UNSPECIFIED);
        }

        view.measure(childMesuredWidth, childMesuredHeight);
    }

    //最初的状态
    private void reset(){
        arrowLl.setVisibility(View.VISIBLE);
        progressLl.setVisibility(View.GONE);
        arrowIv.clearAnimation();
        arrowIv.setImageResource(R.drawable.arrow);
        tipsTv.setText("下拉刷新");
        tipsTv.setPadding(0, 0, 0, 0);

        isRefreshing = false;
    }

    //松开即可刷新
    private void releaseToRefresh() {
        arrowLl.setVisibility(View.VISIBLE);
        progressLl.setVisibility(View.GONE);
        tipsTv.setVisibility(View.VISIBLE);
        tipsTv.setText("松手刷新");

        if (last_state == STATE_PULL_REFRESH) {
            arrowIv.clearAnimation();
            arrowIv.startAnimation(animation);
        }

        isRefreshing = false;

    }

    //下拉即可刷新
    private void pullToRefresh() {
        progressLl.setVisibility(View.GONE);
        tipsTv.setVisibility(View.VISIBLE);
        arrowIv.clearAnimation();
        arrowLl.setVisibility(View.VISIBLE);
        tipsTv.setText("下拉刷新");

        if (last_state == STATE_RELESE_REFRESH) {
            arrowIv.clearAnimation();
            arrowIv.startAnimation(reverseAnimation);
        }

        isRefreshing = false;
    }
    //loading...
    private void refresh(){
        progressLl.setVisibility(View.VISIBLE);
        arrowIv.clearAnimation();
        arrowLl.setVisibility(View.GONE);
        tipsTv.setText("正在加载");

        //如果上拉加载进行中，则取消下拉刷新
        if (isLoadingMore) {
            isRefreshing = false;
            state = STATE_DONE;
            onHeaderViewStateChanged();
            return;
        }

        if (refreshListener != null && !isRefreshing) {
            refreshListener.refresh();
        }
        isRefreshing = true;

    }

    /**
     * 设置上次刷新的时间
     * @param text
     */
    public void setRefreshTime(String text){
        if (null !=  lastUpdateTv){
            lastUpdateTv.setText(text);
            lastUpdateTv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 取消加载状态
     */
    public void goneRefreshLoading(){
        reset();
        state = STATE_DONE;
        onHeaderViewStateChanged();
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }

    public void setCanLoadMore(boolean canLoadMore){

        canLoad = canLoadMore;
        isLoadingMore = false;

        removeFooterView(loadMoreView);

        if ( canLoadMore ) {
            this.addFooterView(loadMoreView);
        }

    }

    public int getCurrentVisiableCount() {
        return currentVisiableCount;
    }

    /**
     * 当前是否处于正在刷新的状态
     * @return
     */
    public boolean isRefreshing(){
        return isRefreshing;
    }

    /**
     * 当前是否处于加载更多的状态
     * @return
     */
    public boolean isLoadingMore() {
        return isLoadingMore;
    }


    /**
     * 设置下拉刷新监听
     * @param refreshListener
     */
    public void setRefreshListener(PullRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 设置上拉加载更多的监听
     * @param loadMoreListener
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if ( canPull ) {

            int action = ev.getAction();

            switch (action) {
                case MotionEvent.ACTION_DOWN:

                    startY = ev.getY();

                    break;
                case MotionEvent.ACTION_MOVE:

                    if (state != STATE_LOADING) {

                        distanceY = ev.getY() - startY;
                        //根据拉动的距离判断显示“下拉刷新”或者"松开刷新"
                        if (distanceY < headerViewHeight && distanceY > 0){
                            last_state = state;
                            state = STATE_PULL_REFRESH;

                        }

                        if (distanceY > headerViewHeight) {
                            if (state != STATE_LOADING) {
                                last_state = state;
                                state = STATE_RELESE_REFRESH;
                            }

                        }
                    } else {
                        distanceY = ev.getY() - startY + headerViewHeight;

                        if (distanceY < headerViewHeight) {
                            distanceY = headerViewHeight;
                        }
                    }

                    onHeaderViewStateChanged();

                    break;
                case MotionEvent.ACTION_UP:
                    //手指抬起时，根据当前的拉动的相对距离判断是加载状态还是返回最初状态。
                    if (distanceY >= headerViewHeight) {
                        last_state = state;
                        state = STATE_LOADING;
                        onHeaderViewStateChanged();
                        distanceY = headerViewHeight;
                        canPull = false;
                        this.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                canPull = true;
                            }
                        }, 50);

                    } else {
                        last_state = state;
                        state = STATE_DONE;
                    }
                    onHeaderViewStateChanged();

                    break;
                default:
                    break;
            }
        }

        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = getAdapter().getCount();
        if(itemsLastIndex < 0) {
            return;
        }

        int lastIndex = itemsLastIndex;
        //  System.out.println( "lastIndex :" + lastIndex);
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLast == lastIndex &&
                !isLoadingMore && canLoad) {
            if (isRefreshing){
                setSelection(visibleLast- visibleCount - 1);
                return;
            }

            if (loadMoreListener != null && !isLoadingMore) {
                loadMoreListener.loadMore();
            }
            //System.out.println("加载~~~");
            isLoadingMore = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        currentVisiableCount = visibleItemCount;

        if (state != STATE_LOADING)
            canPull = firstVisibleItem == 0 && canRefresh;

        visibleLast = firstVisibleItem + visibleItemCount;
        this.visibleCount = visibleItemCount;
        //   System.out.println("visibleLast :" + visibleLast);

    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(this);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position >= 1 && onItemClickListener != null)
            onItemClickListener.onItemClick(adapterView, view,position-1, l);
    }

    public static interface LoadMoreListener {
        public void loadMore();
    }

    //刷新监听
    public static interface PullRefreshListener {

        public void refresh();
    }
}
