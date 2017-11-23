package com.tsy.base.api.tool

import android.support.annotation.NonNull
import com.tsy.base.api.error.exception.ResponseException
import com.tsy.base.api.type.HttpResult
import io.reactivex.functions.Function

/**
 * Created by jay on 17/8/1.
 */

class ServerResponseFunc<T> : Function<HttpResult<T>, T> {

    @Throws(Exception::class)
    override fun apply(@NonNull httpResult: HttpResult<T>): T {
        if (httpResult.errCode == ResponseException.STATUS_CODE_SUCCESS) {
            return httpResult.data
        } else {
            throw ResponseException(httpResult.errMessage, httpResult.errCode)
        }
    }
}
