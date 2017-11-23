package com.tsy.base.tool

import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by jay on 17/7/31.
 */

object VersionTool {


    fun getVersionName(context: Context): String {
        try {
            val pi = context.packageManager.getPackageInfo(context.packageName, 0)
            return pi.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return "3.1.0"
        }

    }

    fun getVersionCode(context: Context): String {
        try {
            val pi = context.packageManager.getPackageInfo(context.packageName, 0)
            return pi.versionCode.toString() + ""
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return "0"
        }

    }


}
