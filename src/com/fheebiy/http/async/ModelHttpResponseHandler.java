package com.fheebiy.http.async;

import android.os.Looper;
import com.alibaba.fastjson.JSON;
import com.fheebiy.exception.ServerResponseException;
import com.fheebiy.model.NewsDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by bob zhou on 15-2-2.
 *
 * 直接结果转化为对象的HttpResponseHandler
 *
 * 猜测可能将返回结果中head字段，改为header字段，应该就能正确判断是回调onSuccess， 还是onFailure
 *
 */
public abstract class ModelHttpResponseHandler<T> extends JsonHttpResponseHandler {

    public static final int SUCCESS_STATUS = 200;


    private Class<T> clazz;

    protected ModelHttpResponseHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void onSuccess(int statusCode, T response);

    public abstract void onSuccess(int statusCode, List<T> response);

    public abstract void onFailure(int statusCode, Throwable e, String msg);


    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        try {
            if (response.has("head")) {
                JSONObject head = response.getJSONObject("head");
                int status = head.getInt("status");
                if (status != SUCCESS_STATUS) {
                    String msg = response.getString("body");
                    ServerResponseException exception = new ServerResponseException(status,msg);
                    onFailure(status, exception, msg);
                    throw exception;
                }
            }

            if (response.has("body")) {
                String jsonStr = response.get("body").toString().trim();
                if (jsonStr.startsWith("{")) {
                    T body = JSON.parseObject(jsonStr, clazz);
                    onSuccess(statusCode, body);
                    return;

                }

                if (jsonStr.startsWith("[")) {
                    onSuccess(statusCode, JSON.parseArray(jsonStr, clazz));
                    return;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(300, e, e.getMessage());
        } catch (ServerResponseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        super.onSuccess(statusCode, headers, response);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        super.onSuccess(statusCode, headers, responseString);
    }


    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        onFailure(statusCode, throwable, responseString);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        onFailure(statusCode, throwable, errorResponse.toString());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        onFailure(statusCode, throwable, errorResponse.toString());
    }


}
