package com.tsy.tsy;

import android.app.Application;
import android.content.Context;

import com.tsy.tsy.config.URLConfig;
import com.zhy.autolayout.config.AutoLayoutConifg;

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
