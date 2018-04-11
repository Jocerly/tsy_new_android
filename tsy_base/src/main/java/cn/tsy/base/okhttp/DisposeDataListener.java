package cn.tsy.base.okhttp;

/**
 * 回调事件处理
 * Created by Jocerly on 2018/4/10.
 */
public interface DisposeDataListener {
    //请求成功回调事件处理
    public void onSuccess(Object responseObj);

    //请求失败回调事件处理
    public void onFailure(Object responseObj);
}
