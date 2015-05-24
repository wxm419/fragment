package com.fheebiy.activity.PullToScaleImg;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fheebiy.R;

/**
 * Created by zhouwenbo on 15/5/10.
 */
public class PullToScaleImgActivity extends Activity {

    private ImageView imageView;

    private float lastY;
    private int initHeight = 700;

    String TAG = "PullToScaleImgActivity";

    private Matrix mCurrentMatrix = new Matrix();

    private  Matrix mMatrix=new Matrix();

    /**  图片长度*/
    private float mImageWidth;
    /**  图片高度 */
    private float mImageHeight;
    /**  原始缩放级别 */
    private float mScale;

    private float mStartDis;

    private float last = initHeight;

    private float current;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_to_scale_img);

        imageView = (ImageView)findViewById(R.id.img);
        initData();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float cy = ev.getY();
                scale((int) (cy - lastY));
                break;
            case MotionEvent.ACTION_UP:
                reset();
                break;
        }


        return super.dispatchTouchEvent(ev);
    }


    public void scale(int y) {
        //imageView.setScaleX(1 + scaleOfLayout);
        //imageView.setScaleY(1 + scaleOfLayout);
        if(y>0){
            ViewGroup.LayoutParams  layoutParams = imageView.getLayoutParams();
            // layoutParams.width =(int) (layoutParams.width*(1+scaleOfLayout));
            float scale = (initHeight+y)/(float)initHeight;
            layoutParams.height = initHeight+y;
            Log.d("zhouwenbo","height="+layoutParams.height+", h="+initHeight*scale);
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            Log.d(TAG, "imageView.getHeight() =" + imageView.getHeight() + "scale=" + scale);
            //imageView.setMinimumHeight((int)(imageView.getHeight()*(1+scaleOfLayout)));
            imageView.setLayoutParams(layoutParams);
        }

    }


    public void scale2(int y){
        if(y > 0){
            mCurrentMatrix.set(imageView.getImageMatrix());
            float scale = (initHeight+y)/(float)initHeight;
            Log.d("zhouwenbo", "y="+y);
         /*   current = last + y;
            scale = current/last;*/

            float[] values=new float[9];
            mCurrentMatrix.getValues(values);
            PointF centerF=getCenter(scale,values);
            //mCurrentMatrix.postScale(scale, scale,centerF.x, centerF.y);
            mCurrentMatrix.setScale(scale, scale, centerF.x, centerF.y);
            imageView.setImageMatrix(mCurrentMatrix);
        }



    }

    /**
     *  获取缩放的中心点。
     *  @param scale
     *  @param values
     *  @return
     */
    private PointF getCenter(float scale,float[] values) {
        //缩放级别小于原始缩放级别时或者为放大状态时，返回ImageView中心点作为缩放中心点
        if(scale*values[Matrix.MSCALE_X]<mScale||scale>=1){
            return new PointF(imageView.getWidth()/2,imageView.getHeight()/2);
        }
        float cx=imageView.getWidth()/2;
        float cy=imageView.getHeight()/2;
        //以ImageView中心点为缩放中心，判断缩放后的图片左边缘是否会离开ImageView左边缘，是的话以左边缘为X轴中心
        if((imageView.getWidth()/2-values[Matrix.MTRANS_X])* scale<imageView.getWidth()/2)
            cx=0;
        //判断缩放后的右边缘是否会离开ImageView右边缘，是的话以右边缘为X轴中心
        if((mImageWidth*values[Matrix.MSCALE_X]+values[Matrix.MTRANS_X])* scale<imageView.getWidth())
            cx=imageView.getWidth();
        return new PointF(cx,cy);
    }



    public void reset() {
        ViewGroup.LayoutParams  layoutParams = imageView.getLayoutParams();
        layoutParams.height = initHeight;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        Log.d(TAG, "scale execut");
    }


    /**
     *   初始化模板Matrix和图片的其他数据
     */
    private void initData() {
        //设置完图片后，获取该图片的坐标变换矩阵
        mMatrix.set(imageView.getImageMatrix());
        float[] values=new float[9];
        mMatrix.getValues(values);
        //图片宽度为屏幕宽度除缩放倍数
        mImageWidth=imageView.getWidth()/values[Matrix.MSCALE_X];
        mImageHeight=(imageView.getHeight()-values[Matrix.MTRANS_Y]*2)/values[Matrix.MSCALE_Y];
        mScale=values[Matrix.MSCALE_X];
    }

}