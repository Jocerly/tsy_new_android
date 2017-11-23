package com.tsy.tsy.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import com.tsy.base.api.TsyClient
import com.tsy.base.api.tool.ServerResponseFunc
import com.tsy.base.constants.BasicParams
import com.tsy.tsy.R
import com.tsy.tsy.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

/**
 * Created by jay on 2017/11/16.
 */
@RuntimePermissions
class SplashActivity : BaseActivity() {

    private val REQUEST_CODE_SETTING = 2017

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe({
//                    startActivity(intentFor<MainActivity>())
//                })

        checkVersion()

    }



    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun checkVersion() {
        TsyClient.api.appUpdate()
                .map(ServerResponseFunc())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("111", it.toString())
                }, {
                    it.printStackTrace()
                })

    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun writeSdCardDenied() {
        showDeniedDialog()
    }

    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + packageName)
        startActivityForResult(intent, REQUEST_CODE_SETTING)
    }

//    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    fun showExplain(request: PermissionRequest) {
//    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun never() {
    }


    @SuppressLint("MissingPermission", "HardwareIds")
    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    fun getDeviceId() {
        val telephoneManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        if (!TextUtils.isEmpty(telephoneManager.deviceId)) {
            BasicParams.deviceId = telephoneManager.deviceId
        }
    }

    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE)
    fun readPhoneStateDenied() {
        showDeniedDialog()
    }

    fun showDeniedDialog() {
        AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", { dialog, which -> finish() })
                .setPositiveButton("确定", { dialog, which -> startAppSettings() })
    }

}