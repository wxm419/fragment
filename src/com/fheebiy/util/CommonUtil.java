package com.fheebiy.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.fheebiy.model.Hero;

import java.util.ArrayList;
import java.util.List;

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



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public static List<Hero> getHeroListData() {
        List<Hero> heros = new ArrayList<Hero>();

        for (int i = 0; i < 10; i++) {
            Hero hero = new Hero();
            hero.setName("杨过" + i);
            hero.setSkill("黯然销魂掌");
            hero.setFrom("神雕侠侣");
            heros.add(hero);
        }

        return heros;
    }


    public static void toast(Context ctx, String msg){
        Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
    }



    public static List<String> getStringList(){
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            list.add("像风一样自由"+i);
        }

        return list;
    }



}
