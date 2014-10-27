package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-10-8.
 */
public class MenuPopupWindow extends PopupWindow{

    private Context ctx;

    public MenuPopupWindow(Context context) {
        super(context);
        this.ctx = context;
        init(context);
    }

    public MenuPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
    }

    public MenuPopupWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
    }


    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.popmenu,null);
        setContentView(contentView);
        //int h = context.getWindowManager().getDefaultDisplay().getHeight();
      //  int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ctx.getResources().getDimensionPixelSize(R.dimen.space_180));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.AnimationPreview);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        setOutsideTouchable(true);
    }

    public void showPopupWindow(View parent, int height) {
        if (!this.isShowing()) {
           // this.showAsDropDown(parent, -100, -4);
            this.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP,15,height);
        } else {
            this.dismiss();
        }
    }

}
