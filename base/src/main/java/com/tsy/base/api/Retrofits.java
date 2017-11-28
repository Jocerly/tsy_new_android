package com.tsy.base.api;

/**
 * Created by jay on 17/7/31.
 */

public class Retrofits {

    private static final Api SINGLETON = new TsyRetrofit().getService();

    public static Api api() {
        return SINGLETON;
    }

}
