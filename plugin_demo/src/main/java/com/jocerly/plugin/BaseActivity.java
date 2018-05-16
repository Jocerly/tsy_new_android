package com.jocerly.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.tsy.base.plugin.PluginInterface;

public class BaseActivity extends Activity implements PluginInterface {

    protected Activity that;

    @Override
    public void setContentView(int layoutResID) {
        if (that == null) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }


    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (that == null) {
            super.onCreate(savedInstanceState);
        } else {
//            that.onCreate(savedInstanceState);
        }
    }

    public void onStart() {
        if (that == null) {
            super.onStart();
        } else {
//            that.onStart();
        }
    }

    public void onDestroy() {
        if (that == null) {
            super.onDestroy();
        } else {
//            that.onDestroy();
        }
    }

    public void onPause() {
        if (that == null) {
            super.onPause();
        } else {
//            that.onPause();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    public void onResume() {
        if (that == null) {
            super.onResume();
        } else {
//            that.onResume();
        }
    }

    @Override
    public void attach(Activity activity) {
        that = activity;
    }
}
