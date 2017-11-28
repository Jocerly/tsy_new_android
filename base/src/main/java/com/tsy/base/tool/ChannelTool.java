package com.tsy.base.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

public final class ChannelTool {

    public static String getChannel(Context context) {
        return getChannel(context, "Channel_Default");
    }

    /**
     * 返回市场。  如果获取失败返回defaultChannel
     *
     * @param context
     * @param defaultChannel
     * @return
     */
    public static String getChannel(Context context, String defaultChannel) {

        String channel = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return TextUtils.isEmpty(channel) ? defaultChannel : channel;
    }

}
