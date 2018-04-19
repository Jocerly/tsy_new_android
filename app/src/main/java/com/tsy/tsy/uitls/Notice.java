package com.tsy.tsy.uitls;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.KeyEvent;

public class Notice {


    public static void alert(Context context, String message) {
        alert(context, "温馨提示", message, null);
    }

    public static void alert(Context context, String title, String message) {
        alert(context, title, message, null);
    }

    public static void alert(Context context, String title, String message, final OnClickListener listener) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton("确定", listener).show();
    }

    public static void alert(Context context, String message, final OnClickListener listener) {
        new AlertDialog.Builder(context).setTitle("温馨提示").setMessage(message)
                .setPositiveButton("确定", listener).show();
    }

    public static void alert(Context context, String message, final OnClickListener listener, boolean flag) {
        new AlertDialog.Builder(context).setTitle("温馨提示").setMessage(message)
                .setPositiveButton("确定", listener).show().setCancelable(flag);
    }

    public static void alertAndExitApp(final Context context, String message, final OnClickListener listener, boolean flag) {
        new AlertDialog.Builder(context).setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    ((Activity) context).finish();
                    System.exit(0);
                }
                return false;
            }
        }).setTitle("温馨提示").setMessage(message).setCancelable(flag)
                .setPositiveButton("确定", listener).show();
    }

    public static void confirm(Context context, String message, OnClickListener yesListener, OnClickListener cancelListener) {
        confirm(context, "温馨提示", message, yesListener, cancelListener, true);
    }

    public static void confirm(Context context, String title, String message, OnClickListener yesListener, OnClickListener cancelListener, boolean flag) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", yesListener)
                .setNegativeButton("取消", cancelListener)
                .setCancelable(flag)
                .show();
    }
}
