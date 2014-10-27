package com.fheebiy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.MenuPopupWindow;

/**
 * Created by bob zhou on 14-10-8.
 */
public class PopupWindowActivity extends Activity {

    private ImageButton menuBtn;

    private MenuPopupWindow popupWindow;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        menuBtn = (ImageButton) findViewById(R.id.circle_index_menu_imgBtn);
        popupWindow = new MenuPopupWindow(this);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int height = CommonUtil.dip2px(PopupWindowActivity.this, 48) + CommonUtil.getTitleBarHeight(PopupWindowActivity.this);
                popupWindow.showPopupWindow(menuBtn,height);
            }
        });
    }


}