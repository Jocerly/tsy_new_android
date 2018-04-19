package cn.tsy.base.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 下载文件处理，回调
 * Created by Jocerly on 2018/4/13.
 */
public class CommonDownloadCallback implements Callback {
    //自定义异常类型
    protected final int NETWORK_ERROR = -1; //the network relative error

    private DisposeDownlaodListener mListener;
    private Handler mDeliveryHandler; //进行消息的转发
    private String saveDir;
    private String url;

    public CommonDownloadCallback(DisposeDownloadHandle handle, String url, String dirPath) {
        this.mListener = handle.mListener;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
        this.url = url;
        this.saveDir = dirPath;
    }

    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private String isExistDir(String saveDir) throws IOException {
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        return downloadFile.getAbsolutePath();
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onDownloadFailed(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        //储存下载文件的目录
        String savePath = isExistDir(saveDir);
        try {
            is = response.body().byteStream();
            long total = response.body().contentLength();
            final File file = new File(savePath, getNameFromUrl(url));
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                final int progress = (int) (sum * 1.0f / total * 100);
                //下载中
                mDeliveryHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onDownloading(progress);
                    }
                });
            }
            fos.flush();
            //下载完成
            mDeliveryHandler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDownloadSuccess(file.getAbsolutePath(), file);
                }
            });
        } catch (final Exception e) {
            mDeliveryHandler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDownloadFailed(new OkHttpException(NETWORK_ERROR, e));
                }
            });
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
