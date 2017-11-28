package com.tsy.base.api.interceptor;

import android.support.v4.util.ArrayMap;

import com.tsy.base.constants.BasicParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jay on 17/7/31.
 */

public class BasicParamsInterceptor implements Interceptor {

    private ArrayMap<String, String> paramsMap = new ArrayMap<>();
    private ArrayMap<String, String> headerParamsMap = new ArrayMap<>();
    private List<String> headerLinesList = new ArrayList<>();

    private BasicParams mBasicParams;

    public BasicParamsInterceptor(BasicParams basicParams) {
        this.mBasicParams = basicParams;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();

        headerParamsMap.put("user-agent", "TaoshouyouApp/android_" + mBasicParams.getVersionName());
        paramsMap.put("mt", "Android");
        paramsMap.put("versionCode", mBasicParams.getVersionCode());
        paramsMap.put("channel", mBasicParams.getChannelName());
        paramsMap.put("AppToken", mBasicParams.getAppToken());
        paramsMap.put("mk", mBasicParams.getDeviceId());

        //Header params
        Headers.Builder headerBuilder = original.headers().newBuilder();
        if (!headerParamsMap.isEmpty()) {
            for (Object o : headerParamsMap.entrySet()) {
                ArrayMap.Entry<String, String> entry = (ArrayMap.Entry) o;
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
            requestBuilder.headers(headerBuilder.build());
        }

        if (!headerLinesList.isEmpty()) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
            requestBuilder.headers(headerBuilder.build());
        }

        //add Params by requestMethod(Get or Post)
        if (original.method().equals("GET") && !paramsMap.isEmpty()) {
            Iterator iterator = paramsMap.entrySet().iterator();
            HttpUrl.Builder urlBuilder = original.url().newBuilder();
            while (iterator.hasNext()) {
                ArrayMap.Entry<String, String> entry = (ArrayMap.Entry) iterator.next();
                urlBuilder.addEncodedQueryParameter(entry.getKey(), entry.getValue());
            }
            requestBuilder.url(urlBuilder.build());
        }

        if (original.method().equals("POST") && !paramsMap.isEmpty()) {
            FormBody.Builder formBodyBuild = new FormBody.Builder();
            FormBody originalBody = (FormBody) original.body();
            for (Object o : paramsMap.entrySet()) {
                ArrayMap.Entry<String, String> entry = (ArrayMap.Entry) o;
                formBodyBuild.addEncoded(entry.getKey(), entry.getValue());
            }

            for (int i = 0; i < originalBody.size(); i++) {
                formBodyBuild.add(originalBody.encodedName(i), originalBody.encodedValue(i));
            }

            requestBuilder.post(formBodyBuild.build());
        }

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }

}
