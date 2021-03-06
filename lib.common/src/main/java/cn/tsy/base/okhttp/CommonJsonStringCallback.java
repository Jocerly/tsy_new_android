package cn.tsy.base.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 专门处理字符串数据的回调响应，在使用时解析
 * Created by Jocerly on 2018/4/10.
 */
public class CommonJsonStringCallback implements Callback {
    //自定义异常类型
    protected final int RESULT_CODE_SUC = 0;
    protected final int NETWORK_ERROR = -1; //the network relative error
    protected final int JSON_ERROR = -2; //the JSON relative error
    protected final int OTHER_ERROR = -3; //the unknow error

    private Handler mDeliveryHandler; //进行消息的转发
    private DisposeDataListener mListener;

    public CommonJsonStringCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(NETWORK_ERROR, e.getMessage());
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(Object responseObj) {
        if (responseObj != null) { //表明正确的转为了实体对象
            mListener.onSuccess(RESULT_CODE_SUC, "", responseObj);
        } else {
            mListener.onFailure(JSON_ERROR, "");
        }
    }
}
