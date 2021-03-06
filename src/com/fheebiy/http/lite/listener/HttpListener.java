package com.fheebiy.http.lite.listener;

import com.fheebiy.http.lite.exception.HttpException;
import com.fheebiy.http.lite.request.Request;
import com.fheebiy.http.lite.response.Response;

/**
 * @author MaTianyu
 * @date 2014-11-06
 */
public abstract class HttpListener implements HttpExecuteListener, HttpConnectListener,
        HttpReadListener, HttpReadingListener {

    @Override
    public abstract void onStart(Request req) throws HttpException;

    @Override
    public abstract void onEnd(Response res);

    @Override
    public void onRetry(Request req, int max, int now) {}

    @Override
    public void onRedirect(Request req) {}

    @Override
    public void onPreConnect(Request req) { }

    @Override
    public void onAfterConnect(Request req) { }

    @Override
    public void onPreRead(Request req) { }

    @Override
    public void onAfterRead(Request req) { }

    @Override
    public void onReading(Request req, long total, int len) {}
}
