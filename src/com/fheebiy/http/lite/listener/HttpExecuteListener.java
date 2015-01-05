package com.fheebiy.http.lite.listener;

import com.fheebiy.http.lite.exception.HttpException;
import com.fheebiy.http.lite.request.Request;
import com.fheebiy.http.lite.response.Response;

/**
 * @author MaTianyu
 * @date 2014-11-06
 */
public interface HttpExecuteListener {
    public void onStart(Request req) throws HttpException;

    public void onEnd(Response res);

    public void onRetry(Request req, int max, int now);

    public void onRedirect(Request req);
}
