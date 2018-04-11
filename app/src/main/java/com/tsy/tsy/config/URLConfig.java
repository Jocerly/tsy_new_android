package com.tsy.tsy.config;


import com.tsy.tsy.BuildConfig;

/**
 * 项目配置类
 *
 * @author Jocerly
 */
public class URLConfig {

    /**
     * 是否在调试阶段
     */
    public final static boolean isDebug = BuildConfig.DEBUG;

    /**
     * 网络配置
     *
     * @author Jocerly
     */
    public static class NetConfig {
        /**
         * api_host
         */
        public static String URL_HOST = BuildConfig.URL_HOST;
        /**
         * 注册登录host
         * t0-passport.taoshouyou.com:8083
         */
        public static final String LOGIN_URL_HOST = BuildConfig.URL_HOST;

        /**
         * 一键支付host
         */
        public static final String PAY_URL_HOST = BuildConfig.PAY_URL_HOST;

        /**
         * 淘手游图片host
         */
        public static final String URL_IMG_HOST = BuildConfig.URL_IMG_HOST;

        /**
         * H5 host
         */
        public static final String H5_HOST = BuildConfig.H5_HOST;
        /**
         * 激光推送标签定义
         */
        public static String OnTag = BuildConfig.OnTag;
    }


    /*** --- 接口API ----*/
    public final class NetConfigt0 {
        /**
         * api_host
         */
        public static final String URL_HOST = "http://cdt0-openapi.taoshouyou.com/api/";

        /**
         * 注册登录host
         * t0-passport.taoshouyou.com:8083
         */
        public static final String LOGIN_URL_HOST = "http://cdt0-passport.taoshouyou.com/api/";

        /**
         * 一键支付host
         */
        public static final String PAY_URL_HOST = "http://cdt0-pay.taoshouyou.com/";

        /**
         * 淘手游图片host
         */
        public static final String URL_IMG_HOST = "http://img-test.taoshouyou.com";

        /**
         * H5 host
         */
        public static final String H5_HOST = "http://cdt0-webapp.taoshouyou.com/";
        public static final String OnTag = "tsy_test";
    }

    public final class NetConfigOnline {
        /**
         * api_host
         */
        public static final String URL_HOST = "http://app.taoshouyou.com/api/";

        /**
         * 注册登录host
         */
        public static final String LOGIN_URL_HOST = "http://passport.taoshouyou.com/api/";

        /**
         * 一键支付host
         */
        public static final String PAY_URL_HOST = "https://pay.taoshouyou.com/";

        /**
         * 淘手游图片host
         */
        public static final String URL_IMG_HOST = "http://img.taoshouyou.com";

        /**
         * H5 host
         */
        public static final String H5_HOST = "https://m.taoshouyou.com/";

        public static final String OnTag = "tsy_online";
    }
}
