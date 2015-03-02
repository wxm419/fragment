package com.fheebiy.http.async;

import com.fheebiy.exception.ServerResponseException;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo on 15-2-3.
 *
 * 感觉，此种方式有点别扭，但是始终还是实现onSuccess的正确回调
 *
 * 此依赖于SUCCESS_STATUS = 200，
 * 猜测可能将返回结果中head字段，改为header字段，应该就能正确判断是回调onSuccess， 还是onFailure
 *
 */
public abstract class MJsonHttpResponseHandler extends JsonHttpResponseHandler {


    public static final int SUCCESS_STATUS = 200;

    public abstract void onSuccess(int statusCode, JSONObject response);


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
                    ServerResponseException exception = new ServerResponseException(status, msg);
                    onFailure(status, exception, msg);
                    throw exception;
                }
            }

            if (response.has("body")) {
                onSuccess(statusCode, response);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ServerResponseException e) {
            e.printStackTrace();
        }


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

