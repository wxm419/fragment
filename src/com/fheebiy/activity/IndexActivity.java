package com.fheebiy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.activity.PullToScaleImg.PullToScaleImgActivity;
import com.fheebiy.activity.animation.FrameAnimationActivity;
import com.fheebiy.activity.animation.PropertyAnimationActivity;
import com.fheebiy.activity.animation.TweenAnimationActivity;
import com.fheebiy.activity.aquery.AQueryActivity;
import com.fheebiy.activity.async.AsyncActivity;
import com.fheebiy.activity.basic.*;
import com.fheebiy.activity.bitmap.BitmapCompressActivity;
import com.fheebiy.activity.broadcast.BroadcastActivity;
import com.fheebiy.activity.communicate.Communicate2Activity;
import com.fheebiy.activity.communicate.Communicate3Activity;
import com.fheebiy.activity.communicate.CommunicateActivity;
import com.fheebiy.activity.listview.LoadMoreListViewActivity;
import com.fheebiy.activity.listview.PullToRefreshActivity;
import com.fheebiy.activity.lite.LiteHttpActivity;
import com.fheebiy.activity.listview.SwipeRefreshLayoutActivity;
import com.fheebiy.activity.main.Main2Activity;
import com.fheebiy.activity.main.Main3Activity;
import com.fheebiy.activity.main.Main4Activity;
import com.fheebiy.activity.main.MainActivity;
import com.fheebiy.activity.listview.SlideToDelLvActivity;
import com.fheebiy.activity.other.ThemeStyleActivity;
import com.fheebiy.activity.overscroll.ImageScaleActivity;
import com.fheebiy.activity.overscroll.ScrollTestActivity;
import com.fheebiy.activity.pulltorefresh.PullMainActivity;
import com.fheebiy.activity.service.RemoteServiceActivity;
import com.fheebiy.activity.service.ServiceMainActivity;
import com.fheebiy.activity.test.PullToRefreshTestActivity;
import com.fheebiy.activity.vp.VpActivity;
import com.fheebiy.activity.vp.VpAnimationActivity;
import com.fheebiy.activity.vp.VpComplexActivity;
import com.fheebiy.activity.vp.VpStripActivity;
import com.fheebiy.activity.webview.WebViewActivity;
import com.fheebiy.adapter.IndexAdapter;
import com.fheebiy.model.UIModel;

import java.util.ArrayList;
import java.util.List;


/**
 * created by bob zhou 2014.08.01
 * <p/>
 * 用于测试fragment
 */
public class IndexActivity extends FragmentActivity {

    private AQuery aq;

    private ListView listView;

    private IndexAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        aq = new AQuery(this);
        listView = aq.id(R.id.listView).getListView();
        adapter = new IndexAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(getUIList());
        bindListener();
    }


    public List<UIModel> getUIList() {
        List<UIModel> list = new ArrayList<UIModel>();
        list.add(new UIModel("to 图片压缩 activity", BitmapCompressActivity.class));
        list.add(new UIModel("to 图片拖动缩放 activity", PullToScaleImgActivity.class));
        list.add(new UIModel("to broadcast activity", BroadcastActivity.class));
        list.add(new UIModel("to handler activity", HandlerActivity.class));
        list.add(new UIModel("to dir and path activity", DirPathActivity.class));
        list.add(new UIModel("to webview activity", WebViewActivity.class));
        list.add(new UIModel("to layout basic activity", ThemeStyleActivity.class));
        list.add(new UIModel("to async task activity", AsyncTaskActivity.class));
        list.add(new UIModel("to combination view", CombinationViewActivity.class));
        list.add(new UIModel("to self draw view", SelfDrawViewActivity.class));
        list.add(new UIModel("to property animation", PropertyAnimationActivity.class));
        list.add(new UIModel("to tween animation", TweenAnimationActivity.class));
        list.add(new UIModel("to frame animation", FrameAnimationActivity.class));
        list.add(new UIModel("to event dispatcher", EventDispatcherActivity.class));
        list.add(new UIModel("to async http", AsyncActivity.class));
        list.add(new UIModel("to remote service", RemoteServiceActivity.class));
        list.add(new UIModel("to service", ServiceMainActivity.class));
        list.add(new UIModel("to aquery", AQueryActivity.class));
        list.add(new UIModel("to lite http", LiteHttpActivity.class));
        list.add(new UIModel("to OverScrollView", ScrollTestActivity.class));
        list.add(new UIModel("to img scale test", ImageScaleActivity.class));
        list.add(new UIModel("to pull to refresh list view by guolin", PullToRefreshActivity.class));
        list.add(new UIModel("to pull to refresh list view by myself", PullToRefreshTestActivity.class));
        list.add(new UIModel("ListView 不能自动扩展测试", LoadMoreListViewActivity.class));
        list.add(new UIModel("to SwipeRefreshLayout", SwipeRefreshLayoutActivity.class));
        list.add(new UIModel("to window", PopupWindowActivity.class));
        list.add(new UIModel("to del list view", SlideToDelLvActivity.class));
        list.add(new UIModel("to communicate3", Communicate3Activity.class));
        list.add(new UIModel("to communicate2", Communicate2Activity.class));
        list.add(new UIModel("to communicate1", CommunicateActivity.class));
        list.add(new UIModel("to main4", Main4Activity.class));
        list.add(new UIModel("to main3", Main3Activity.class));
        list.add(new UIModel("to main2", Main2Activity.class));
        list.add(new UIModel("to main1", MainActivity.class));
        list.add(new UIModel("to vp strip", VpStripActivity.class));
        list.add(new UIModel("to animation vp", VpAnimationActivity.class));
        list.add(new UIModel("to complex vp", VpComplexActivity.class));
        list.add(new UIModel("to simple vp", VpActivity.class));
        list.add(new UIModel("to pull main", PullMainActivity.class));

        return list;
    }


    public void bindListener() {
        aq.id(listView).itemClicked(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UIModel model = (UIModel) parent.getAdapter().getItem(position);
                Intent intent = new Intent(IndexActivity.this,model.getCls());
                startActivity(intent);
            }
        });
    }


}
