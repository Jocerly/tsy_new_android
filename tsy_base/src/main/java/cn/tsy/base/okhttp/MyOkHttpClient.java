package cn.tsy.base.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 请求的发送，请求参数的配置，https支持
 * Created by Jocerly on 2018/4/10.
 */
public class MyOkHttpClient {
    private static final int TIME_OUT = 30; //超时参数
    private static OkHttpClient mOkHttpClient;

    //为我们的Client配置参数，使用静态语句块来配置
    static {
        //创建我们Client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                //为构建者填充超时时间
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                //允许重定向
                .followRedirects(true)
                //添加https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory());
//        .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的HTTP以及Https请求
     * @param request
     * @param commonCallback
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonCallback commonCallback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonCallback);
        return call;
    }

    /**
     * 发送具体的HTTP以及Https请求
     * @param request
     * @param jsonStringCallback
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonStringCallback jsonStringCallback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(jsonStringCallback);
        return call;
    }

    /**
     * GET请求
     * @param request
     * @param handle
     * @return
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    /**
     * POST请求
     * @param request
     * @param handle
     * @return
     */
    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
