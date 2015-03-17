package com.fheebiy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.fheebiy.R;

/**
 * Created by Lenovo on 15-3-16.
 * <p/>
 * 圆角矩形的ImageView，比RoundImageView简单
 * 很多东西需要一直练习或者使用，现在可能会做，可是过几天没准儿有忘了
 * 孰能生巧，就像卖油翁一样，经验往往很重要，只有有了经验，才有自己创造的可能
 */
public class RoundRectImageView extends ImageView {

    private float radius = 0;

    private Context context;


    public RoundRectImageView(Context context) {
        super(context);
        this.context = context;

    }

    public RoundRectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);

    }

    public RoundRectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundRectImageView);
        radius = typedArray.getDimension(R.styleable.RoundRectImageView_radius, 0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (radius == 0) {
            super.onDraw(canvas);
        } else {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            if(drawable.getClass() == NinePatchDrawable.class){
                return;
            }
            if (getWidth() * getHeight() == 0) {
                return;
            }
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap rBitmap = getRoundRectBitmap(bitmap);
            canvas.drawBitmap(rBitmap,0,0,null);
        }
    }


    /**
     * 这段是关键中关键，获取了一个圆角矩形的bitmap,其中首先要对bitmap进行压缩处理,然后要做一个bitmap的output，
     * 将此output传入Canvas的构造器中，然后，设置Canvas的透明度，为完全透明,然后，再在canvas上画出一个圆角矩形，
     * 画好之后，设定paint的setXfermode为SRC_IN，然后在Canvas上画bitmap，这时候才把一个圆角矩形画好，将此bitmap return即可
     * @param bitmap
     * @return 圆角矩形
     */
    private Bitmap getRoundRectBitmap(Bitmap bitmap){
        //压缩图片，去掉这句代码，会看到图片只显示左上角的一块儿区域
        Bitmap scaleSrcBitmap = Bitmap.createScaledBitmap(bitmap,getWidth(),getHeight(), true);
        Bitmap output = Bitmap.createBitmap(scaleSrcBitmap.getWidth(),scaleSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0,0,0,0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,radius,radius,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaleSrcBitmap,0f,0f,paint);
        scaleSrcBitmap = null;
        bitmap = null;
        return output;
    }

}
