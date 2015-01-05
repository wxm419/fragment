package com.fheebiy.http.aquery;

import android.app.Application;
import com.androidquery.AQuery;
import com.fheebiy.http.urlconfig.UrlConfig;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 14-12-29.
 */
public class Http {

    private Object handler;

    private AQuery aq;

    public Http(Object handler) {
        this.handler = handler;
    }




    public void getHomePageNews(String sy_c){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sy_c", sy_c);
        doGet(UrlConfig.HOMEPAGE_NEWS,"getHomePageNewsCallback",params,false);
    }



    private void doGet(String url, String method, Map<String, Object> params, boolean refresh) {
    /*    return doGet(http, url, method, params, refresh, null);*/
        AQuery aq = new AQuery(new Application());

        aq.ajax(url, params, JSONObject.class, handler, method);

    }


}
