package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.fheebiy.R;
import com.fheebiy.util.Log;

/**
 * Created by Lenovo on 15-2-4.
 * <a> http://blog.csdn.net/guolin_blog/article/details/9097463 </a> 两篇博客
 * <a> http://blog.csdn.net/guolin_blog/article/details/9153747 </a>
 * blog 地址
 */
public class EventDispatcherActivity extends Activity {

    private Button btn;

    private ImageView imageView;

    public static final String TAG = "EventDispatcherActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_dispather);

        btn = (Button) findViewById(R.id.btn1);
        imageView = (ImageView)findViewById(R.id.imageview);

        bindListener();


    }

    private void bindListener() {


        /**
         * 可见，先执行的，onTouch 再执行的click,onTouch先执行down，在执行up,共两次执行(你还可能会有多次ACTION_MOVE的执行，如果你手抖了一下)
         *
         * 如果把onTouch返回值设为true,我们发现，onClick方法不再执行了！为什么会这样呢？你可以先理解成onTouch方法返回true就认为这个事件被onTouch消费掉了，因而不会再继续向下传递。
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick execute");
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch execute , event.action=" + event.getAction());      //MotionEvent常量，0--->down, 1----up, 2---move
                return true;
            }
        });


   /*     imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick execute");
            }
        });
*/
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch execute , event.action=" + event.getAction());
                return false;
            }
        });

        /**
         * imageView setOnTouchListener只会执行 down，因为imageView是enable == false，但是你还setOnClickListener，则都会执行其他action
         * 这是因为，setOnClickListener会将ImageView设置成enable = true的
         */






    }
}