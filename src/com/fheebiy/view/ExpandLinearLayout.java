package com.fheebiy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.fheebiy.R;

public class ExpandLinearLayout extends LinearLayout {

    private BaseAdapter adapter;
    private OnItemClickListener onItemClickListener;

    private DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }
    };
    private int divider;
    private int divideHeight;
    private int itemSelector;

    public ExpandLinearLayout(Context context) {
        super(context);
    }

    public ExpandLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithStyle(context, attrs);
    }

    private void initWithStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandLinearLayout);
        divider = a.getResourceId(R.styleable.ExpandLinearLayout_divider,R.drawable.listview_divider);            // <!-- format="color|reference" 如果写两个的话，代码里写getDrawable -->
        divideHeight = (int) a.getDimension(R.styleable.ExpandLinearLayout_divideHeight, 1f);
        itemSelector = a.getResourceId(R.styleable.ExpandLinearLayout_itemSelector, R.drawable.list_item_selector);
        a.recycle();
    }

    public void setAdapter(BaseAdapter dynamicProductsAdapter) {
        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(observer);
        }
        this.adapter = dynamicProductsAdapter;
        notifyDataSetChanged();
        adapter.registerDataSetObserver(observer);
    }


    /**
     * 此方法，性能上可能有问题，因为如果是上拉加载更多的时候，也要removeAllViews
     * 然后再重绘，此不可取
     */
    private void notifyDataSetChanged() {
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }
        int i = this.getChildCount();
        int j = adapter.getCount();
        if (j > i) {  //加载更多
            for (; i < j; i++) {
                addItemView(i);
                addDivider();
            }
        } else {     //刷新
            this.removeAllViews();
            for (int k = 0; k < j; k++) {
                addItemView(k);
                addDivider();
            }
        }
    }

    private void addItemView(int i) {
        View view = adapter.getView(i, null, this);
        if (view != null) {
            view.setBackgroundResource(itemSelector);
            this.addView(view);
            final int tmp = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, tmp);
                    }
                }
            });
        }
    }

    public void addDivider() {
        View div = new View(getContext());
        div.setBackgroundResource(divider);
        LayoutParams params = new LayoutParams(-1, divideHeight);
        addView(div, params);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int index);
    }

}
