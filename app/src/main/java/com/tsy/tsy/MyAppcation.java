package com.tsy.tsy;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.didi.virtualapk.PluginManager;
import com.tsy.tsy.config.URLConfig;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.lang.reflect.Method;

import cn.tsy.base.uitls.JCLoger;
import dalvik.system.DexClassLoader;

/**
 * Created by Jocerly on 2018/4/10.
 */

public class MyAppcation extends Application {
    private static Context context;
    public static DexClassLoader mClassLoader;
    private AssetManager assetManager;
    private Resources newResource;
    private Resources.Theme mTheme;

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
        PluginManager.getInstance(base).init();

        initPluginManager();
    }

    private void initPluginManager() {
        try {
            //创建我们自己的Resource
            String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/plugin.apk";
            String mPath = getPackageResourcePath();

            assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assetManager, apkPath);

            newResource = new Resources(assetManager, getResources().getDisplayMetrics(), getResources().getConfiguration());

            mTheme = newResource.newTheme();
            mTheme.setTo(super.getTheme());
        } catch (Exception e) {
            JCLoger.debug("走了我的callActivityOnCreate 错了 = " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public AssetManager getAssets() {
        return assetManager == null ? super.getAssets() : assetManager;
    }

    @Override
    public Resources getResources() {
        return newResource == null ? super.getResources() : newResource;
    }

    @Override
    public Resources.Theme getTheme() {
        return mTheme == null ? super.getTheme() : mTheme;
    }

    public static Context getContext() {
        return context;
    }
}
