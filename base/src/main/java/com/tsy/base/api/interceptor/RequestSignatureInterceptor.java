package com.tsy.base.api.interceptor;

import android.support.v4.util.ArrayMap;

import com.tsy.base.tool.RequestSignTool;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jay on 2017/11/2.
 */

public class RequestSignatureInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        ArrayMap<String, String> requestParams = new ArrayMap<>();
        StringBuilder values = new StringBuilder();

        if (original.method().equals("GET")) {
            HttpUrl.Builder urlBuilder = original.url().newBuilder();
            Set<String> paramsKey = original.url().queryParameterNames();

            boolean hasVerifyCode = false;

            if (paramsKey.size() > 0) {
                for (int i = 0; i < paramsKey.size(); i++) {
                    if (original.url().queryParameterName(i).equals("verifyCode")) {
                        hasVerifyCode = true;
                    }
                    requestParams.put(original.url().queryParameterName(i), original.url().queryParameterValue(i));
                    values.append(original.url().queryParameterValue(i));
                    urlBuilder.addEncodedQueryParameter(original.url().queryParameterName(i), original.url().queryParameterValue(i));
                }
                if (!hasVerifyCode) {
                    urlBuilder.addEncodedQueryParameter("verifyCode", RequestSignTool.getVerifyCode(values.toString()));
                }
                requestBuilder.url(urlBuilder.build());
            }
        }

        if (original.method().equals("POST")) {
            FormBody.Builder formBodyBuild = new FormBody.Builder();
            FormBody originalBody = (FormBody) original.body();

            if (originalBody.size() > 0) {
                for (int i = 0; i < originalBody.size(); i++) {
                    formBodyBuild.add(originalBody.encodedName(i), originalBody.encodedValue(i));
                    values.append(originalBody.value(i));
                }
                formBodyBuild.addEncoded("verifyCode", RequestSignTool.getVerifyCode(values.toString()));
                requestBuilder.post(formBodyBuild.build());
            }
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
