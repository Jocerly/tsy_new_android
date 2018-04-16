package com.tsy.tsy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import cn.tsy.base.okhttp.RequestParams;
import cn.tsy.base.ui.I_JCFragment;
import cn.tsy.base.ui.ViewInject;
import cn.tsy.base.uitls.JCLoger;

/**
 * Fragment基类
 * Created by Jocerly on 2018/4/16.
 */
public abstract class BaseFragment extends Fragment implements I_JCFragment {
    protected Activity aty;
    protected RequestParams params = null;
    protected Fragment currentFragment;

    @Override
    public void onDestroy() {
        super.onDestroy();
        JCLoger.debug(aty.getClass().getName() + "---------onDestroy ");
        currentFragment = null;
        aty = null;
    }

    public void toast(String msg) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                ViewInject.toast(aty, msg);
            } else {
                ViewInject.toast(aty, "加载失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void longToast(String msg) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                ViewInject.longToast(aty, msg);
            } else {
                ViewInject.longToast(aty, "加载失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void toast(int msgId) {
        try {
            if (msgId != 0) {
                ViewInject.toast(aty, getResources().getString(msgId));
            } else {
                ViewInject.toast(aty, "加载失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity跳转
     */
    public void showActivity(Context context, Class<?> cls) {
        JCLoger.debug(context.getClass().getName());
        Intent intent = new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * Activity跳转
     */
    public void showActivityForResult(Context context, Class<?> cls, int requestCode) {
        JCLoger.debug(context.getClass().getName());
        Intent intent = new Intent();
        intent.setClass(context, cls);
        startActivityForResult(intent, requestCode);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    public void showActivityForResult(Context context, Class<?> cls, Bundle extras, int requestCode) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(context, cls);
        startActivityForResult(intent, requestCode);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Context context, Class<?> cls, int flag) {
        JCLoger.debug(context.getClass().getName());
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.addFlags(flag);
        startActivity(intent);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Context context, Class<?> cls, Bundle extras, int flag) {
        JCLoger.debug(context.getClass().getName());
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(extras);
        intent.setFlags(flag);
        startActivity(intent);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Context context, Intent it) {
        startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    public void showActivity(Context context, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(context, cls);
        startActivity(intent);
        aty.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    /**
     * 用Fragment替换视图
     *
     * @param layoutId       将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int layoutId, Fragment targetFragment) {
        if (targetFragment.equals(currentFragment)) {
            return;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }
        if (!targetFragment.isAdded()) {
            transaction.add(layoutId, targetFragment, targetFragment.getClass().getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
//            transaction.remove(currentFragment);//移除当前Fragment，下次打开需要从新初始化
        }
        currentFragment = targetFragment;
//        transaction.commit();
        transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //transaction.commit();-->出现了这个错误 IllegalStateException: Can not perform this action after onSaveInstanceState
        transaction.commitAllowingStateLoss();
    }
}
