package com.tsy.base.api

import com.tsy.base.api.type.HttpResult
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by jay on 2017/11/15.
 */
interface TsyApi {
    companion object {
        val API_HOST = "http://cdt1-openapi.taoshouyou.com/api/"
        val PASSPORT_HOST = "http://cdt1-passport.taoshouyou.com/api/"
        val PAY_HOST = "http://cdt1-pay.taoshouyou.com/"
        val IMG_HOST = "http://img-test.taoshouyou.com"
    }

    // 神策统计－点击
    @FormUrlEncoded
    @POST("statistics/cm-click")
    fun cmClick(@Field("clk_page_uri") pageUri: String, @Field("clk_target_url") targetUrl: String, @Field("clk_item_index") itemIndex: String,
                @Field("clk_name_en") nameEn: String, @Field("opt_queryword") queryword: String) : Flowable<HttpResult<Any>>

    // 神策统计－页面访问
    @FormUrlEncoded
    @POST("statistics/page-view")
    fun pageView(@Field("viw_page_uri") pageUri: String, @Field("viw_page_name_zh") pageNameZh: String) : Flowable<HttpResult<Any>>

    // App更新
    @GET("app-version/checkversion")
    fun  appUpdate()
    
}