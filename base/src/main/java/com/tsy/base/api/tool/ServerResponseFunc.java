package com.tsy.base.api.tool;

import android.support.annotation.NonNull;

import com.tsy.base.api.error.exception.NetworkConnectionException;
import com.tsy.base.api.error.exception.ResponseException;
import com.tsy.base.api.type.HttpResult;

import io.reactivex.functions.Function;

/**
 * Created by jay on 17/8/1.
 */

public class ServerResponseFunc<T> implements Function<HttpResult<T>, T> {

    private static final String TAG = "ServerResponseFunc";

    @Override
    public T apply(@NonNull HttpResult<T> httpResult) throws Exception {

        if (httpResult == null) {
            throw new NetworkConnectionException();
        } else if (httpResult.errCode == ResponseException.STATUS_CODE_SUCCESS) {
            return httpResult.data;
        } else {
            throw new ResponseException(httpResult);
        }
    }
}
