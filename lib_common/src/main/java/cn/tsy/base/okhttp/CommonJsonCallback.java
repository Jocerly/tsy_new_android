package cn.tsy.base.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import cn.tsy.base.uitls.JCLoger;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

/**
 * 专门处理JSON数据的回调响应
 * Created by Jocerly on 2018/4/10.
 */
public class CommonJsonCallback implements Callback {
    //与服务器的字段的一个对应关系
    protected final String RESULT_CODE = "errCode"; //有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final String ERROR_MSG = "errMessage";
    protected final String DATA = "data";

    protected final int RESULT_CODE_VALUE = 0;
    protected final String EMPTY_MSG = "";

    //自定义异常类型
    protected final int NETWORK_ERROR = -1; //the network relative error
    protected final int JSON_ERROR = -2; //the JSON relative error
    protected final int OTHER_ERROR = -3; //the unknow error

    private DisposeDataListener mListener;
    private Class<?> mClass;
    private Handler mDeliveryHandler; //进行消息的转发

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败的处理
    @Override
    public void onFailure(@NonNull Call call, @NonNull final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(NETWORK_ERROR, e.getMessage());
            }
        });
    }

    //请求成功的处理
    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理成功的响应
     *
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        //为了保证代码的健壮性
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(NETWORK_ERROR, EMPTY_MSG);
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            JCLoger.debug(result.toString());
            if (result.has(RESULT_CODE) && result.has(ERROR_MSG) && result.has(DATA)) {
                //从JSON对象中取出我们的响应码，如果为0，则是正确的响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(result.getInt(RESULT_CODE), result.getString(ERROR_MSG), result.get(DATA));
                    } else { //需要转化为实体对象
                        Object obj = new Gson().fromJson(result.getString(DATA), mClass);
                        if (obj != null) { //表明正确的转为了实体对象
                            mListener.onSuccess(result.getInt(RESULT_CODE), result.getString(ERROR_MSG), obj);
                        } else {
                            mListener.onFailure(result.getInt(RESULT_CODE), result.getString(ERROR_MSG));
                        }
                    }
                } else { //将服务端返回的异常回调到应用层去处理
                    mListener.onFailure(result.getInt(RESULT_CODE), result.getString(ERROR_MSG));
                }
            } else {
                mListener.onFailure(result.getInt(RESULT_CODE), result.getString(ERROR_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mListener.onFailure(OTHER_ERROR, e.getMessage());
        }
    }
}
