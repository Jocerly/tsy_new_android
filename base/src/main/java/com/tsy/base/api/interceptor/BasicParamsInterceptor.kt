//package com.tsy.base.api.interceptor
//
//import android.support.v4.util.ArrayMap
//import android.util.ArrayMap
//
//import java.io.IOException
//import java.util.ArrayList
//
//import okhttp3.FormBody
//import okhttp3.Headers
//import okhttp3.HttpUrl
//import okhttp3.Interceptor
//import okhttp3.Request
//import okhttp3.Response
//import java.lang.IllegalArgumentException
//
///**
// * Created by jay on 17/7/31.
// */
//
//class BasicParamsInterceptor private constructor() : Interceptor {
//
//    private val queryParamsMap = ArrayMap<String, String>()
//    val paramsMap = ArrayMap<String, String>()
//    val headerMap = ArrayMap<String, String>()
//    private val headerLinesList = ArrayList<String>()
//
//
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val original = chain.request()
//        val requestBuilder = original.newBuilder()
//
//        //Header params
//        val headerBuilder = original.headers().newBuilder()
//        if (!headerMap.isEmpty) {
//            val iterator = headerMap.entries.iterator()
//            while (iterator.hasNext()) {
//                val entry = iterator.next() as Entry<*, *>
//                headerBuilder.add(entry.key, entry.value)
//            }
//            requestBuilder.headers(headerBuilder.build())
//        }
//
//        if (!headerLinesList.isEmpty()) {
//            for (line in headerLinesList) {
//                headerBuilder.add(line)
//            }
//            requestBuilder.headers(headerBuilder.build())
//        }
//
//        //add Params by requestMethod(Get or Post)
//        if (original.method() == "GET" && !paramsMap.isEmpty) {
//            val iterator = paramsMap.entries.iterator()
//            val urlBuilder = original.url().newBuilder()
//            while (iterator.hasNext()) {
//                val entry = iterator.next() as Entry<*, *>
//                urlBuilder.addEncodedQueryParameter(entry.key, entry.value)
//            }
//            requestBuilder.url(urlBuilder.build())
//        }
//
//        if (original.method() == "POST" && !paramsMap.isEmpty) {
//            val formBodyBuild = FormBody.Builder()
//            val originalBody = original.body() as FormBody?
//            val iterator = paramsMap.entries.iterator()
//            while (iterator.hasNext()) {
//                val entry = iterator.next() as Entry<*, *>
//                formBodyBuild.addEncoded(entry.key, entry.value)
//            }
//
//            for (i in 0 until originalBody!!.size()) {
//                formBodyBuild.add(originalBody!!.encodedName(i), originalBody.encodedValue(i))
//            }
//
//            requestBuilder.post(formBodyBuild.build())
//        }
//
//        val request = requestBuilder.build()
//
//        return chain.proceed(request)
//    }
//
//    class Build {
//        internal var interceptor: BasicParamsInterceptor
//
//        init {
//            interceptor = BasicParamsInterceptor()
//        }
//
//        fun addParam(key: String, value: String): Build {
//            interceptor.paramsMap.put(key, value)
//            return this
//        }
//
//        fun addParams(params: Map<String, String>): Build {
//            interceptor.paramsMap.putAll(params)
//            return this
//        }
//
//        fun addHeaderParam(key: String, value: String): Build {
//            interceptor.headerMap.put(key, value)
//            return this
//        }
//
//        fun addHeaderParams(params: Map<String, String>): Build {
//            interceptor.headerMap.putAll(params)
//            return this
//        }
//
//        fun addHeaderLine(headerLine: String): Build {
//            val index = headerLine.indexOf(":")
//            if (index == -1) {
//                throw IllegalArgumentException("Unexcepted header: " + headerLine)
//            }
//            interceptor.headerLinesList.add(headerLine)
//            return this
//        }
//
//        fun addHeaderLineList(headerLineList: List<String>): Build {
//            for (headerLine in headerLineList) {
//                val index = headerLine.indexOf(":")
//                if (index == -1) {
//                    throw IllegalArgumentException("Unexcepted header: " + headerLine)
//                }
//                interceptor.headerLinesList.add(headerLine)
//            }
//            return this
//        }
//
//        fun addQueryParam(key: String, value: String): Build {
//            interceptor.queryParamsMap.put(key, value)
//            return this
//        }
//
//        fun addQueryParam(params: Map<String, String>): Build {
//            interceptor.queryParamsMap.putAll(params)
//            return this
//        }
//
//        fun build(): BasicParamsInterceptor {
//            return interceptor
//        }
//
//    }
//
//
//}
