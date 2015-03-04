package com.fheebiy.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.fheebiy.model.JsModel;
import com.fheebiy.util.CommonUtil;
import com.google.gson.Gson;

/**
 * Created by Lenovo on 15-3-4.
 */
public class CustomWebView extends WebView{

    private Context context;

    public CustomWebView(Context context) {
        super(context);
        this.context = context;
        initializeOptions();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeOptions();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeOptions();
    }

    @SuppressLint("NewApi")
    public void initializeOptions() {
        WebSettings settings = getSettings();
        // User settings
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);     //自动加载图片

        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        //设置报存表单数据和密码
        settings.setSaveFormData(true);
        settings.setSavePassword(true);

        //缩放等级中等
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
       /* String str = "";
        try {
            str = settings.getUserAgentString() + (ConfigApi.isSouyue() ? "-souyue4.0" : "-superapp-" + URLEncoder.encode(CommonStringsApi.APP_NAME, "utf-8") + "-souyue4.0");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        settings.setUserAgentString(str);
*/
        //不支持缩放和不现实缩放控件
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        // Technical settings
        settings.setSupportMultipleWindows(true);
        setLongClickable(true);
        setScrollbarFadingEnabled(true);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        setDrawingCacheEnabled(true);

        CookieManager.getInstance().setAcceptCookie(true);
//      CookieManager.setAcceptFileSchemeCookies(true);

        /** Add By YinGuanPing webview Cache */

        // LOAD_DEFAULT
        // 默认加载方式，使用这种方式，会实现快速前进后退，在同一个标签打开几个网页后，关闭网络时，可以通过前进后退来切换已经访问过的数据，同时新建网页需要网络
        // - LOAD_NO_CACHE
        // - LOAD_NORMAL
        // * 这个方式跟LOAD_NO_CACHE方式相同，不使用缓存，如果没有网络，即使以前打开过此网页也不会使用以前的网页。
        // - LOAD_CACHE_ELSE_NETWORK
        // *
        // 这个方式不论如何都会从缓存中加载，除非缓存中的网页过期，出现的问题就是打开动态网页时，不能时时更新，会出现上次打开过的状态，除非清除缓存。
        // - LOAD_CACHE_ONLY
        // * 这个方式只是会使用缓存中的数据，不会使用网络。

        if (CommonUtil.isNetworkAvailable(context))
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        else
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 设置 缓存模式

        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启 database storage API 功能
//      settings.setDatabaseEnabled(true);
//      String cacheDirPath = context.getFilesDir().getAbsolutePath()
//              + APP_CACAHE_DIRNAME;
//      Log.i("CustomWebView", "cacheDirPath=" + cacheDirPath);

        // 应用可以有数据库
        settings.setDatabaseEnabled(true);
        String dbPath = context.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);
        // 应用可以有缓存
        settings.setAppCacheEnabled(true);
        String appCaceDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCaceDir);
//      // 设置数据库缓存路径
//      settings.setDatabasePath(cacheDirPath);
//        // 开启 Application Caches 功能
//        settings.setAppCacheEnabled(true);
//        // 设置 Application Caches 缓存目录
//        settings.setAppCachePath(cacheDirPath);

        // if (Build.VERSION.SDK_INT <= 7) {
        // settings.setPluginsEnabled(true);
        // } else {
        // settings.setPluginState(PluginState.ON);
        // }

        //JS代码中，window后的命名空间必须为和第二个参数"JsInterface"保持一致
        this.addJavascriptInterface(new JsInterface(), "JsInterface");
    }




    class JsInterface {

        //接口参数必须保持一致，包括类型
        @JavascriptInterface
        public void closeCurPage(){
            ((Activity)context).finish();
        }

        @JavascriptInterface
        public void toDataTest(String json){
            JsModel jsc = new Gson().fromJson(json, JsModel.class);
            CommonUtil.toast(context,jsc.getCategory());
        }


    }





}
