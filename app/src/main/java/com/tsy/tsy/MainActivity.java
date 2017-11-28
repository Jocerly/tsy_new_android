package com.tsy.tsy;

import android.os.Bundle;

import com.tsy.tsy.ui.base.BaseActivity;

/**
 * Created by jay on 2017/11/23.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(true);
    }
}
