package com.tsy.tsy;

import android.app.Application;
import android.content.Context;

import com.tsy.tsy.config.URLConfig;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.tsy.base.uitls.JCLoger;

/**
 * Created by Jocerly on 2018/4/10.
 */

public class MyAppcation extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        JCLoger.openDebugLog(URLConfig.isDebug);
        AutoLayoutConifg.getInstance().useDeviceSize();

        initJpushData();
    }

    private void initJpushData() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Set<String> stringSet = new HashSet<>();//设置tag
        stringSet.add("googleplay");
        JPushInterface.setTags(getApplicationContext(), stringSet, null);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = base;

    }

    public static Context getContext() {
        return context;
    }
}
