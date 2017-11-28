package com.tsy.base.api.type;

/**
 * Created by jay on 17/7/31.
 */

public class HttpResult<T> {

    public int errCode;

    public String errMessage;

    public T data;

}
