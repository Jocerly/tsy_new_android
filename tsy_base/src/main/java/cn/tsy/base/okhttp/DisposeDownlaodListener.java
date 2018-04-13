package cn.tsy.base.okhttp;

/**
 * 下载回调事件
 * Created by Jocerly on 2018/4/13.
 */
public interface DisposeDownlaodListener {
    /**
     * 下载成功
     */
    void onDownloadSuccess(String path);
    /**
     * 下载进度
     * @param progress
     */
    void onDownloading(int progress);
    /**
     * 下载失败
     */
    void onDownloadFailed(Object responseObj);
}
