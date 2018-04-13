package cn.tsy.base.okhttp;

/**
 * Created by Jocerly on 2018/4/13.
 */
public class DisposeDownloadHandle {
    public DisposeDownlaodListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDownloadHandle(DisposeDownlaodListener listener) {
        this.mListener = listener;
    }

    public DisposeDownloadHandle(DisposeDownlaodListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }
}
