package cn.tsy.base.okhttp;

import java.io.File;

/**
 * 下载回调事件
 * Created by Jocerly on 2018/4/13.
 */
public interface DisposeDownlaodListener {
    /**
     * 下载成功
     */
    void onDownloadSuccess(String path, File file);
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
