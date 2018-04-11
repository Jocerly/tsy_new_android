package com.tsy.tsy;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jay on 2017/11/23.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.image)
    ImageView image;


    private static final int quality = 20;

    /**
     * 图片存放根目录
     */
    private final String mImageRootDir = Environment
            .getExternalStorageDirectory().getPath() + "/jpeg_picture/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 压缩后保存临时文件目录
        File tempFile = new File(mImageRootDir);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
    }

    @OnClick(R.id.btn1)
    public void doBtn1() {

    }

    @OnClick(R.id.btn2)
    public void doBtn2() {

    }

    @OnClick(R.id.btn3)
    public void doBtn3() {

    }

    @OnClick(R.id.btn4)
    public void doBtn4() {

    }


}
