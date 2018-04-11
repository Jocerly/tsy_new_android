package cn.tsy.base.okhttp;

import java.io.File;
import java.util.Map;

import cn.tsy.base.uitls.JCLoger;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 接收请求参数，为我们生成Request对象
 * Created by Jocerly on 2018/4/10.
 */
public class CommonRequest {
    /**
     * 创建Get请求的Request
     *
     * @param url
     * @param params
     * @return 通过传入的参数返回一个Get类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }

        String requestUrl = urlBuilder.substring(0, urlBuilder.length() - 1);
        JCLoger.debug(requestUrl);
        return new Request.Builder().url(requestUrl).get().build();
    }

    /**
     * 创建Post请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();
        JCLoger.debug(url + params.urlParams.toString());

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFromBodyBuilder.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    /**
     * 上传文件Request
     *
     * @param url
     * @param localPath
     * @return
     */
    public static Request uploadFileRequest(String url, String localPath) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        File f = new File(localPath);
        builder.addFormDataPart("file", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));

        final MultipartBody requestBody = builder.build();
        //构建请求
        JCLoger.debug(url + localPath);
        return new Request.Builder()
                .url(url)//地址
                .post(requestBody)//添加请求体
                .build();

    }

    /**
     * 上传文件Request
     *
     * @param url
     * @param file
     * @return
     */
    public static Request uploadFileRequest(String url, File file) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));

        final MultipartBody requestBody = builder.build();
        //构建请求
        JCLoger.debug(url + file.getAbsolutePath());
        return new Request.Builder()
                .url(url)//地址
                .post(requestBody)//添加请求体
                .build();

    }
}
