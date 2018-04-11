package com.tsy.tsy;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsy.tsy.okhttp.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tsy.base.okhttp.DisposeDataListener;

/**
 * Created by jay on 2017/11/23.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.text1)
    TextView message;
    @BindView(R.id.container)
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RequestCenter.postRequest("https://www.baidu.com", null, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object responseObj) {

            }
        }, String.class);
    }

}
