package com.tsy.tsy.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.tsy.tsy.MyAppcation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.tsy.base.uitls.JCLoger;

public class IActivityManagerHandler implements InvocationHandler {

    private static final String TAG = "IActivityManagerHandler";

    Object mBase;

    public IActivityManagerHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("startActivity".equals(method.getName())) {
            JCLoger.debug("startActivity方法拦截了");

            // 找到参数里面的第一个Intent 对象
            Intent raw;
            int index = 0;

            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            raw = (Intent) args[index];
            //创建一个要被掉包的Intent
            Intent newIntent = new Intent();
            // 替身Activity的包名, 也就是我们自己的"包名"
            String stubPackage = MyAppcation.getContext().getPackageName();

            // 这里我们把启动的Activity临时替换为 ZhanKengActivitiy
            ComponentName componentName = new ComponentName(stubPackage, ProxyActivity.class.getName());
            newIntent.setComponent(componentName);

            // 把我们原始要启动的TargetActivity先存起来
            newIntent.putExtra(AMSHookHelper.EXTRA_TARGET_INTENT, raw);

            // 替换掉Intent, 达到欺骗AMS的目的
            args[index] = newIntent;
            JCLoger.debug("startActivity方法 hook 成功");
            JCLoger.debug("args[index] hook = " + args[index]);
            return method.invoke(mBase, args);
        }

        return method.invoke(mBase, args);
    }
}
