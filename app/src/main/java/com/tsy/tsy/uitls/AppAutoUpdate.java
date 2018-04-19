package com.tsy.tsy.uitls;

import android.app.Activity;
import android.content.DialogInterface;

import com.tsy.tsy.config.URLConfig;
import com.tsy.tsy.entry.Checkversion;
import com.tsy.tsy.okhttp.RequestCenter;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import cn.tsy.base.okhttp.DisposeDataListener;
import cn.tsy.base.okhttp.DisposeDownlaodListener;
import cn.tsy.base.okhttp.RequestParams;
import cn.tsy.base.ui.ViewInject;
import cn.tsy.base.uitls.JCLoger;
import cn.tsy.base.uitls.MD5Utils;
import cn.tsy.base.uitls.SHA;
import cn.tsy.base.uitls.SystemTool;

/**
 * 自动检测更新下载类
 *
 * @author Jocerly
 */
public class AppAutoUpdate {
    private Activity context;
    private boolean isShowToast = false;

    private Checkversion checkversion;
    private RequestParams params;

    private OnAppUpdateClickLister onAppUpdateClickLister;

    public AppAutoUpdate(Activity context, boolean isShowToast) {
        this.context = context;
        this.isShowToast = isShowToast;
        params = new RequestParams();
    }

    /**
     * 提示有新版本
     */
    protected void showUpdataDialog() {
        Notice.confirm(context, "版本更新", checkversion.getVersionMessage(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                downLoadApk();
                if (onAppUpdateClickLister != null) {
                    onAppUpdateClickLister.OnCancleClickLister();
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onAppUpdateClickLister != null) {
                    onAppUpdateClickLister.OnCancleClickLister();
                }
            }
        }, false);
    }

    /**
     * 下载apk
     */
    protected void downLoadApk() {

        RequestCenter.downloadFileRequest(checkversion.getVersionUrl(), "", new DisposeDownlaodListener() {
            @Override
            public void onDownloadSuccess(String path, File file) {
                SystemTool.installApk(context, file);
            }

            @Override
            public void onDownloading(int progress) {


            }

            @Override
            public void onDownloadFailed(Object responseObj) {
                ViewInject.toast(context, "下载失败");
                if (onAppUpdateClickLister != null) {
                    onAppUpdateClickLister.OnCancleClickLister();
                }
            }
        });
    }

    /**
     * 版本信息校验
     */
    public void checkVersion() {
        String code = "";
        try {
            code = SHA.getSHA1(MD5Utils.computeDigest(String.valueOf(SystemTool.getAppVersionCode(context)).replaceAll(" ", "")));
            JCLoger.debug(code);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        params.put("verifyCode", code);
        params.put("AppToken", "");
        params.put("mt", "Android");
        params.put("channel", "Channel_Default");
        params.put("mk", "867391035393961");
        params.put("versionCode", String.valueOf(SystemTool.getAppVersionCode(context)));

        RequestCenter.getRequest(URLConfig.CHECK_VERSION, params, new DisposeDataListener() {
            @Override
            public void onSuccess(int errCode, String errMessage, Object responseObj) {
                checkversion = (Checkversion) responseObj;
                JCLoger.debug(errCode + "----" + errMessage);
                JCLoger.debug(checkversion.getVersionCode() + "---" + checkversion.getVersionName() + "----" + checkversion.getVersionUrl());
                showUpdataDialog();
            }

            @Override
            public void onFailure(int errCode, String errMessage) {
                ViewInject.toast(context, errCode + "----" + errMessage);
                if (1030 == errCode) {
                    if (isShowToast) {
                        ViewInject.toast(context, "已是最新版");
                    }
                } else {
                    if (isShowToast) {
                        ViewInject.toast(context, errMessage);
                    }
                }
                if (onAppUpdateClickLister != null) {
                    onAppUpdateClickLister.OnCancleClickLister();
                }
            }
        }, Checkversion.class);
    }

    /**
     * 更新回调
     *
     * @author asus
     */
    public interface OnAppUpdateClickLister {
        public void OnCancleClickLister();
    }

    public void setOnAppUpdateClickLister(OnAppUpdateClickLister onAppUpdateClickLister) {
        this.onAppUpdateClickLister = onAppUpdateClickLister;
    }
}