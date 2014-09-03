package com.fheebiy.http;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 14-9-3.
 */
public class Http {

    private static final String tag = "http";

    public static String postData(String urlStr, List<NameValuePair> params){
        HttpClient httpClient = new DefaultHttpClient();;
        HttpGet post = new HttpGet(urlStr);
        try {
           /* post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));*/
            // 请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
            // 读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
            HttpResponse resp = httpClient.execute(post);
            String data = EntityUtils.toString(resp.getEntity());
           // JSONObject obj = new JSONObject(data);
            return data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(tag, e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e(tag, e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(tag, e.toString());
        } /*catch (JSONException e) {
            e.printStackTrace();
            Log.e(tag, e.toString());
        }*/
        return null;
    }


}
