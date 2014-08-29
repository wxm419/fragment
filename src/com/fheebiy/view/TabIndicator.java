package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-8-29.
 */
public class TabIndicator extends RelativeLayout{

    private int iconRes;

    private String title;

    private Context ctx;

    private TextView tv;

    private ImageView icon;


    public TabIndicator(Context context) {
        super(context);
        this.ctx = context;
        initView();
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        initView();
    }

    public TabIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        initView();
    }



    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.tab_indicator,this);
        icon = (ImageView)view.findViewById(R.id.indicator_img);
        tv = (TextView)view.findViewById(R.id.indicator_text);

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        tv.setText(title);
        //invalidate();
    }

    public int getIconRes() {
        return iconRes;
    }


    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
        icon.setImageResource(iconRes);
        //invalidate();
    }
}
