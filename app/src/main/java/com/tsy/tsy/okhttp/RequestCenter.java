package com.tsy.tsy.okhttp;

import java.io.File;

import cn.tsy.base.okhttp.CommonJsonStringCallback;
import cn.tsy.base.okhttp.CommonRequest;
import cn.tsy.base.okhttp.DisposeDataHandle;
import cn.tsy.base.okhttp.DisposeDataListener;
import cn.tsy.base.okhttp.DisposeDownlaodListener;
import cn.tsy.base.okhttp.DisposeDownloadHandle;
import cn.tsy.base.okhttp.MyOkHttpClient;
import cn.tsy.base.okhttp.RequestParams;

/**
 * 封装的okhttp3请求
 * Created by Jocerly on 2018/4/10.
 */

public class RequestCenter {
    /**
     * 根据参数发送所有的get请求
     *
     * @param url
     * @param params
     * @param listener
     * @param clazz
     */
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        MyOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 根据参数发送所有的post请求
     *
     * @param url
     * @param params
     * @param listener
     * @param clazz
     */
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        MyOkHttpClient.post(CommonRequest.createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 直接发送请求
     *
     * @param url
     * @param params
     * @param listener
     * @param clazz
     */
    public static void sendRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        MyOkHttpClient.sendRequest(CommonRequest.createPostRequest(url, params), new CommonJsonStringCallback(new DisposeDataHandle(listener, clazz)));
    }

    /**
     * 上传文件发送请求
     *
     * @param url
     * @param localPath
     * @param listener
     * @param clazz
     */
    public static void uploadFileRequest(String url, String localPath, DisposeDataListener listener, Class<?> clazz) {
        MyOkHttpClient.sendRequest(CommonRequest.uploadFileRequest(url, localPath), new CommonJsonStringCallback(new DisposeDataHandle(listener, clazz)));
    }

    /**
     * 上传文件发送请求
     *
     * @param url
     * @param file
     * @param listener
     * @param clazz
     */
    public static void uploadFileRequest(String url, File file, DisposeDataListener listener, Class<?> clazz) {
        MyOkHttpClient.sendRequest(CommonRequest.uploadFileRequest(url, file), new CommonJsonStringCallback(new DisposeDataHandle(listener, clazz)));
    }

    /**
     * 下载文件发送请求
     *
     * @param url
     * @param listener
     */
    public static void downloadFileRequest(String url, String dirPath, DisposeDownlaodListener listener) {
        MyOkHttpClient.sendDownloadRequest(CommonRequest.downloadFileRequest(url), new DisposeDownloadHandle(listener), url, dirPath);
    }
}
