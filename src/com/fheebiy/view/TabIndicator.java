package com.fheebiy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;


/**
 * Created by Administrator on 2014/8/20. mailto:wzyax@qq.com
 */
public class TabIndicator extends RelativeLayout {
    private BadgeView bage;

    private LayoutInflater inflater;
    private TextView title;
    private ImageView icon;
    private View containerView;
    private Context context;

    @SuppressLint("NewApi")
    public TabIndicator(Context context) {
        super(context);
        this.inflater = LayoutInflater.from(context);
        this.context = context;

        View localView = this.inflater.inflate(R.layout.tab_indicator, this);

        containerView = localView.findViewById(R.id.indicator_container);
        title = (TextView) localView.findViewById(R.id.title);
        icon = (ImageView) findViewById(R.id.icon);
        bage = new BadgeView(context, containerView);
        // bage.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        bage.setTextSize(12);
        bage.setMaxEms(2);
        bage.setSingleLine(true);
        bage.setBadgeMargin(((CommonUtil.getScreenWidth(context) / 4 - CommonUtil
                .dip2px(context, 60)) / 2), 5);
        bage.hide();

    }

    private BadgeView getBage() {
        return bage;
    }

    /*public TabIndicator(Context context, String type) {
        super(context);
        this.inflater = LayoutInflater.from(context);

        View localView = this.inflater
                .inflate(R.layout.msg_tab_indicator, this);

        containerView = localView.findViewById(R.id.indicator_container);
        title = (TextView) localView.findViewById(R.id.title);
        icon = (ImageView) findViewById(R.id.icon);

    }
*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public TabIndicator(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.inflater = LayoutInflater.from(paramContext);

        View localView = this.inflater.inflate(R.layout.tab_indicator, this);
        title = (TextView) localView.findViewById(R.id.title);
        icon = (ImageView) findViewById(R.id.icon);
    }

    public void setView(int titleText, int iconResource, boolean selected) {
        title.setText(titleText);
        icon.setBackgroundResource(iconResource);

		/*
         * if (selected) {
		 * title.setTextColor(getResources().getColor(R.color.white)); //
		 * setBackgroundResource(R.drawable.tab_bar_btn_selected); } else {
		 * title.setTextColor(getResources().getColor(
		 * R.color.tab_text_unselected)); //
		 * setBackgroundResource(R.drawable.tab_bar_btn_normal); }
		 */
    }

    public void setSelected(boolean selected, int iconSelectedResource) {
        if (selected) {
            title.setTextColor(getResources().getColor(R.color.white));
            icon.setBackgroundResource(iconSelectedResource);
            // setBackgroundResource(R.drawable.tab_bar_btn_selected);
        } else {
            title.setTextColor(getResources().getColor(
                    R.color.tab_text_unselected));
            icon.setBackgroundResource(iconSelectedResource);
            // setBackgroundResource(R.drawable.tab_bar_btn_normal);
        }
    }

    /**
     * 添加底部栏消息提醒
     *
     * @param newMsgCount
     */
    public void setNewMsgCountBadge(int newMsgCount) {
        if (newMsgCount > 0) {
            if (newMsgCount > 99) {
                getBage().setText("...");
            } else {
                getBage().setText(String.valueOf(newMsgCount));
            }
            getBage().show();
        } else if (newMsgCount == -1) {// 显示一个空气泡
            findViewById(R.id.small_red).setVisibility(View.VISIBLE);
//            ImageView imageView = new ImageView(context);
//            imageView.setImageResource(R.drawable.taskcenter_red);
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            lp.gravity = Gravity.RIGHT 入| Gravity.TOP;
//            lp.setMargins(((Utils.getScreenWidth(context) / 4 - DensityUtil
//                    .dip2px(context, 60)) / 2), 5, 0, 0);
//            imageView.setLayoutParams(lp);
//            ((ViewGroup) containerView).addView(imageView);
//            getBage().setText(String.valueOf("  "));
//            getBage().setLayoutParams(new FrameLayout.LayoutParams(5, 5));
//            getBage().setBackgroundResource(R.drawable.taskcenter_red);
//            getBage().show();
        } else if (newMsgCount == -2) {//隐藏气泡
            findViewById(R.id.small_red).setVisibility(View.GONE);
        } else {
            getBage().hide();
        }
    }

}