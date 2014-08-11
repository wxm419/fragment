package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.fheebiy.R;

/**
 * Created by Administrator on 14-8-11.
 */
public class MySlideView extends LinearLayout {

    private Context context;

    private LinearLayout content_view;


    public MySlideView(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    public MySlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initUI();
    }

    public MySlideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initUI();
    }


    private void initUI() {
        setOrientation(LinearLayout.HORIZONTAL);
        View.inflate(context, R.layout.myslide, this);
        content_view = (LinearLayout)findViewById(R.id.view_content);
    }

    public void setContentView(View view){
        this.content_view.addView(view);
    }

}
