package com.tsy.tsy.uitls;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import com.tsy.tsy.config.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

public class Common {
    /**
     * 为指定edittext添加限制小数点
     *
     * @param editText
     * @param precision 小数点后允许的位数
     */
    public static void setDecimalFilter(EditText editText, final int precision) {
        if (editText != null) {
            InputFilter lengthFilter = new InputFilter() {

                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    if ("".equals(source)) {
                        return null;
                    } else {
                        String[] csArray = dest.toString().split("\\.");
                        if (csArray.length > 1 && csArray[1].length() >= precision) {
                            return "";
                        }

                        if (".".equals(source)) {
                            if (dest.length() - dstart > precision) {
                                return "";
                            }
                        }
                    }
                    return source;
                }
            };
            editText.setFilters(new InputFilter[]{lengthFilter});
        }
    }


    /**
     * 获取ANDROID_ID
     *
     * @param var0
     * @return
     */
    public static String getAndroidId(Context var0) {
        String var1 = "";
        var1 = Settings.Secure.getString(var0.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(var1)) {
            var1 = "";
        }

        return var1;
    }

    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs.append("0").append(stmp);
            else {
                hs = hs.append(stmp);
            }
        }
        return String.valueOf(hs);
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static String callCmd(String cmd, String filter) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);

            // 执行命令cmd，只取结果中含有filter的这一行
            while ((line = br.readLine()) != null
                    && line.contains(filter) == false) {
                // result += line;
            }

            result = line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断WIFI是否连接
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 得到sharedPreferences的key的值
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getAppInfo(Context context, String key,
                                    String defaultValue) {
        return getAppInfo(context, "", key, defaultValue);
    }

    /**
     * 得带sharedPreferences的key的值
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getAppInfo(Context context, String prefix, String key,
                                    String defaultValue) {
        SharedPreferences app_info = context.getSharedPreferences(
                Constants.PreferenceName, 0);
        return app_info.getString(prefix + key, defaultValue);
    }

    /**
     * 得到sharedPreferences的key的bool值
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getAppInfo(Context context, String key,
                                     boolean defaultValue) {
        return getAppInfo(context, "", key, defaultValue);
    }

    /**
     * 得带sharedPreferences的key的bool值
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getAppInfo(Context context, String prefix, String key,
                                     boolean defaultValue) {
        SharedPreferences app_info = context.getSharedPreferences(
                Constants.PreferenceName, 0);
        return app_info.getBoolean(prefix + key, defaultValue);
    }

    /**
     * 设置sharedPreferences的key的值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setAppInfo(Context context, String key, String value) {
        setAppInfo(context, "", key, value);
    }

    /**
     * 设置sharedPreferences的key的值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setAppInfo(Context context, String prefix, String key,
                                  String value) {
        if (!"".equals(value)) {
            SharedPreferences app_info = context.getSharedPreferences(Constants.PreferenceName, 0);
            Editor edit = app_info.edit();
            edit.putString(key, value);
            edit.commit();
            edit = null;
        }
    }

    /**
     * 设置sharedPreferences的key的bool值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setAppInfo(Context context, String prefix, String key,
                                  boolean value) {
        SharedPreferences app_info = context.getSharedPreferences(
                Constants.PreferenceName, 0);
        Editor edit = app_info.edit();
        edit.putBoolean(key, value);
        edit.commit();
        edit = null;

    }

    /**
     * 通过map来保存数据
     *
     * @param context
     * @param map
     */
    public static void setAppInfoByMap(Context context, String prefix,
                                       HashMap<String, String> map) {
        if (map != null) {
            SharedPreferences app_info = context.getSharedPreferences(
                    Constants.PreferenceName, 0);
            Editor edit = app_info.edit();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                edit.putString(prefix + key, map.get(key));
            }
            edit.commit();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取通知栏权限是否开启
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {
        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
      /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断手机网络是否可用
     *
     * @param context 上下文
     * @return 返回手机网络状态
     */
    public static boolean isNetWorkOK(Context context) {
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cwjManager.getActiveNetworkInfo().isAvailable();
    }

    /**
     * 时间对比
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isCurrentDate(String start, String end) {
        if (TextUtils.isEmpty(start)) {
            return false;
        }
        boolean current = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SimpleDateFormat);
        try {
            Date dt1 = dateFormat.parse(start);
            Date dt2 = dateFormat.parse(end);
            if (dt1.getTime() > dt2.getTime()) {
                current = false;
            } else {
                current = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return current;
    }

}
