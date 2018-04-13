package com.tsy.tsy.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;
import com.tsy.tsy.okhttp.RequestCenter;
import com.tsy.tsy.uitls.ImageUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tsy.base.okhttp.DisposeDownlaodListener;
import cn.tsy.base.uitls.JCLoger;
import cn.tsy.base.views.NumberProgressBar;
import cn.tsy.base.views.SwitchButton;

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
    ImageView mImage;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.switchButton)
    SwitchButton switchButton;
    @BindView(R.id.selectPic)
    Button selectPic;
    @BindView(R.id.number_progress_bar)
    NumberProgressBar numberProgressBar;

    private static final int quality = 20;
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;

    /**
     * 图片存放根目录
     */
    private final String mImageRootDir = Environment.getExternalStorageDirectory().getPath() + "/jpeg_picture/";
    @BindView(R.id.btnFile)
    Button btnFile;

    private List<LocalMedia> mLocalMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @OnClick(R.id.btn1)
    public void doBtn1() {//尺寸 质量 libjpeg结合压缩
        new Thread(new Runnable() {
            @Override
            public void run() {
                final File afterCompressImgFile = new File(mImageRootDir
                        + "/结合终极压缩666.jpg");

                //先尺寸质量压缩后在用jni libjpeg压缩   (先保证SD卡目录下/jpeg_picture/temp.jpg存在)
                String tempCompressImgPath = mImageRootDir + File.separator + "temp.jpg";
                ImageUtils.compressBitmap(tempCompressImgPath, afterCompressImgFile.getPath());

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImage.setImageBitmap(BitmapFactory
                                .decodeFile(afterCompressImgFile.getPath()));
                    }
                });
            }
        }).start();
    }

    @OnClick(R.id.btn2)
    public void doBtn2() {//直接jni libjpeg压缩
        new Thread(new Runnable() {

            @Override
            public void run() {
                final File afterCompressImgFile = new File(mImageRootDir
                        + "/" + quality + "直接终极压缩666.jpg");

                String tempCompressImgPath = mImageRootDir + File.separator + "temp.jpg";//事先准备好的sd卡目录下的图片
                //直接使用jni libjpeg压缩
                Bitmap bitmap = BitmapFactory.decodeFile(tempCompressImgPath);
                String codeString = ImageUtils.compressBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), quality, afterCompressImgFile.getAbsolutePath().getBytes(), true);
                Log.e("code", "code " + codeString);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImage.setImageBitmap(BitmapFactory
                                .decodeFile(afterCompressImgFile.getPath()));
                    }
                });
            }
        }).start();
    }

    @OnClick(R.id.btn3)
    public void doBtn3() {//原图
        JCLoger.debug("原图.....");
        String tempCompressImgPath = mImageRootDir + File.separator + "temp.jpg";
        mImage.setImageBitmap(BitmapFactory
                .decodeFile(tempCompressImgPath));
    }

    @OnClick(R.id.btn4)
    public void doBtn4() {//压缩后
        final File afterCompressImgFile = new File(mImageRootDir
                + "/终极压缩666.jpg");
        mImage.setImageBitmap(BitmapFactory
                .decodeFile(afterCompressImgFile.getPath()));
    }

    @OnClick(R.id.btn5)
    public void doBtn5() {//扫码
        showActivity(aty, ScanActivity.class);
    }

    @OnClick(R.id.btnFile)
    public void downloadFile() {
        RequestCenter.downloadFileRequest("http://img.zcool.cn/community/010f87596f13e6a8012193a363df45.jpg@1280w_1l_2o_100sh.jpg",
                mImageRootDir, new DisposeDownlaodListener() {
                    @Override
                    public void onDownloadSuccess(String path) {
                        JCLoger.debug(path);
                        toast("下载完成，文件：" + path);
                    }

                    @Override
                    public void onDownloading(int progress) {
                        numberProgressBar.setProgress(progress);
                    }

                    @Override
                    public void onDownloadFailed(Object responseObj) {
                        JCLoger.debug(responseObj.toString());
                    }
                });
    }

    @Override
    public void initView() {
        // 压缩后保存临时文件目录
        File tempFile = new File(mImageRootDir);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                JCLoger.debug(String.valueOf(isChecked));
            }
        });
//        numberProgressBar.setOnProgressBarListener(new OnProgressBarListener() {
//            @Override
//            public void onProgressChange(int current, int max) {
//                if (max == current) {
//                    toast("下载完成");
//                }
//            }
//        });
    }

    @Override
    public void initData() {
        switchButton.setChecked(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.selectPic)
    public void onViewClicked() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(3)
                .imageSpanCount(4)
                .previewImage(false)
                .isCamera(false)
                .isZoomAnim(false)
                .enableCrop(false)
                .compress(false)
                .glideOverride(160, 160)
                .isGif(false)
                .selectionMedia(mLocalMedia)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    mLocalMedia = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : mLocalMedia) {
                        Log.d("图片-----》", media.getPath());
                    }
                    break;
            }
        }
    }

}
