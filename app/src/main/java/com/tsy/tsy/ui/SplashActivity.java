package com.tsy.tsy.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.tsy.base.api.Retrofits;
import com.tsy.base.api.tool.ServerResponseFunc;
import com.tsy.base.constants.BasicParams;
import com.tsy.base.tool.ChannelTool;
import com.tsy.base.tool.RequestSignTool;
import com.tsy.base.tool.VersionTool;
import com.tsy.tsy.R;
import com.tsy.tsy.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import timber.log.Timber;

/**
 * Created by jay on 2017/11/23.
 */

@RuntimePermissions
public class SplashActivity extends BaseActivity {

    private static final int REQUEST_CODE_SETTING = 2017;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkVersion();
    }


    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    void checkVersion() {
        getDeviceId();

        Retrofits.api().appUpdate(RequestSignTool.getVerifyCode(VersionTool.getVersionCode(this)))
                .map(new ServerResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(appUpdate -> Timber.d(appUpdate.versionName), throwable -> goNextPage());
    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    void writeSdCardDenied() {
        showDeniedDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SETTING:
                checkVersion();
                break;
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private void getDeviceId() {
        String deviceId = ChannelTool.getChannel(this).contains("xiaomi") ? "0" : ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        BasicParams.getInstance().setDeviceId(deviceId);
    }

//    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE)
//    void readPhoneStateDenied() {
//
//    }

    private void showDeniedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", (dialog, which) -> {

                })
                .setPositiveButton("确定", (dialog, which) -> startAppSettings());
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE_SETTING);
    }

    private void goNextPage() {
        Flowable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> LoadingActivity.start(this));
    }
}
