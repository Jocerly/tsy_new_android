package com.tsy.tsy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tsy.base.views.TimeDownView;

/**
 * Created by Jocerly on 2018/4/16.
 */

public class MemberCenterFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.timeDownView)
    TimeDownView timeDownView;

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
                refreshLayout.finishLoadMore(1000, true, false);
            }
        });
    }

    @Override
    public void initData(View view) {
        timeDownView.startTime();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
