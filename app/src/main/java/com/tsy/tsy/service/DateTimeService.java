package com.tsy.tsy.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tsy.tsy.ui.MainActivity;

import cn.tsy.base.uitls.JCLoger;

/**
 * 系统时间监听广播
 */
public class DateTimeService extends BroadcastReceiver {
    private OnDateTimeChangeListener onDateTimeChangeListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_TIME_CHANGED:
                //设置了系统时间
                Toast.makeText(context, "system time changed", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_TIMEZONE_CHANGED:
                //设置了系统时区的action
                Toast.makeText(context, "system time zone changed", Toast.LENGTH_SHORT).show();
                break;
        }
        if (context instanceof MainActivity) {
            JCLoger.debug("---------");
            onDateTimeChangeListener.DateTimeChanged(intent.getAction());
        }
    }

    public interface OnDateTimeChangeListener {
        void DateTimeChanged(String action);
    }

    public void setOnDateTimeChangeListener(OnDateTimeChangeListener onDateTimeChangeListener) {
        this.onDateTimeChangeListener = onDateTimeChangeListener;
    }
}
