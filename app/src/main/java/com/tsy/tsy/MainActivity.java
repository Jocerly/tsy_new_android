package com.tsy.tsy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.tsy.tsy.uitls.ImageUtils;

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


}
