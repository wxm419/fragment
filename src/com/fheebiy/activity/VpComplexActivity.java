package com.fheebiy.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpcomplex);
        viewPager = (ViewPager)findViewById(R.id.vpcomplex_vp);

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
