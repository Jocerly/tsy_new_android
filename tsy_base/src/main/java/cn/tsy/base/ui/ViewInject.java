/*
 * Copyright (c) 2014,JCFrameForAndroid Open Source Project,Jocerly.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.tsy.base.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.tsy.base.R;
import cn.tsy.base.uitls.DensityUtils;


/**
 * 侵入式View的调用工具类
 *
 * @author Jocerly.
 */
public class ViewInject {

    private ViewInject() {
    }

    private static class ClassHolder {
        private static final ViewInject instance = new ViewInject();
    }

    /**
     * 类对象创建方法
     *
     * @return 本类的对象
     */
    public static ViewInject create() {
        return ClassHolder.instance;
    }

    /**
     * 长时间显示一个toast
     *
     * @param msg
     */
    public static void longToast(String msg) {
        try {
            longToast(JCActivityStack.create().topActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 长时间显示一个toast
     *
     * @param msg
     */
    public static void longToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示一个toast
     *
     */
    public static void toast(Context context, String msg) {
        TextView textView = new TextView(context);
        textView.setText(msg);
        textView.setBackgroundResource(R.drawable.my_toast);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setView(textView);
        toast.show();
    }

    /**
     * 显示一个toast
     *
     */
    public static void toast(Context context, String msg, float size) {
        TextView textView = new TextView(context);
        textView.setText(msg);
        textView.setBackgroundResource(R.drawable.my_toast);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(size);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setView(textView);
        toast.show();
    }

    /**
     * 显示一个toast
     */
    public static void toastCenter(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 返回一个退出确认对话框
     */
    public void getExitDialog(final Context context, String title,
                              OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", l);
        builder.create();
        builder.show();
        builder = null;
    }

    /**
     * 返回一个自定义View对话框
     */
    public AlertDialog getDialogView(Context cxt, String title, View view) {
        AlertDialog dialog = new AlertDialog.Builder(cxt).create();
        dialog.setMessage(title);
        dialog.setView(view);
        dialog.show();
        return dialog;
    }

    /**
     * 返回一个等待信息弹窗
     *
     * @param aty    要显示弹出窗的Activity
     * @param msg    弹出窗上要显示的文字
     * @param cancel dialog是否可以被取消
     */
    public static ProgressDialog getprogress(Activity aty, String msg,
                                             boolean cancel) {
        // 实例化一个ProgressBarDialog
        ProgressDialog progressDialog = new ProgressDialog(aty);
        progressDialog.setMessage(msg);
        progressDialog.getWindow().setLayout(DensityUtils.getScreenW(aty),
                DensityUtils.getScreenH(aty));
        progressDialog.setCancelable(cancel);
        // 设置ProgressBarDialog的显示样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        return progressDialog;
    }
}
