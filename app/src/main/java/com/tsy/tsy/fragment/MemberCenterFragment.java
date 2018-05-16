package com.tsy.tsy.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;
import com.tsy.tsy.plugin.AMSHookHelper;
import com.tsy.tsy.plugin.MyHookHelper;
import com.tsy.tsy.ui.MainActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tsy.base.uitls.JCLoger;
import dalvik.system.DexClassLoader;

/**
 * Created by Jocerly on 2018/4/16.
 */

public class MemberCenterFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        aty = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData(view);
        return view;
    }

    @Override
    public void initView(View view) {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000, true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000, true, true);
            }
        });
    }

    @Override
    public void initData(View view) {
    }

    @OnClick(R.id.button)
    public void onCheckVersion() {
       /* params = new RequestParams();
        String code = "";
        try {
            code = SHA.getSHA1(MD5Utils.computeDigest(String.valueOf(SystemTool.getAppVersionCode(aty)).replaceAll(" ", "")));
            JCLoger.debug(code);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        params.put("verifyCode", code);
        params.put("AppToken", "");
        params.put("mt", "Android");
        params.put("channel", "Channel_Default");
        params.put("mk", "867391035393961");
        params.put("versionCode", String.valueOf(SystemTool.getAppVersionCode(aty)));

        RequestCenter.getRequest(URLConfig.CHECK_VERSION, params, new DisposeDataListener() {
            @Override
            public void onSuccess(int errCode, String errMessage, Object responseObj) {
                Checkversion checkversion = (Checkversion) responseObj;
                JCLoger.debug(errCode + "----" + errMessage);
                JCLoger.debug(checkversion.getVersionCode()+"---"+checkversion.getVersionName()+"----"+checkversion.getVersionUrl());
            }

            @Override
            public void onFailure(int errCode, String errMessage) {
                toast(errCode + "----" + errMessage);
            }
        }, Checkversion.class);*/
    }

    @OnClick(R.id.button2)
    public void intoPlugin() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huanju.chajiandemo", "com.huanju.chajiandemo.MainActivity"));
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        loadPlugin(context);
    }

    private void loadPlugin(final Context context) {
        try {
            new Thread() {
                @Override
                public void run() {
                    //创建一个属于我们自己插件的ClassLoader，我们分析过只能使用DexClassLoader
                    String cachePath = context.getCacheDir().getAbsolutePath();
                    String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/plugin.apk";
                    DexClassLoader mClassLoader = new DexClassLoader(apkPath, cachePath, cachePath, context.getClassLoader());
                    MyHookHelper.inject(mClassLoader);
                    try {
                        AMSHookHelper.hookActivityManagerNative();
                        AMSHookHelper.hookActivityThreadHandler();
                    } catch (Exception e) {
                        JCLoger.debug("加载异常了 = " + e.getMessage());
                        e.printStackTrace();
                    }
                    JCLoger.debug("加载完成....");

                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
