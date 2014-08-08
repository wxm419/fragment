package com.fheebiy.view;

import android.content.Context;
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

        animation = new RotateAnimation(90, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);


        reverseAnimation = new RotateAnimation(-90, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(250);
        animation.setFillAfter(true);

        measureView(headerView);

        headerViewHeight = headerView.getMeasuredHeight();

        this.addHeaderView(headerView);

        current_state = STATE_DONE;
        last_state = STATE_DONE;


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


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                start_y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distance_y = ev.getY() - start_y;
                if (distance_y > 0 && distance_y < 2*headerViewHeight) {        //下拉刷新，此时，拉动的具体小于headerViewHeight
                    int y = (int) distance_y;
                    headerView.setPadding(0, y, 0, 0);
                    if(y == headerViewHeight){
                        arrowIv.startAnimation(animation);
                    }

                }
                if (distance_y > 2*headerViewHeight) {      //也在下拉，但是此时，拉动的距离已经大于headerViewHeight了
                    headerView.setPadding(0, 2*headerViewHeight, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                headerView.setPadding(0,0,0,0);
                arrowIv.startAnimation(reverseAnimation);
                break;
        }

        return super.onTouchEvent(ev);
    }
}
