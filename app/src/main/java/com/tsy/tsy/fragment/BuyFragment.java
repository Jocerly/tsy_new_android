package com.tsy.tsy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;

/**
 * Created by Jocerly on 2018/4/16.
 */

public class BuyFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, null);
        aty = getActivity();
        initView(view);
        initData(view);
        return view;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData(View view) {

    }
}
