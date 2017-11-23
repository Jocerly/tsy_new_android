package com.tsy.base.api.error.exception

import com.tsy.base.api.type.HttpResult

/**
 * Created by jay on 17/8/1.
 */

class ResponseException(private val errMessage: String, private val errCode: Int) : Exception() {

    val errorMessage: String
        get() = errMessage

    val statusCode: Int
        get() = errCode

    companion object {

        //成功
        val STATUS_CODE_SUCCESS = 0

        //需要登录
        val STATUS_CODE_NEED_LOGIN = 1002

        //客服QQ验证未通过
        val STATUS_CODE_SERVICE_QQ_VALIDATE_FAILURE = 1015

        //没有绑定手机，需要绑定手机
        val STATUS_CODE_NEED_BIND_MOBILE = 1017

        //用户当前有大于等于3个商品的出价等待卖家处理
        val STATUS_CODE_BARGAIN_MORE_THAN_THREE_BID = 110001

        //用户已对该商品出过价
        val STATUS_CODE_BARGAIN_THIS_GOODS_IS_BIDED = 110002

        //用户当日收到的卖家同意出价后，超过2次没有及时下单付款成功或还未成功下单付款
        val STATUS_CODE_BARGAIN_MORE_THAN_TWO_SUCCESS_TRADE = 110003

        //用户成功购买，后台客服取消订单
        val STATUS_CODE_BARGAIN_SUCCESS_BUT_SERVER_CANCEL_ORDER = 110023
    }

}
