package com.fheebiy.http.async;

import com.fheebiy.http.urlconfig.UrlConfig;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by bob zhou on 15-2-2.
 */
public class HttpHelper {


    public static void getNews(String sy_c, AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.add("sy_c", sy_c);
        AppRestClient.get(UrlConfig.HOMEPAGE_NEWS,params,handler);
    }

    public static void getCircleMenu(String sy_c, AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.add("sy_c", sy_c);
        AppRestClient.get(UrlConfig.CIRCLE_MENU,params,handler);
    }

}
