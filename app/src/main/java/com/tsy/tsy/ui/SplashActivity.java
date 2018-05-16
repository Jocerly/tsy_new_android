package com.tsy.tsy.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;
import com.tsy.tsy.config.Constants;
import com.tsy.tsy.uitls.AppAutoUpdate;
import com.tsy.tsy.uitls.Notice;

import cn.tsy.base.uitls.SystemTool;

/**
 * Created by Jocerly on 2018/4/18.
 */
public class SplashActivity extends BaseActivity {
    private boolean isphoneSatte = false, isFile = false;
    private AppAutoUpdate appAutoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        checkPhonePermission();

    }

    /**
     * 手机state权限
     */
    private void checkPhonePermission() {
        if (SystemTool.checkSelfPermission(aty, Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(aty, new String[]{Manifest.permission.READ_PHONE_STATE},
                    Constants.Permission.READ_PHONE_STATE);
        } else {
            isphoneSatte = true;
            checkFilePermission();
        }
    }

    /**
     * 文件存储，读写权限
     */
    private void checkFilePermission() {
        if (SystemTool.checkSelfPermission(aty, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(aty, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, Constants.Permission.EXTERNAL_STORAGE);
        } else {
            isFile = true;
            checkCameraPermission();
        }
    }

    /**
     * 相机权限
     */
    private void checkCameraPermission() {
        if (SystemTool.checkSelfPermission(aty, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(aty, new String[]{Manifest.permission.CAMERA}, Constants.Permission.CAMERA_SCAN);
        } else {
            checkOverlayPermisson();
        }
    }

    /**
     * 检查是否需要开启悬浮窗权限
     */
    private void checkOverlayPermisson() {
        if (Build.VERSION.SDK_INT > 24 && !Settings.canDrawOverlays(this)) {
            Notice.confirm(this, "淘手游需要您开启悬浮窗权限，是否开启？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, Constants.Permission.REQUEST_CODE_OVERLAY_PERMISSON);
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishPermission();
                }
            });
        } else {
            finishPermission();
        }
    }

    /**
     * 结束权限，检查更新
     */
    private void finishPermission() {
        if (isphoneSatte && isFile) {
            showActivity(aty, MainActivity.class);
            finish();
            //检查更新
//            if (appAutoUpdate == null) {
//                appAutoUpdate = new AppAutoUpdate(aty, false);
//                appAutoUpdate.setOnAppUpdateClickLister(new AppAutoUpdate.OnAppUpdateClickLister() {
//                    @Override
//                    public void OnCancleClickLister() {
//                        showActivity(aty, MainActivity.class);
//                        finish();
//                    }
//                });
//            }
//            appAutoUpdate.checkVersion();
        } else {
            showSettingPermission();
        }
    }

    private void showSettingPermission() {
        Notice.confirm(this, "当前应用缺少必要权限，暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, Constants.Permission.REQUEST_CODE_SETTING);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.Permission.EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isFile = true;
                } else {
                    toast("获取手机存储权限失败，请到设置里面打开");
                    isFile = false;
                }
                checkCameraPermission();
                break;
            case Constants.Permission.READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isphoneSatte = true;
                } else {
                    isphoneSatte = false;
                    toast("获取手机设备信息权限失败，请到设置里面打开");
                }
                checkFilePermission();
                break;
            case Constants.Permission.CAMERA_SCAN:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkOverlayPermisson();
                } else {
                    toast("需要此权限才能打开相机，请到设置里面打开");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Permission.REQUEST_CODE_OVERLAY_PERMISSON:
                finishPermission();
                break;
            case Constants.Permission.REQUEST_CODE_SETTING:
                checkPhonePermission();
                break;
        }
    }
}
