package com.tsy.base.api

import com.google.gson.GsonBuilder
import com.tsy.base.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jay on 2017/11/15.
 */
object TsyClient {

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

    fun retrofit(): TsyApi {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        val client = clientBuilder
                .retryOnConnectionFailure(true)
                .build()

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(HttpUrl.parse(TsyApi.API_HOST))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)

        val retrofit = retrofitBuilder.build()
        return retrofit.create(TsyApi::class.java)
    }
}
