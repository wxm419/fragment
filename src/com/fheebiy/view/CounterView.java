package com.fheebiy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-2-9.
 * 自定义View-----计数器-----自绘控件
 */
public class CounterView extends View implements View.OnClickListener{

    private Paint mPaint;

    private Rect mBounds;

    private int count;

    private Context ctx;


    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);       //消除锯齿的
        mBounds = new Rect();
        setOnClickListener(this);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);       //消除锯齿的
        mBounds = new Rect();
        setOnClickListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);

        /**
         *   Rect（int left,int top,int right,int bottom）
         *
         *   left
         *   矩形左上角X坐标值
         *   top
         *   矩形左上角Y坐标值
         *   right
         *   矩形右下角X坐标值
         *   bottom
         *   矩形右下角Y坐标值
         *
         *   矩形的宽= right -left
         *   矩形的高= bottom_top
         *
         *
         *   需要注意的是，canvas的大小是由xml中设置的，都是100dp,位置也是在xml设置的居中的
         *   而canvas.drawRect中，left,top ,right, bottom 是相对于canvas的而不是屏幕的(这点很重要)，
         *   也就是，此坐标系的原点是canvas的左上角的点.(left，top)为此坐标系(canvas左上角为原点)中矩形
         *   的左上角的点，(right,bottom)为右下角的点，这样就很好理解了.
         *
         */


        canvas.drawRect(50, 200, getWidth(), getHeight(), mPaint);


        /**
         * 这个地方，文字的绘制在canvas上的一些设置，其中颜色和字体大小很好理解
         * mBounds-----------代表文字外边框，也就是文字边界
         */
        mPaint.setColor(Color.parseColor("#FFFFFF"));
        mPaint.setTextSize(ctx.getResources().getDimensionPixelSize(R.dimen.space_16));
        String text = String.valueOf(count);
        mPaint.getTextBounds(text,0,text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();


        /**
         * float x  The x-coordinate of the origin of the text being drawn
         *     绘制文字的起始x坐标，相对于canvas的(不是屏幕的，这点很重要)
         *
         *
         * float y  The y-coordinate of the origin of the text being drawn
         *     绘制文字的起始y坐标，相对于canvas的(不是屏幕的，这点很重要)
         *
         * 此处是将文字绘制到canvas正中间的位置，通过设置文字绘制起始位置在canvas
         * 中的坐标点来实现的，只要明确原理，算法就比较简单了
         *
         */
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);

    }

    @Override
    public void onClick(View v) {
        count ++;
        invalidate();
    }
}
