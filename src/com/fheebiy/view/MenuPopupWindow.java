package com.fheebiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.fheebiy.R;
import com.fheebiy.adapter.HeroLvAdapter;
import com.fheebiy.model.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-10-8.
 */
public class MenuPopupWindow extends PopupWindow{

    private Context ctx;

    public MenuPopupWindow(Context context) {
        super(context);
        this.ctx = context;
        init(context);
    }

    public MenuPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
    }

    public MenuPopupWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
    }


    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.popmenu1,null);
        setContentView(contentView);
        /**
         * 这个地方，有个很有意思的事情，如果去掉这设置contentView高度或宽度的代码，发现，根本看不到window弹出了
         * 其实这只是表象，window弹出了，但是我们却看不到，因为此时window的width或height为0，这点
         * 很怪异，因为我们看布局文件，发现其  android:layout_width="180dp" android:layout_height="wrap_content
         * 这明明有值180dp，wrap_content其实是这样的里面有一个基本理论没搞清楚，其实这里不管你将contentView的layout_width和
         * layout_height的值修改成多少，都不会有任何效果的，因为这两个值现在已经完全失去了作用。
         * 平时我们经常使用layout_width和layout_height来设置View的大小，并且一直都能正常工作，
         * 就好像这两个属性确实是用于设置View的大小的。而实际上则不然，它们其实是用于设置View在
         * 布局中的大小的，也就是说，首先View必须存在于一个布局中，之后如果将layout_width设置
         * 成match_parent表示让View的宽度填充满布局，如果设置成wrap_content表示让View的宽度
         * 刚好可以包含其内容，如果设置成具体的数值则View的宽度会变成相应的数值。这也是为什么这
         * 两个属性叫作layout_width和layout_height，而不是width和height。
         *
         * 我们可以看到，R.layout.popmenu确实不在任何布局中，要想改变，这个，最合理的办法，不是我们下面
         * 这句设定宽度的代码，而是将R.layout.popmenu外面再套一层布局即可。
         *
         * 看到这里，也许有些朋友心中会有一个巨大的疑惑。不对呀！平时在Activity中指定布局文件的时候，
         * 最外层的那个布局是可以指定大小的呀，layout_width和layout_height都是有作用的。确实，这主要是因为，
         * 在setContentView()方法中，Android会自动在布局文件的最外层再嵌套一个FrameLayout，所以layout_width
         * 和layout_height属性才会有效果。
         *
         * 这里面更深层的道理，在inflater.inflate(R.layout.popmenu,null);对于源码，没有看或者没有耐心研究确实遗憾
         *
         * 自此我也有些感悟，比如以前在做这个window的时候，只是在做而已，至于上述疑惑，没有去理解，更没有去研究，这实在
         * 是肤浅至极。应用只能是从表面上解决问题，只有扎实的理论基础与实践结合才能做到无往不利，事半功倍.
         *
         * 至于此PopupWindow，必须设置高度和宽度，但是，如果用R.layout.popmenu，要想显示的设置， android:layout_width="xxdp"
         * android:layout_height="xxdp"在R.layout.popmenu设置是没用的，必须用
         *  this.setWidth(ctx.getResources().getDimensionPixelSize(R.dimen.space_180));
         *  this.setHeight(ctx.getResources().getDimensionPixelSize(R.dimen.space_180));
         * 但是用R.layout.popmenu1，则设置即可
         *   this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
         *   this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
         */

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT); //这两句代码缺一不可，必须同时存在
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.AnimationPreview);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        setOutsideTouchable(true);
    }

    public void showPopupWindow(View parent, int height) {
        if (!this.isShowing()) {
           // this.showAsDropDown(parent, -100, -4);
            this.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP,15,height);
        } else {
            this.dismiss();
        }
    }

}
