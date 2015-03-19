package com.fheebiy.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.*;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;

/**
 * Created by bob zhou on 15-3-18.
 * 模仿guo lin的RefreshableView,基本上是照抄的
 *
 * @author bob zhou
 */
public class MyRefreshableView extends LinearLayout implements View.OnTouchListener {

    //下拉刷新
    private static final int STATUS_PULL_TO_REFRESH = 0;

    //松手刷新
    private static final int STATUS_RELEASE_TO_REFRESH = 1;

    //正在刷新
    private static final int STATUS_REFRESHING = 2;

    //刷新完毕
    private static final int STATUS_REFRESH_FINISHED = 3;

    private static final long ONE_SECOND = 1000;

    private static final long ONE_MINUTE = 60 * ONE_SECOND;

    private static final long ONE_HOUR = 60 * ONE_MINUTE;

    private static final long ONE_DAY = 24 * ONE_HOUR;

    private static final long ONE_MONTH = 30 * ONE_DAY;

    private static final long ONE_YEAR = 12 * ONE_MONTH;

    private static final String UPDATED_AT = "UPDATE_AT";


    private int mId;
    /**
     * 用于存储上次更新时间
     */
    private SharedPreferences preferences;

    //是否加载过一次
    private boolean loadOnce;

    //头部
    private View headerView;

    //箭头
    private ImageView arrowIv;

    //旋转小菊花
    private ProgressBar progressBar;

    //描述，如松手刷新,正在刷新等
    private TextView descriptionTv;

    //刷新的时间，如2小时前刷新等
    private TextView updateAtTv;

    //隐藏头部的高度
    private int hideHeaderHeight;

    private MarginLayoutParams marginLayoutParams;

    private ListView listView;

    /**
     * 当前是否可以下拉，只有ListView滚动到头的时候才允许下拉
     */
    private boolean ableToPull;

    private float yDown;

    public static final int SCROLL_SPEED = -20;

    private PullToRefreshListener refreshListener;

    /**
     * 在被判定为滚动之前用户手指可以移动的最大值。
     */
    private int touchSlop;

    private int currentStatus = STATUS_REFRESH_FINISHED;

    private int lastStatus = currentStatus;

    public MyRefreshableView(Context context) {
        super(context);
        init(context);
    }

    public MyRefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        headerView = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null);
        arrowIv = (ImageView) headerView.findViewById(R.id.arrow);
        progressBar = (ProgressBar) headerView.findViewById(R.id.progress_bar);
        descriptionTv = (TextView) headerView.findViewById(R.id.description);
        updateAtTv = (TextView) headerView.findViewById(R.id.updated_at);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        setOrientation(VERTICAL);
        addView(headerView, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            hideHeaderHeight = -headerView.getHeight();
            marginLayoutParams = (MarginLayoutParams) headerView.getLayoutParams();
            marginLayoutParams.topMargin = hideHeaderHeight;
            listView = (ListView) getChildAt(1);
            if (listView == null) {
                throw new RuntimeException("ListView not found!");
            } else {
                listView.setOnTouchListener(this);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {     //注意，这里是onTouch(),可不是onTouchEvent()
        ableToPull(event);
        if (ableToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    yDown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dis = (int) (event.getRawY() - yDown);
                    if (dis <= 0) {
                        return false;
                    }
                    if (dis < touchSlop) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (marginLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_TO_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_TO_REFRESH;
                        }

                        marginLayoutParams.topMargin = (dis / 2) + hideHeaderHeight;
                        headerView.setLayoutParams(marginLayoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_PULL_TO_REFRESH) {
                        // 松手时如果是下拉状态，就去调用隐藏下拉头的任务
                        new HideHeaderTask().execute();
                    }

                    if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                        new RefreshingTask().execute();
                    }
                    Log.d(CommonUtil.LOG_TAG,"cs="+currentStatus+",and lc="+lastStatus);
                    break;
            }

            if (currentStatus == STATUS_PULL_TO_REFRESH || currentStatus == STATUS_RELEASE_TO_REFRESH) {
                updateHeaderView();
                // 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
                // 当前正处于下拉或释放状态，通过返回true屏蔽掉ListView的滚动事件(这点至关重要)
                return true;
            }
        }


        return false;
    }


    private void updateHeaderView() {
        if(lastStatus != currentStatus){
            if(currentStatus == STATUS_PULL_TO_REFRESH){
                descriptionTv.setText(getResources().getString(R.string.pull_to_refresh));
                arrowIv.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                rotateArrow();
            }

            if(currentStatus == STATUS_RELEASE_TO_REFRESH){
                descriptionTv.setText(getResources().getString(R.string.release_to_refresh));
                arrowIv.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                rotateArrow();
            }

            if(currentStatus == STATUS_REFRESHING){
                descriptionTv.setText(getResources().getString(R.string.refreshing));
                arrowIv.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                arrowIv.clearAnimation();
            }
            refreshUpdatedAtValue();
        }



    }

    private void refreshUpdatedAtValue() {
        long    lastUpdateTime = preferences.getLong(UPDATED_AT + mId, -1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (lastUpdateTime == -1) {
            updateAtValue = getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < 0) {
            updateAtValue = getResources().getString(R.string.time_error);
        } else if (timePassed < ONE_MINUTE) {
            updateAtValue = getResources().getString(R.string.updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "个月";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else {
            timeIntoFormat = timePassed / ONE_YEAR;
            String value = timeIntoFormat + "年";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        }
        updateAtTv.setText(updateAtValue);

    }


    private void rotateArrow(){
        float pivotX = arrowIv.getWidth() / 2f;
        float pivotY = arrowIv.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if(currentStatus == STATUS_PULL_TO_REFRESH){
            fromDegrees = 180f;
            toDegrees = 360f;
        }
        if(currentStatus == STATUS_RELEASE_TO_REFRESH){
            fromDegrees = 0f;
            toDegrees = 180f;
        }

        RotateAnimation animation = new RotateAnimation(fromDegrees,toDegrees,pivotX,pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrowIv.startAnimation(animation);
    }


    private void ableToPull(MotionEvent event) {
        View firstChild = listView.getChildAt(0);
        if (firstChild != null) {
            int firstVisiblePos = listView.getFirstVisiblePosition();
            if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
                ableToPull = true;
            } else {
                if (marginLayoutParams.topMargin != hideHeaderHeight) { //这段代码如果不加，如果正在刷新时，你滚动ListView,会头部不隐藏的问题
                    marginLayoutParams.topMargin = hideHeaderHeight;
                    headerView.setLayoutParams(marginLayoutParams);
                }
                ableToPull = false;
            }
        } else {
            ableToPull = true;
        }
    }


    /**
     * 当所有的刷新逻辑完成后，记录调用一下，否则你的ListView将一直处于正在刷新状态。
     */
    public void finishRefreshing() {
        currentStatus = STATUS_REFRESH_FINISHED;
        preferences.edit().putLong(UPDATED_AT + mId, System.currentTimeMillis()).commit();
        new HideHeaderTask().execute();
    }


    class RefreshingTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            int topMargin = marginLayoutParams.topMargin;
            while (true) {
                topMargin += SCROLL_SPEED;
                if (topMargin <= 0) {
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            currentStatus = STATUS_REFRESHING;
            publishProgress(0);     //这句代码必须要在前面，而onRefresh()必须要在后面
            if (refreshListener != null) {
                refreshListener.onRefresh();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            super.onProgressUpdate(topMargin);
            Log.d(CommonUtil.LOG_TAG,"current="+currentStatus+",last="+lastStatus);
            updateHeaderView();
            marginLayoutParams.topMargin = topMargin[0];
            headerView.setLayoutParams(marginLayoutParams);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    class HideHeaderTask extends AsyncTask<Void,Integer, Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            int topMargin = marginLayoutParams.topMargin;
            while (true){
                topMargin += SCROLL_SPEED;
                if(topMargin <= hideHeaderHeight){
                    topMargin = hideHeaderHeight;
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            super.onProgressUpdate(topMargin);
            marginLayoutParams.topMargin = topMargin[0];
            headerView.setLayoutParams(marginLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer topMargin) {
            super.onPostExecute(topMargin);
            marginLayoutParams.topMargin = topMargin;
            headerView.setLayoutParams(marginLayoutParams);
            currentStatus = STATUS_REFRESH_FINISHED;
        }
    }



    private void sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 下拉刷新的监听器，使用下拉刷新的地方应该注册此监听器来获取刷新回调。
     *
     * @author guolin
     */
    public interface PullToRefreshListener {

        /**
         * 刷新时会去回调此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中调用的， 你可以不必另开线程来进行耗时操作。
         */
        void onRefresh();

    }

    public void setRefreshListener(PullToRefreshListener refreshListener, int id) {
        this.refreshListener = refreshListener;
        this.mId = id;
    }
}
