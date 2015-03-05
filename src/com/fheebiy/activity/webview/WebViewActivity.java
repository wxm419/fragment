package com.fheebiy.activity.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.*;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.view.CustomWebView;
import com.fheebiy.view.TitleView;

/**
 * Created by Lenovo on 15-3-4.
 *
 */
public class WebViewActivity extends Activity {

    private CustomWebView mWebView;

    private TitleView titleView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        init();
    }


    private void init() {
        titleView = (TitleView) findViewById(R.id.title);
        mWebView = (CustomWebView) findViewById(R.id.webview);

        /**
         * WebViewClient与WebChromeClient的区别
         * WebViewClient主要帮助WebView处理各种通知、请求事件的
         * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度
         */

        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleView.setTitleText(title);
            }

          /*  @Override
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("test", cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
                return true;
            }

            @Override

            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //Toast.makeText(WebViewActivity.this, message, Toast.LENGTH_SHORT).show();
                return true;
            }*/

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d(CommonUtil.LOG_TAG, "onProgressChanged execute, newProgress=" + newProgress);
            }
        };

        WebViewClient wvc = new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //点击超链接时，重写此方法返回true表明点击网页里面的链接在当前的webview里跳转，不跳到浏览器那边。
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {//处理按键事件
                return super.shouldOverrideKeyEvent(view, event);
            }


            @Override
            public void onPageFinished(WebView view, String url) {  //加载结束
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {   //加载开始
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onLoadResource(WebView view, String url) {  //加载资源,加载本地资源不会调用
                super.onLoadResource(view, url);
                Log.d(CommonUtil.LOG_TAG, "onLoadResource execute, url=" + url);
            }
        };


        mWebView.setWebChromeClient(webChromeClient);
        mWebView.setWebViewClient(wvc);
        mWebView.loadUrl("file:///android_asset/sample.html");
    }
}