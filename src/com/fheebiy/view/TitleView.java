package com.fheebiy.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.fheebiy.R;

/**
 * Created by bob zhou on 15-2-9.
 *
 * 自定义view,非常简单
 */
public class TitleView extends FrameLayout implements View.OnClickListener {


    private Context ctx;

    private ImageButton backBtn;

    private ImageButton menuBtn;

    private TextView titleTv;

    private MenuClickListener menuClickListener;


    private String text;

    private int textColor;

    private float textSize;

    private boolean showMenu;


    public TitleView(Context context) {
        super(context);
        this.ctx = context;
        LayoutInflater.from(context).inflate(R.layout.title_view, this);
        init();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        initWithStyle(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_view, this);
        init();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        initWithStyle(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_view, this);
        init();
    }


    private void initWithStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        text = a.getString(R.styleable.TitleView_text);
        textColor = a.getColor(R.styleable.TitleView_textColor, Color.parseColor("#FFFFFF"));
        textSize = a.getDimension(R.styleable.TitleView_textSize, 18f);
        showMenu = a.getBoolean(R.styleable.TitleView_showMenu, false);
    }


    private void init() {
        backBtn = (ImageButton) findViewById(R.id.titleview_back_imgBtn);
        menuBtn = (ImageButton) findViewById(R.id.titleview_menu_imgBtn);
        titleTv = (TextView) findViewById(R.id.titleview_text_tv);

        titleTv.setText(text);
        titleTv.setTextColor(textColor);
        titleTv.setTextSize(textSize);

        menuBtn.setVisibility(showMenu ? VISIBLE : GONE);

        backBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleview_back_imgBtn:
                ((Activity) ctx).finish();
                break;
            case R.id.titleview_menu_imgBtn:
                if (menuClickListener != null) {
                    menuClickListener.onMenuClick(v);
                }
                break;
            default:

                break;

        }

    }


    public interface MenuClickListener {
        void onMenuClick(View view);
    }

    public void setMenuClickListener(MenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    public void setTitleText(String text) {
        this.text = text;
        //invalidate();
        titleTv.setText(text);
    }

    public void setTitleTextColor(int color) {
        this.textColor = color;
        titleTv.setTextColor(color);
    }

    public void setTitleTextSize(float textSize) {
        this.textSize = textSize;
        titleTv.setTextSize(textSize);
    }

    public void showMenu(boolean showMenu) {
        this.showMenu = showMenu;
        menuBtn.setVisibility(showMenu ? VISIBLE : GONE);
    }
}
