package com.tsy.base.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tsy.base.BuildConfig
import com.tsy.base.api.interceptor.BasicParamsInterceptor
import com.tsy.base.api.interceptor.RequestSignatureInterceptor
import com.tsy.base.constants.BasicParams
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jay on 2017/11/15.
 */
object TsyClient {

    private fun getGson(): Gson {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
        return gson
    }

    private fun getLogInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun getBasicParamsInterceptor(): Interceptor {

        return BasicParamsInterceptor.Build()
                .addHeaderParam("user-agent", "TaoshouyouApp/android_" + BasicParams.versionName)
                .addParam("mt", "Android")
                .addParam("versionCode", BasicParams.versionCode)
                .addParam("channel", BasicParams.channelName)
                .addParam("mk", BasicParams.deviceId)
                .addParam("AppToken", BasicParams.appToken)
                .build()
    }

    val api: TsyApi by lazy {
        createRetrofitService(TsyApi::class.java)
    }

    fun <T> createRetrofitService(clazz: Class<T>): T {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(getLogInterceptor())
        }

        clientBuilder.addInterceptor(getBasicParamsInterceptor())
        clientBuilder.addInterceptor(RequestSignatureInterceptor())

        val client = clientBuilder
                .retryOnConnectionFailure(true)
                .build()

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(HttpUrl.parse(TsyApi.API_HOST)!!)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)

        val retrofit = retrofitBuilder.build()
        return retrofit.create(clazz)
    }

}
