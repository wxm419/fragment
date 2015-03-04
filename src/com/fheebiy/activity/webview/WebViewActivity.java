package com.fheebiy.activity.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.view.CustomWebView;
import com.fheebiy.view.TitleView;

/**
 * Created by Lenovo on 15-3-4.
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
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleView.setTitleText(title);
            }

         /*   @Override
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("test", cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
                return true;
            }

            @Override

            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(WebViewActivity.this, message, Toast.LENGTH_SHORT).show();
                return true;
            }*/

        };

    mWebView.setWebChromeClient(webChromeClient);
    mWebView.loadUrl("file:///android_asset/sample.html");
}
}