package com.tsy.tsy.ui;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.header.bezierradar.WaveView;
import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tsy.base.views.SiriWaveView;
import cn.tsy.base.views.SiriWaveViewNine;

public class WaveActivity extends BaseActivity {
    @BindView(R.id.sirWaveView)
    SiriWaveView sirWaveView;
    @BindView(R.id.sirWaveViewNie)
    SiriWaveViewNine sirWaveViewNie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
