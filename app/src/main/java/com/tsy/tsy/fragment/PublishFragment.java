package com.tsy.tsy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tsy.base.views.CircleProgressBar;

/**
 * Created by Jocerly on 2018/4/16.
 */

public class PublishFragment extends BaseFragment {


    @BindView(R.id.button3)
    Button button3;
    Unbinder unbinder;
    @BindView(R.id.progress1)
    CircleProgressBar progress1;
    @BindView(R.id.progress2)
    ProgressBar progress2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish, null);
        aty = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData(view);
        return view;
    }

    @Override
    public void initView(View view) {
        progress1.setOnLoadListener(new CircleProgressBar.OnLoadListener() {
            @Override
            public void sucLoad() {
                progress1.setVisibility(View.GONE);
                progress2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void initData(View view) {

    }

    @OnClick(R.id.button3)
    public void onButton() {
        progress2.setVisibility(View.GONE);
        progress1.setVisibility(View.VISIBLE);
        progress1.reStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
