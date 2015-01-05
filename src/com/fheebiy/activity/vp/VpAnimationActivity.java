package com.fheebiy.activity.vp;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.adapter.ComplexVpAdapter;
import com.fheebiy.fragment.TabFourFragment;
import com.fheebiy.fragment.TabThreeFragment;
import com.fheebiy.fragment.TabTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-8-4.
 * <p/>
 * title strip 带动画的viewpager
 */

public class VpAnimationActivity extends FragmentActivity {

    static final String TAG = "VpAnimationActivity";

    private ViewPager viewPager;

    private List<Fragment> list = new ArrayList<Fragment>();

    private TextView title1;

    private TextView title2;

    private TextView title3;

    private List<TextView> titles = new ArrayList<TextView>();

    private ImageView strip;

    private int offset;

    private int currIndex;

    private int current_x = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpanimation);
        findViews();
        initStripImageView();
        initUI();
        bindListener();
    }


    private void findViews() {
        viewPager = (ViewPager) findViewById(R.id.anim_viewpager);

        title1 = (TextView) findViewById(R.id.anim_title1);
        title2 = (TextView) findViewById(R.id.anim_title2);
        title3 = (TextView) findViewById(R.id.anim_title3);

        strip = (ImageView) findViewById(R.id.animation_strip);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);
    }

    private void bindListener() {

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                Log.d(TAG, "i=" + i + ",v=" + v + ",i2=" + i2);
            /*
                int next_x = 0;
                int x = (int)(offset*v);
                Log.d(TAG,"x="+x+",current_x="+current_x);
                if (i == currIndex) {
                    next_x = current_x + x;
                } else {
                    next_x = -current_x + x;
                }
                Log.d(TAG,"current_x="+current_x+",next_x="+next_x);
                Animation animation = new TranslateAnimation(current_x, next_x, 0, 0);
                current_x = next_x;
                animation.setFillAfter(true);
                animation.setDuration(100);
                strip.startAnimation(animation);*/
            }

            @Override
            public void onPageSelected(int i) {
                Animation animation = new TranslateAnimation(currIndex * offset, i * offset, 0, 0);
                currIndex = i;
                animation.setFillAfter(true);
                animation.setDuration(300);
                strip.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        for (int i = 0; i < titles.size(); i++) {
            View view = titles.get(i);
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(index);
                }
            });
        }
    }


    private void initUI() {

        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();

        list.add(twoFragment);
        list.add(threeFragment);
        list.add(fourFragment);

        ComplexVpAdapter adapter = new ComplexVpAdapter(getSupportFragmentManager(), list, this);

        viewPager.setAdapter(adapter);
    }

    private void initStripImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);      //这句，去掉动画就失效了,很神奇，这是为什么了
        int screenWidth = dm.widthPixels;
        offset = screenWidth / 3;
    }

}
