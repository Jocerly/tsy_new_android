package com.tsy.base.api.type

/**
 * Created by jay on 17/7/31.
 */

data class HttpResult<T>(var errCode: Int, var errMessage: String, var data: T)
