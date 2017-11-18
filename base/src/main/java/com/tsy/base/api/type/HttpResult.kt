package com.tsy.base.api.type

/**
 * Created by jay on 17/7/31.
 */

class HttpResult<T> {

    var errCode: Int = 0

    var errMessage: String? = null

    var data: T? = null

}
