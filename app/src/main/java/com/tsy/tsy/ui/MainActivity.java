package com.tsy.tsy.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;
import com.tsy.tsy.config.URLConfig;
import com.tsy.tsy.fragment.BuyFragment;
import com.tsy.tsy.fragment.HomeFragment;
import com.tsy.tsy.fragment.MemberCenterFragment;
import com.tsy.tsy.fragment.PublishFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.tsy.base.uitls.JCLoger;

/**
 * Created by jay on 2017/11/23.
 */

public class MainActivity extends BaseActivity {
    HomeFragment homeFragment;
    BuyFragment buyFragment;
    PublishFragment publishFragment;
    MemberCenterFragment memberCenterFragment;

    @BindView(R.id.llFragment)
    FrameLayout llFragment;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbBuy)
    RadioButton rbBuy;
    @BindView(R.id.rbPublish)
    RadioButton rbPublish;
    @BindView(R.id.rbMine)
    RadioButton rbMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        JCLoger.debug(URLConfig.URL_HOST + "------" + URLConfig.OnTag);
        initView();
        initData();
    }

    @Override
    public void initView() {
        JPushInterface.resumePush(getApplicationContext());
    }

    @Override
    public void initData() {
        showHomeFragment();
    }

    @OnClick(R.id.rbHome)
    public void showHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        changeFragment(llFragment, homeFragment);
    }

    @OnClick(R.id.rbBuy)
    public void showBuyFragment() {
        if (buyFragment == null) {
            buyFragment = new BuyFragment();
        }
        changeFragment(llFragment, buyFragment);
    }

    @OnClick(R.id.rbPublish)
    public void showPublishFragment() {
        if (publishFragment == null) {
            publishFragment = new PublishFragment();
        }
        changeFragment(llFragment, publishFragment);
    }

    @OnClick(R.id.rbMine)
    public void showMemberCenterFragment() {
        if (memberCenterFragment == null) {
            memberCenterFragment = new MemberCenterFragment();
        }
        changeFragment(llFragment, memberCenterFragment);
    }
}
