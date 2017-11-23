package com.tsy.base.tool

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils

/**
 * Created by jay on 2017/11/20.
 */

object ChannelTool {

    fun getChannel(context: Context): String {
        return getChannel(context, "Channel_Default")
    }

    fun getChannel(context: Context, defaultChannel: String): String {

        val appInfo = context.packageManager
                .getApplicationInfo(context.packageName,
                        PackageManager.GET_META_DATA)
        var channel = appInfo.metaData.getString("UMENG_CHANNEL")

        return if (TextUtils.isEmpty(channel)) {
            defaultChannel
        } else {
            channel
        }
    }

}
