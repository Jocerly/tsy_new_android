package com.tsy.tsy.plugin;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.tsy.base.uitls.JCLoger;

/**
 * Created by Jocerly on 2017/2/23.
 */

public class MyInstrumentation extends Instrumentation {

    private final Context mContext;
    private final Instrumentation mInstr;

    public MyInstrumentation(Instrumentation base, Context context) {
        mInstr = base;
        mContext = context;
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        JCLoger.debug("走了我的callActivityOnCreate");
        JCLoger.debug("activity = " + activity);
        JCLoger.debug("activity class = " + activity.getClass());
        JCLoger.debug("activity resource = " + activity.getResources());

            try {
                //拿到ContextWrapper类中的字段mBase字段，就是Context
                Class<?> aClass = activity.getClass();
                JCLoger.debug("activity aClass = " + aClass);
                JCLoger.debug("activity aClass aClass = " + aClass.getSuperclass());
                JCLoger.debug("activity aClass aClass aClass = " + aClass.getSuperclass().getSuperclass());
                Field mBaseField = Activity.class.getSuperclass().getSuperclass().getDeclaredField("mBase");

                mBaseField.setAccessible(true);
                Context mBase = (Context) mBaseField.get(activity);
                JCLoger.debug("mBase = " + mBase);

                //拿出Context中的Resource字段
                Class<?> mContextImplClass = Class.forName("android.app.ContextImpl");
                Field mResourcesField = mContextImplClass.getDeclaredField("mResources");
                mResourcesField.setAccessible(true);

                //创建我们自己的Resource
                String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/plugin.apk";
                String mPath = mContext.getApplicationContext().getPackageResourcePath();

                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
                addAssetPathMethod.setAccessible(true);

                addAssetPathMethod.invoke(assetManager, apkPath);
                addAssetPathMethod.invoke(assetManager, mPath);

//                Method ensureStringBlocks = AssetManager.class.getDeclaredMethod("ensureStringBlocks");
//                ensureStringBlocks.setAccessible(true);
//                ensureStringBlocks.invoke(assetManager);

                Resources supResource = mContext.getResources();
                Resources newResource = new Resources(assetManager, supResource.getDisplayMetrics(), supResource.getConfiguration());
                mResourcesField.set(mBase, newResource);
                JCLoger.debug("设置 getResource = " + activity.getResources());
            } catch (Exception e) {
                JCLoger.debug("走了我的callActivityOnCreate 错了 = " + e.getMessage());
                e.printStackTrace();
            }
        super.callActivityOnCreate(activity, icicle);
    }
}
