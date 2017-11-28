package com.tsy.base.api;


import com.tsy.base.api.type.AppUpdate;
import com.tsy.base.api.type.HttpResult;
import com.tsy.base.api.type.user.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jay on 17/7/31.
 */

public interface Api {

    String API_HOST = "http://cdt1-openapi.taoshouyou.com/api/";
    String PASSPORT_HOST = "http://cdt1-passport.taoshouyou.com/api/";
    String PAY_HOST = "http://cdt1-pay.taoshouyou.com/";
    String IMG_HOST = "http://img-test.taoshouyou.com";


    //神策统计－点击
    @FormUrlEncoded
    @POST("statistics/cm-click")
    Flowable<HttpResult<Object>> cmClick(@Field("clk_page_uri") String pageUri, @Field("clk_target_url") String targetUrl, @Field("clk_item_index") String itemIndex,
                                         @Field("clk_name_en") String nameEn, @Field("opt_queryword") String opt_queryword);

    //神策统计－页面访问
    @FormUrlEncoded
    @POST("statistics/page-view")
    Flowable<HttpResult<Object>> pageView(@Field("viw_page_uri") String pageUri, @Field("viw_page_name_zh") String pageNameZh);


    @FormUrlEncoded
    @POST(PASSPORT_HOST + "appuser/login")
    Flowable<HttpResult<User>> login(@Field("userName") String userName, @Field("password") String password, @Field("picVerifyCode") String picVerifyCode,
                                     @Field("smsVerifyCode") String smsVerifyCode, @Field("signature") String signature);

    @GET("app-version/checkversion")
    Flowable<HttpResult<AppUpdate>> appUpdate(@Query("verifyCode") String verifyCode);

//    @GET("index/recommend-games-list")
//    Flowable<HttpResult<List<Game>>> recommendGames(@Query("gameNames") String gameNames, @Query("num") String num);
//
//    @GET("index/getbannerandfuns")
//    Flowable<HttpResult<BannerAndFunc>> bannerAndFunc();
//
//    @GET("index/getnewdeals")
//    Flowable<HttpResult<NewTradings>> newTradings();
//
//    @GET("index/pagead")
//    Flowable<HttpResult<Ad>> gifAd(@Query("position") String position);
//
//    @GET("index/recommended")
//    Flowable<HttpResult<List<RecommendGood>>> recommendGoods(@Query("nums") String num);
//
//    @GET("index/recommend-shops-list")
//    Flowable<HttpResult<List<RecommendShop>>> recommendShops();
//
//    @GET("index/checkqq")
//    Flowable<BaseHttpResult> checkQq(@Query("userQq") String userQq);
//
//    @GET("index/new-trades-list")
//    Flowable<HttpResult<List<NewTrade>>> newTradesList(@Query("gameNames") String gameNames, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
//
//    @GET("games/getallgames")
//    Flowable<HttpResult<CategoryGameList>> categoryGameList(@Query("letter") String letter, @Query("goodsid") String goodsid, @Query("ispublish") String ispublish);
//
//    @GET("games/searchgames")
//    Flowable<HttpResult<CategoryGameList>> searchGameList(@Query("keywords") String keywords, @Query("goodsid") String goodsid, @Query("ispublish") String ispublish);
//
//    @GET("personal/getuserinfo")
//    Flowable<HttpResult<UserInfo>> getUserInfo();
//
//    @GET("msg/haveunread")
//    Flowable<HttpResult<HaveUnread>> haveUnread();
//
//    @GET("index/tenpay-interface")
//    Observable<HttpResult<TenpayInfo>> tenpayInterface();
//
//    @GET("games/gamegoods-by-gameid")
//    Flowable<HttpResult<List<GoodsType>>> goodsType(@Query("gameid") String gameId);
//
//    @GET("games/gettradeclients")
//    Flowable<HttpResult<GameClient>> gameClient(@Query("gameId") String gameId, @Query("goodsId") String goodsId);
//
//    @GET("selltrades/gettradeservices")
//    Flowable<HttpResult<List<GameServer>>> gameServer(@Query("gameId") String gameId, @Query("clientId") String clientId, @Query("goodsId") String goodsId);
//
//    @GET("trades/gettradeslist")
//    Flowable<HttpResult<GoodsList>> goodsList(@Query("gameid") String gameId, @Query("sort_type") String sortType, @Query("deal_type") String dealType,
//                                              @Query("system") String system, @Query("client") String client, @Query("minPrice") String minPrice,
//                                              @Query("maxPrice") String maxPrice, @Query("s_area") String sArea, @Query("pageNum") int pageNum,
//                                              @Query("pageSize") int pageSize, @Query("is_pic") String isPic, @Query("is_video") String isVideo,
//                                              @Query("isbindcertificate") String isBindIdCard, @Query("isbindmobile") String isBindMobile,
//                                              @Query("isbindemail") String idBindEmail);
//
//
//    @GET("buy/verify-promise-password")
//    Flowable<HttpResult<Object>> verifyPromisePwd(@Query("tradeid") String tradeId, @Query("password") String pwd);
//
//    @GET("trades/tradesinfo")
//    Flowable<HttpResult<GoodsDetail>> goodsDetail(@Query("tradeid") String tradeId);
//
//    @GET("trades/iscollection")
//    Flowable<HttpResult<Object>> collection(@Query("productId") String productId, @Query("flag") String flag);
//
//    @GET("buy/verify-orders")
//    Flowable<HttpResult<Object>> verifyOrder(@Query("tradeid") String tradeId);
//
//    @FormUrlEncoded
//    @POST("bargain/check-trade")
//    Flowable<HttpResult<BasePrice>> checkTrade(@Field("tradeid") String tradeId, @Field("from") String from);
//
//    @GET("buy-trade-game-account/index")
//    Flowable<HttpResult<OrderPrice>> gameAccountPrice(@Query("tradeid") String tradeId, @Query("buyNum") String buyNum, @Query("serviceid") String serviceId);
//
//    @GET("buy-trade-first-recharge/index")
//    Flowable<HttpResult<OrderPrice>> firstRechargePrice(@Query("tradeid") String tradeId, @Query("buyNum") String buyNum);
//
//    @GET("buy-trade-behalf-recharge/index")
//    Flowable<HttpResult<OrderPrice>> rechargePrice(@Query("tradeid") String tradeId, @Query("buyNum") String buyNum);
//
//    @GET("buy-trade-game-currency/index")
//    Flowable<HttpResult<OrderPrice>> gameCurrencyPrice(@Query("tradeid") String tradeId, @Query("buyNum") String buyNum);
//
//    @GET("buy-trade-prop/index")
//    Flowable<HttpResult<OrderPrice>> gamePropsPrice(@Query("tradeid") String tradeId, @Query("buyNum") String buyNum);
//
//    @GET("buy/select-first-attr")
//    Flowable<HttpResult<List<FirstRechargeParams>>> firstRechargeParams(@Query("gameid") String gameId, @Query("tradeid") String tradeId);
//
//    @GET("buy-trade-first-recharge/save")
//    Flowable<HttpResult<OrderInfo>> firstRechargeOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("buyermobile") String buyerMobile,
//                                                       @Query("buyerqq") String buyerQq, @QueryMap Map params);
//
//    @GET("buy-trade-game-account/save")
//    Flowable<HttpResult<OrderInfo>> gameAccountOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("serviceid") String serviceId,
//                                                     @Query("buyermobile") String buyerMobile, @Query("buyerqq") String buyerQq,
//                                                     @Query("buyerbindmobile") String buyerBindMobile, @Query("buyerbindmail") String buyerBindEmail,
//                                                     @Query("servicecode") String serviceCode);
//
//    @GET("buy-trade-behalf-recharge/save")
//    Flowable<HttpResult<OrderInfo>> rechargeOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("buyermobile") String buyerMobile,
//                                                  @Query("buyerqq") String buyerQq, @Query("areaname") String areaName, @Query("account") String account,
//                                                  @Query("accountpwd") String pwd, @Query("rolename") String roleName, @Query("require") String require);
//
//    @GET("buy-trade-game-currency/save")
//    Flowable<HttpResult<OrderInfo>> gameCurrencyOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("buyermobile") String buyerMobile,
//                                                      @Query("buyerqq") String buyerQq, @Query("buyerareaname") String buyerAreaName,
//                                                      @Query("buyerrolename") String buyerRoleName, @Query("rolelevel") String roleLevel);
//
//
//    @GET("buy-trade-prop/save")
//    Flowable<HttpResult<OrderInfo>> gamePropOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("buyermobile") String buyerMobile,
//                                                  @Query("buyerqq") String buyerQq);
//
//    @GET("buy/getcustomerinfo")
//    Flowable<HttpResult<CustomerService>> customerServices(@Query("gameId") String gameId);
//
//    @GET("siteauth/sendsms")
//    Flowable<HttpResult<SendSms>> sendSms(@Query("smstype") String smsType);
//
//    @GET("siteauth/check-balance-purchase-verify-code")
//    Flowable<HttpResult<Object>> verifySms(@Query("mobile") String mobile, @Query("code") String code);
//
//    @GET("index/get-coupon-list")
//    Flowable<HttpResult<Object>> couponList(@Query("stype") String stype, @Query("order_id") String orderId, @Query("trade_id") String tradeId);
//
//    @GET("trades/first-continue")
//    Flowable<HttpResult<RechargeAccountDetail>> rechargeAccountDetail(@Query("tradeid") String tradeId);
//
//    @GET("buy/continue-account")
//    Flowable<HttpResult<Renewal>> verifyRenewalAccount(@Query("accountinfo") String accountInfo, @Query("tradeid") String tradeId);
//
//    @GET("trades/continue-to-recharge-list")
//    Flowable<HttpResult<RenewalGoodsList>> renewalGoodsList(@Query("accountinfo") String accountInfo, @Query("sort_type") String sortType,
//                                                            @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
//
//    @GET("trades/write-continuetorecharge-demand")
//    Flowable<HttpResult<RenewalParams>> renewalParams(@Query("tradeid") String tradeId);
//
//    @GET("buy-trade-continue-to-recharge/index")
//    Flowable<HttpResult<OrderPrice>> renewalGoodsPrice(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("accountinfo") String accountInfo);
//
//    @GET("buy-trade-continue-to-recharge/save")
//    Flowable<HttpResult<OrderInfo>> renewalOrder(@Query("tradeid") String tradeId, @Query("buynum") String buyNum, @Query("accountinfo") String accountInfo,
//                                                 @Query("buyermobile") String buyerMobile, @Query("buyerqq") String buyerQq, @QueryMap Map params);
//
//
//    @GET("games/gamegoods")
//    Flowable<HttpResult<SellGoodsParams>> sellGoodsParams(@Query("gameId") String gameId);
//
//    @GET("games/getgameclients")
//    Flowable<HttpResult<SellGoodsPlatform>> sellGoodsPlatform(@Query("gameId") String gameId);
//
//    @GET("selltrades/accountattr")
//    Flowable<HttpResult<SellGameAccountParams>> sellGameAccountParams(@Query("gameId") String gameId, @Query("clientId") String clientId, @Query("sellmodeId") String sellmodeId);
//
//
//    @GET("selltrades/trade-service-fee")
//    Flowable<HttpResult<ServiceFee>> sellServiceFee(@Query("goodsid") String goodsId, @Query("gameid") String gameId, @Query("price") String price,
//                                                    @Query("sellmodeid") String sellModeId);
//
//    @GET("selltrades/edit-trades-info")
//    Flowable<HttpResult<SellGoodsInfo>> sellGoodsInfo(@Query("tradeid") String tradeId);
//
//    @GET("selltrades/getgameservices")
//    Flowable<HttpResult<GameServer>> sellGameServer(@Query("gameId") String gameId, @Query("clientId") String clientId);
//
//    @Multipart
//    @POST("uploadpic/savepic")
//    Flowable<HttpResult<Object>> uploadPic(@Part("description") RequestBody description, @Part MultipartBody.Part file);
//
//    @FormUrlEncoded
//    @POST("sell-trade-quick-charge/save")
//    Flowable<HttpResult<SellOrderInfo>> sellQuickOrder(@Field("content") String content);
//
//    @FormUrlEncoded
//    @POST("sell-trade-game-account/save")
//    Flowable<HttpResult<SellOrderInfo>> sellOrder(@Field("content") String content);
}
