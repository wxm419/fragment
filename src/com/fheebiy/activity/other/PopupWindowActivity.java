package com.fheebiy.activity.other;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.MenuPopupWindow;

/**
 * Created by bob zhou on 14-10-8.
 *
 * 此例子，不仅作为PopupWindow应用实例，更是view基础实例
 * 其包含view基本理论
 *
 */
public class PopupWindowActivity extends Activity {

    private ImageButton menuBtn;

    private MenuPopupWindow popupWindow;

    private LinearLayout rootLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        rootLayout = (LinearLayout)findViewById(R.id.pop_root_layout);


        /**
         * 看到这里，也许有些朋友心中会有一个巨大的疑惑。不对呀！平时在Activity中指定布局文件的时候，
         * 最外层的那个布局是可以指定大小的呀，layout_width和layout_height都是有作用的。确实，这主要是因为，
         * 在setContentView()方法中，Android会自动在布局文件的最外层再嵌套一个FrameLayout，所以layout_width
         * 和layout_height属性才会有效果。
         *
         * 此可证明上面这段话的正确性
         */
        ViewParent viewParent = rootLayout.getParent();
        Log.d(CommonUtil.LOG_TAG, "the parent of rootLayout is " + viewParent);


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


    public void onBackPressClick(View view){
        this.finish();
    }


}