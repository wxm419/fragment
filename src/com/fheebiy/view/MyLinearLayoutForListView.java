package com.fheebiy.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.fheebiy.adapter.LinearLayoutBaseAdapter;

public class MyLinearLayoutForListView extends LinearLayout {
    private LinearLayoutBaseAdapter adapter;
    private OnItemClickListener onItemClickListener;

    public MyLinearLayoutForListView(Context context) {
        super(context);
    }

    public MyLinearLayoutForListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(LinearLayoutBaseAdapter adapter) {
        this.adapter = adapter;
        // setAdapter 时添加 view
        bindView();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    /**
     * 绑定 adapter 中所有的 view
     */
    private void bindView() {
        if (adapter == null) {
            return;
        }

        for (int i = 0; i < adapter.getCount(); i++) {
            final View v = adapter.getView(i);
            final int tmp = i;
            final Object obj = adapter.getItem(i);

            // view 点击事件触发时回调我们自己的接口
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(v, obj, tmp);
                    }
                }
            });

            addView(v);
        }
    }

    /**
     *
     * 回调接口
     */
    public interface OnItemClickListener {
        /**
         *
         * @param v
         *            点击的 view
         * @param obj
         *            点击的 view 所绑定的对象
         * @param position
         *            点击位置的 index
         */
        public void onItemClicked(View v, Object obj, int position);
    }



}