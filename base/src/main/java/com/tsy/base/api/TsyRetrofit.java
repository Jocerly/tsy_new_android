package com.tsy.base.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsy.base.api.interceptor.BasicParamsInterceptor;
import com.tsy.base.api.interceptor.RequestSignatureInterceptor;
import com.tsy.base.constants.BasicParams;
import com.tsy.base.tool.VersionTool;

import java.util.Base64;

import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jay on 2017/11/23.
 */

public final class TsyRetrofit {

    final Api service;

    private static boolean debug = true;

    private static Interceptor basicInterceptor;

    private static final Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();


    TsyRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (debug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.addInterceptor(new RequestSignatureInterceptor());

        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor(BasicParams.getInstance());

        clientBuilder.addInterceptor(basicParamsInterceptor);

        OkHttpClient client = clientBuilder
                .retryOnConnectionFailure(true)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(Api.API_HOST))
                .addConverterFactory(GsonConverterFactory.create(GSON))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client);

        Retrofit retrofit = builder.build();
        service = retrofit.create(Api.class);

    }

    public static void setDebug(boolean debug) {
        TsyRetrofit.debug = debug;
    }

    public static void setBasicInterceptor(Interceptor interceptor) {
        TsyRetrofit.basicInterceptor = interceptor;
    }

    public static Interceptor getBasicInterceptor() {
        return basicInterceptor;
    }

    Api getService() {
        return service;
    }

}
