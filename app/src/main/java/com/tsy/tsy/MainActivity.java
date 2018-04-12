package com.tsy.tsy;

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
import com.tsy.tsy.uitls.ImageUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tsy.base.uitls.JCLoger;

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

    private static final int quality = 20;

    /**
     * 图片存放根目录
     */
    private final String mImageRootDir = Environment.getExternalStorageDirectory().getPath() + "/jpeg_picture/";

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

    private List<LocalMedia> mLocalMedia;

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
