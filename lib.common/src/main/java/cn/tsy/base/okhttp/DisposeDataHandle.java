package cn.tsy.base.okhttp;

/**
 * 将我们的事件回调和用于处理Json转换实体对象的字节码对象做一个封装
 * Created by Jocerly on 2018/4/10.
 */
public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }
}
