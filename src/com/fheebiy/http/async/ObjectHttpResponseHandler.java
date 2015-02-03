/*
package com.fheebiy.activity.async;

import android.os.Message;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpStatus;

import java.util.List;

*/
/**
 * Created with IntelliJ IDEA.
 * User: justinhou
 * Date: 13-10-16
 * Time: 下午5:48
 *//*

public abstract class ObjectHttpResponseHandler<T> extends AsyncHttpResponseHandler {

    private Class<T> clazz;

    public ObjectHttpResponseHandler(Class<T> clazz){
        this.clazz = clazz;
    }


    protected static final int SUCCESS_JSON_MESSAGE = 100;

    //
    // Callbacks to be overridden, typically anonymously
    //

    */
/**
     * Fired when a request returns successfully and contains a json object
     * at the base of the response string. Override to handle in your
     * own code.
     * @param response the parsed json object found in the server response (if any)
     *//*

    public abstract void onSuccess(T response);


    */
/**
     * Fired when a request returns successfully and contains a json array
     * at the base of the response string. Override to handle in your
     * own code.
     * @param response the parsed json array found in the server response (if any)
     *//*

    public abstract void onSuccess(List<T> response);

    */
/**
     * Fired when a request returns successfully and contains a json object
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param headers the headers of the HTTP response
     * @param response the parsed json object found in the server response (if any)
     *//*

    public void onSuccess(int statusCode, Header[] headers,  T response) {
        onSuccess(statusCode,response);
    }

    */
/**
     * Fired when a request returns successfully and contains a json object
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param response the parsed json object found in the server response (if any)
     *//*

    public void onSuccess(int statusCode, T response) {
        onSuccess(response);
    }

    */
/**
     * Fired when a request returns successfully and contains a json array
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param headers the headers of the HTTP response
     * @param response the parsed json array found in the server response (if any)
     *//*

    public void onSuccess(int statusCode, Header[] headers, List<T> response) {
        onSuccess(statusCode, response);
    }

    */
/**
     * Fired when a request returns successfully and contains a json array
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param response the parsed json array found in the server response (if any)
     *//*

    public void onSuccess(int statusCode,  List<T> response) {
        onSuccess(response);
    }

    public abstract void onFailure(Throwable e, String msg);
    


    //
    // Pre-processing of messages (executes in background threadpool thread)
    //

    @Override
    protected void sendSuccessMessage(int statusCode, Header[] headers, String responseBody) {
        if (statusCode != HttpStatus.SC_NO_CONTENT){
            try {
                Object jsonResponse = parseResponse(responseBody);
                sendMessage(obtainMessage(SUCCESS_JSON_MESSAGE, new Object[]{statusCode, headers, jsonResponse}));
            }  catch (Exception e) {
                sendFailureMessage(e, responseBody);
            }
        } else {
            sendMessage(obtainMessage(SUCCESS_JSON_MESSAGE, new Object[]{statusCode, new JSONObject()}));
        }
    }


    //
    // Pre-processing of messages (in original calling thread, typically the UI thread)
    //

    @Override
    protected void handleMessage(Message msg) {
        switch(msg.what){
            case SUCCESS_JSON_MESSAGE:
                Object[] response = (Object[]) msg.obj;
                handleSuccessJsonMessage(((Integer) response[0]).intValue(),(Header[]) response[1] ,(JSONObject)response[2]);
                break;
            default:
                super.handleMessage(msg);
        }
    }

    protected void  handleSuccessJsonMessage(int statusCode,Header[] headers, JSONObject jsonResponse) {
        if(jsonResponse!=null && jsonResponse.containsKey("body")) {
            if(jsonResponse.getString("body").startsWith("{")){
                T body = jsonResponse.getObject("body", clazz);
                onSuccess(statusCode, headers, body);
                return;
            } else if(jsonResponse.getString("body").startsWith("[")){
                onSuccess(statusCode, headers, JSON.parseArray(jsonResponse.getJSONArray("body").toJSONString(), clazz));
                return;
            }
        }
        onFailure(new JSONException("Unexpected type " + jsonResponse.getClass().getName()), "Unexpected type " + jsonResponse.getClass().getName());
    }

    protected JSONObject parseResponse(String responseBody) {
        JSONObject result = null;
        //trim the string to prevent start with blank, and test if the string is valid JSON, because the parser don't do this :(. If Json is not valid this will return null
        responseBody = responseBody.trim();
        if(responseBody.startsWith("{") ) {
            result = JSON.parseObject(responseBody);
        }
        return result;
    }

    @Override
    protected void handleFailureMessage(Throwable e, String responseBody) {
        Log.d("ent_net","callback:"+e.getMessage());
        try {
            if (responseBody != null) {
                Object jsonResponse = parseResponse(responseBody);
                if(jsonResponse instanceof JSONObject) {
                    onFailure(e, responseBody);
                } else if(jsonResponse instanceof JSONArray) {
                    onFailure(e, responseBody);
                } else {
                    onFailure(e, responseBody);
                }
            }else {
                onFailure(e, null);
            }
        }catch(Exception ex) {
            onFailure(e, responseBody);
        }
    }
}
*/
