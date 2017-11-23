package com.tsy.base.api.interceptor

import android.support.v4.util.ArrayMap
import com.tsy.base.tool.RequestSignTool
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by jay on 2017/11/2.
 */

class RequestSignatureInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        val requestParams = ArrayMap<String, String>()
        val values = StringBuilder()

        if (original.method() == "GET") {
            val urlBuilder = original.url().newBuilder()
            val paramsKey = original.url().queryParameterNames()

            if (paramsKey.size > 0) {
                for (i in paramsKey.indices) {
                    requestParams.put(original.url().queryParameterName(i), original.url().queryParameterValue(i))
                    values.append(original.url().queryParameterValue(i))
                }
                val iterator = requestParams.entries.iterator()
                while (iterator.hasNext()) {
                    val entry = iterator.next()
                    urlBuilder.addEncodedQueryParameter(entry.key, entry.value)
                }
                urlBuilder.addEncodedQueryParameter("verifyCode", RequestSignTool.getVerifyCode(values.toString()))
                requestBuilder.url(urlBuilder.build())
            }
        }

        if (original.method() == "POST") {
            val formBodyBuild = FormBody.Builder()
            val originalBody = original.body() as FormBody?

            if (originalBody!!.size() > 0) {
                for (i in 0 until originalBody.size()) {
                    formBodyBuild.add(originalBody.encodedName(i), originalBody.encodedValue(i))
                    values.append(originalBody.value(i))
                }
                formBodyBuild.addEncoded("verifyCode", RequestSignTool.getVerifyCode(values.toString()))
                requestBuilder.post(formBodyBuild.build())
            }
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
