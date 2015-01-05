package com.fheebiy.http.lite.response.handler;

import com.fheebiy.http.lite.exception.HttpException;
import com.fheebiy.http.lite.response.Response;

/**
 * Handle Response on UI Thread
 * 
 * @author MaTianyu
 * 2014-2-26下午9:02:09
 */
public abstract class HttpModelHandler<Model> {
	protected abstract void onSuccess(Model data, Response res);

	protected abstract void onFailure(HttpException e, Response res);

	public HttpModelHandler<Model> handleModelData(Model data, Response res) {
		if (res != null) {
			HttpException he = res.getException();
			if (he != null) {
				onFailure(he, res);
			} else {
				onSuccess(data, res);
			}
		}
		return this;
	}
}
