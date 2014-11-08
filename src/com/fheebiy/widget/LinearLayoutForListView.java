package com.fheebiy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class LinearLayoutForListView extends LinearLayout{
    private BaseAdapter mAdapter;
    private OnClickListener onClickListener = null;
    private int mFooterCount = 0;

    public LinearLayoutForListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutForListView(Context context) {
        super(context);
    }

    /**
     * 由于AdapterView绑定的Adapter
     * 的notyfyDataSetChanged()方法
     * 在非AdapterView上不起作用
     * 为了改善 重新new出来的Adapter所
     * 消耗的性能 所以自己写了这个方法
     */
    public void notifyDataSetChanged(){
        int i = this.getChildCount();
        int j = mAdapter.getCount();
        int footerCount = this.mFooterCount;
        int insertIndex = i-footerCount;
        if(j > insertIndex){

            for( ; i<j; i++){
                this.addView(mAdapter.getView(i, null, null), insertIndex);
            }

        }
    }

    public void clearOldView() {
        this.removeAllViews();
    }

    public void setAdapter(BaseAdapter adapter){
        this.mAdapter = adapter;
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = mAdapter.getView(i, null, null);
            addView(view,i);
        }
    }

    /**
     * 用来判断是否有FooterCount;
     */
    public int getFooterViewsCount(){
        return this.mFooterCount;
    }

    /**
     * 添加Footer
     * @param view 作为footer的view
     */
    public void addFooterView(View view){
        if(view!=null){
            addView(view);
            mFooterCount++;
        }
    }

    /**
     * 移除Footer
     * @param view 作为footer的view
     */
    public void removeFooterView(View view){
        if(view!=null)
        {removeView(view);
            mFooterCount--;
        }
    }

    //item注册监听事件
    public void setOnItemclickLinstener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            this.getChildAt(i).setOnClickListener(onClickListener);
        }
    }

    public BaseAdapter getAdpater() {
        return mAdapter;
    }

    public OnClickListener getOnclickListner() {
        return onClickListener;
    }




}