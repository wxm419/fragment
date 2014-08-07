package com.fheebiy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
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
 *
 * 自定义的viewpager demo
 */

public class VpComplexActivity extends FragmentActivity{

    private ViewPager viewPager;

    private List<Fragment> list = new ArrayList<Fragment>();

    private View strip1;

    private View strip2;

    private View strip3;

    private TextView title1;

    private TextView title2;

    private TextView title3;

    private List<TextView> titles = new ArrayList<TextView>();

    private List<View> strips = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpcomplex);
        findViews();
        initUI();
        bindListener();
    }


    private void findViews(){
        viewPager = (ViewPager)findViewById(R.id.vpcomplex_vp);
        strip1 = findViewById(R.id.tab_strip1);
        strip2 = findViewById(R.id.tab_strip2);
        strip3 = findViewById(R.id.tab_strip3);
        strips.add(strip1);
        strips.add(strip2);
        strips.add(strip3);

        title1 = (TextView)findViewById(R.id.title1);
        title2 = (TextView)findViewById(R.id.title2);
        title3 = (TextView)findViewById(R.id.title3);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);

    }

    private void bindListener(){

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                for(View v: strips){
                    v.setVisibility(View.INVISIBLE);
                }
                strips.get(i).setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        for(int i = 0; i<titles.size(); i++){
            View view = titles.get(i);
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(View strip: strips){
                        strip.setVisibility(View.INVISIBLE);
                    }
                    strips.get(index).setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(index);
                }
            });

        }

    }


    private void initUI(){

        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();

        list.add(twoFragment);
        list.add(threeFragment);
        list.add(fourFragment);

        ComplexVpAdapter adapter = new ComplexVpAdapter(getSupportFragmentManager(),list,this);

        viewPager.setAdapter(adapter);
    }



}
