package com.fheebiy.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;

/**
 * Created by Administrator on 14-10-22.
 */
public class CommonUtil {

    /**
     * 将dp转化为px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 获取状态栏高度
     * @param cx
     * @return 返回数值单位为px
     */
    public static int getTitleBarHeight(Activity cx){
        Rect frame = new Rect();
        cx.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }


}
