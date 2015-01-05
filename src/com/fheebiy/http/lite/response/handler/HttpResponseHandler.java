package com.fheebiy.http.lite.response.handler;

import com.fheebiy.http.lite.data.HttpStatus;
import com.fheebiy.http.lite.data.NameValuePair;
import com.fheebiy.http.lite.exception.HttpException;
import com.fheebiy.http.lite.response.Response;

/**
 * Handle Response on UI Thread
 * 
 * @author MaTianyu
 * 2014-2-26下午9:02:09
 */
public abstract class HttpResponseHandler {
	protected abstract void onSuccess(Response res, HttpStatus status, NameValuePair[] headers);

	protected abstract void onFailure(Response res, HttpException e);

	public HttpResponseHandler handleResponse(Response res) {
		if (res != null) {
			HttpException e = res.getException();
			if (e == null) {
				onSuccess(res, res.getHttpStatus(), res.getHeaders());
			} else {
				onFailure(res, e);
			}
		}
		return this;
	}
}
