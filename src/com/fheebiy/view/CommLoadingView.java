package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-10-28.
 */
public class CommLoadingView extends FrameLayout implements View.OnClickListener {

    private ProgressBar progressBar;

    private TextView textView;

    private boolean can;

    private RefreshListener listener;

    public CommLoadingView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.common_loading, this);
        init();
    }

    public CommLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_loading, this);
        init();
    }

    public CommLoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.common_loading, this);
        init();
    }


    private void init() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.loading_tips);
        setOnClickListener(this);
    }


    public void goneLoading() {
        this.setVisibility(GONE);
    }

    public void showLoading() {
        progressBar.setVisibility(VISIBLE);
        textView.setVisibility(GONE);
        can = false;
    }

    public void showNoData() {
        progressBar.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        textView.setText("暂无数据");
        can = true;
    }

    public void showError() {
        progressBar.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        textView.setText("加载失败，点击重试");
        can = true;
    }


    public void setListener(RefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (can) {
            showLoading();
            if (listener != null) {
                listener.refresh();
            }
        }
    }

    public interface RefreshListener {
        void refresh();
    }
}
