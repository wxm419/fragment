package com.fheebiy.fragment;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.fheebiy.R;
import com.fheebiy.adapter.ComplexVpAdapter;
import com.fheebiy.adapter.ImagePagerAdapter;
import com.fheebiy.util.Log;
import com.fheebiy.view.AutoScrollViewPager;
import com.fheebiy.view.FaceLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwenbo on 15/5/23.
 */
public class TabSixFragment extends Fragment implements View.OnClickListener, FaceLinearLayout.onTitleLensiter,Runnable {
    private AutoScrollViewPager autoScrollViewPager;

    private List<Integer> imageIdList;


    private ViewPager viewPager;

    private List<Fragment> list;

    private ComplexVpAdapter adapter;

    private TabSevenFragment sevenFragment;
    private Fragment eightFragment;
    private Fragment nineFragment;

    private TextView title1;

    private TextView title2;

    private TextView title3;

    private List<TextView> titles = new ArrayList<TextView>();

    private ImageView strip;

    private int offset;

    private int currIndex;

    private int current_x = 0;

    private FragmentActivity context;

    public static final String TAG = "TabSixFragment";

    protected FaceLinearLayout mTotalLayout;

    protected int mTitleSpeed;
    public int mOffsety;
    protected boolean isScroll;
    protected boolean mDoWork;

    protected int mTitleHeight;
    protected int mTabHeight;
    protected int mRelativeHeight;
    private int mInitFaceHeight;

    protected int mScrollState;
    private boolean mIsFreshFromNet;

    private int initHeight;

    /** 屏幕密度 */
    private float mDensity;
   // private ScrollableLinearLayout mTotalLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.tab6_2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        findViews(view);
        initRecycleView();
        initStripImageView();
        initUI();
        bindListener();


    }

    private void initRecycleView() {
         final View view  =LayoutInflater.from(getActivity()).inflate(R.layout.tab6_banner,null);
        autoScrollViewPager = (AutoScrollViewPager) view.findViewById(R.id.asvierpager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.banner1);
        imageIdList.add(R.drawable.banner2);
        imageIdList.add(R.drawable.banner3);
        imageIdList.add(R.drawable.banner4);
        autoScrollViewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
        // autoScrollViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());

        //viewPager = (ViewPager) view.findViewById(R.id.anim_viewpager);
        title1.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },10);
        mTotalLayout.addView(view, 0);
    }


    private void bindListener() {

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //  Log.d(TAG, "i=" + i + ",v=" + v + ",i2=" + i2);
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
                Log.d(TAG, "current  index=" + i);
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


        title1.setOnClickListener(this);
        title2.setOnClickListener(this);
        title3.setOnClickListener(this);

       // scrollableLinearLayout.setOnScrollListener(this);
    }

    private void findViews(View view) {
        mDensity = getResources().getDisplayMetrics().density;
        viewPager = (ViewPager) view.findViewById(R.id.anim_viewpager);

        title1 = (TextView) view.findViewById(R.id.anim_title1);
        title2 = (TextView) view.findViewById(R.id.anim_title2);
        title3 = (TextView) view.findViewById(R.id.anim_title3);

        strip = (ImageView) view.findViewById(R.id.animation_strip);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);

        mTotalLayout = (FaceLinearLayout) view.findViewById(R.id.main_container);
        mTotalLayout.setOnTitleLensiter(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTotalLayout.postOnAnimation(this);
        } else {
            mTotalLayout.postDelayed(this, 10);
        }
        //mTotalLayout.setOnTitleLensiter(this);
       /* mTotalLayout.post(new Runnable() {
            @Override
            public void run() {
               ViewGroup.LayoutParams layoutParams = mTotalLayout.getLayoutParams();
                layoutParams.height += mDensity*180;
             //   mTotalLayout.setLayoutParams(layoutParams);
            }
        });*/

    /*    title1.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = mTotalLayout.getLayoutParams();
                layoutParams.height += mDensity*180;
                mTotalLayout.setLayoutParams(layoutParams);
            }
        },10);*/
    }


    private void initUI() {
        context = getActivity();
        sevenFragment = new TabSevenFragment();
        eightFragment = new TabEightFragment();
        nineFragment = new TabNineFragment();
        list = new ArrayList<Fragment>();
        list.add(sevenFragment);
        list.add(eightFragment);
        list.add(nineFragment);

        adapter = new ComplexVpAdapter(context.getSupportFragmentManager(), list, getActivity());

        viewPager.setAdapter(adapter);
    }

    private void initStripImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);      //这句，去掉动画就失效了,很神奇，这是为什么了
        int screenWidth = dm.widthPixels;
        offset = screenWidth / 3;
    }

    @Override
    public void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        autoScrollViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        // start auto scroll when onResume
        autoScrollViewPager.startAutoScroll();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim_title1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.anim_title2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.anim_title3:
                viewPager.setCurrentItem(2);
                break;


        }
    }


    @Override
    public void run() {
        if (mScrollState == FaceLinearLayout.ACTION_TITLE_STOP) {
            if (mOffsety > -mTitleHeight / 2) {
                mOffsety += mTitleSpeed;
            } else {
                mOffsety -= mTitleSpeed;
            }
            if (mOffsety < -mTitleHeight) {
                mOffsety = -mTitleHeight;
            } else if (mOffsety > 0) {
                mOffsety = 0;
            }
        }
        if (mOffsety == 0 || mOffsety == -mTitleHeight) {
            if (mDoWork) {

                scrollToPosition(mOffsety);
                mDoWork = false;
            }
        } else {
//            scrollToPosition(mOffsety);
//            mTotalLayout.setTopHeight(mTitleHeight + mOffsety);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTotalLayout.postOnAnimation(this);
        } else {
            mTotalLayout.postDelayed(this, 10);
        }
    }

    @Override
    public boolean scrollTo(float offsety, int state) {
        mScrollState = state;
        isScroll = false;
        if (mTitleHeight == 0) {
            mTitleHeight = autoScrollViewPager.getHeight();
        }
        mOffsety += offsety;
        Log.d(TAG,"mOffsety="+mOffsety+",mTitleHeight="+mTitleHeight);
        if (mOffsety < -mTitleHeight) {
            mOffsety = -mTitleHeight;
            isScroll = false;
        } else if (mOffsety > 0) {
            mOffsety = 0;
            isScroll = false;
        } else {
            scrollToPosition(mOffsety);
            isScroll = true;
        }
        if (!mDoWork) {
            mDoWork = true;
        }
        return isScroll;
    }


    protected void scrollToPosition(float position){
        mTotalLayout.scrollTo(0, -(int)(position));
        float persent = (float) (position) / mTitleHeight;
        int sh =0;
        //ViewGroup.LayoutParams layoutParams = mTotalLayout.getLayoutParams();
        //layoutParams.height = initHeight + (int)Math.abs(position);
        //mTotalLayout.setLayoutParams(layoutParams);
//        if(context!=null){
//            sh= ((MainActivity) context).updateTabWidget(persent);
//        }
//        sh= ((MainActivity) context).getmWidgetHeight();
//        if (sh!=0) {
//            caculateViewPageHeight(sh);
//        }
    }
   /* @Override
    public boolean canScrollUp() {
        return false;
    }

    @Override
    public boolean canScrollDown() {
        return false;
    }

    @Override
    public ListView getListView() {
       // return sevenFragment.getListView();
        return sevenFragment.getListView();
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onScrollChanged(int scrollY) {

    }*/
}