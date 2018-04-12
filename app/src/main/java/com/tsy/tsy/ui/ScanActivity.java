package com.tsy.tsy.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;
import com.tsy.tsy.zbar.camera.CameraManager;
import com.tsy.tsy.zbar.decode.MainHandler;
import com.tsy.tsy.zbar.utils.BeepManager;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tsy.base.uitls.JCLoger;

/**
 * 扫描一维码Activity
 * Created by Jocerly on 2017/3/16.
 */
public class ScanActivity extends BaseActivity implements SurfaceHolder.Callback {
    @BindView(R.id.capture_preview)
    SurfaceView scanPreview;
    @BindView(R.id.capture_mask_top)
    ImageView captureMaskTop;
    @BindView(R.id.capture_scan_line)
    ImageView captureScanLine;
    @BindView(R.id.capture_crop_view)
    RelativeLayout scanCropView;
    @BindView(R.id.textLight)
    TextView textLight;
    @BindView(R.id.capture_mask_bottom)
    ImageView captureMaskBottom;
    @BindView(R.id.capture_mask_left)
    ImageView captureMaskLeft;
    @BindView(R.id.capture_mask_right)
    ImageView captureMaskRight;
    @BindView(R.id.capture_container)
    RelativeLayout scanContainer;

    private MainHandler mainHandler;
    private SurfaceHolder mHolder;
    private CameraManager mCameraManager;
    private BeepManager beepManager;
    private boolean isCameraOpen = true;
    private Rect mCropRect = null;

    private boolean isHasSurface = false;
    private Thread thread = null;

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("扫描商品条码");
        MobclickAgent.onPause(aty);
    }

    public Handler getHandler() {
        return mainHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void initData() {
        isHasSurface = false;
        beepManager = new BeepManager(this);
    }

    @OnClick(R.id.textLight)
    public void doLight(){
        mCameraManager.switchLight();
        textLight.setBackgroundResource(mCameraManager.getIsOpenLight() ? R.mipmap.light : R.mipmap.light_not);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseCamera();
        //关闭相机
        if (mCameraManager != null) {
            mCameraManager.closeDriver();
            mCameraManager = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //remove SurfaceCallback
        //关闭声音
        if (null != beepManager) {
            JCLoger.debug("releaseCamera: beepManager release");
            beepManager.close();
            beepManager = null;
        }
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        if (thread != null) {
            thread.isInterrupted();
            thread = null;
        }
    }

    //region 初始化和回收相关资源
    private void initCamera(SurfaceHolder surfaceHolder) {
        mainHandler = null;
        try {
            mCameraManager.openDriver(surfaceHolder);
            if (mainHandler == null) {
                mainHandler = new MainHandler(this, mCameraManager);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            JCLoger.debug("相机被占用", ioe);
        } catch (RuntimeException e) {
            e.printStackTrace();
            isCameraOpen = false;
            textLight.setEnabled(false);
        }
    }

    private void resumeCamera() {
        if (mCameraManager == null) {
            mCameraManager = new CameraManager(getApplication());
        }
        mHolder = scanPreview.getHolder();
        if (isHasSurface) {
            thread = new Thread(new OpenCameraRunnable(aty, mHolder));
            thread.start();
        } else {
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    @Override
    public void onResume() {
        resumeCamera();
        super.onResume();
    }

    private void releaseCamera() {
        if (null != mainHandler) {
            //关闭聚焦,停止预览,清空预览回调,quit子线程looper
            mainHandler.quitSynchronously();
            mainHandler = null;
        }
    }

    //region 扫描结果
    public void checkResult(final String result) {
        if (beepManager != null) {
            beepManager.playBeepSoundAndVibrate();
        }
        MobclickAgent.onEvent(aty, "Goods_SearchScanDone");
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JCLoger.debug(result.trim());
                scanResult(result.trim());
            }
        }, 100);
    }

    /**
     * 扫描成功结果
     */
    public void scanResult(String result) {
        if (TextUtils.isEmpty(result)) {
            toast("识别错误");
            releaseCamera();
            resumeCamera();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("code", result);
        setResult(RESULT_OK, intent);
        finish();
    }

    //region  初始化截取的矩形区域
    public Rect initCrop() {
        int cameraWidth = 0;
        int cameraHeight = 0;
        if (null != mCameraManager) {
            cameraWidth = mCameraManager.getCameraResolution().y;
            cameraHeight = mCameraManager.getCameraResolution().x;
        }

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
        return new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //endregion

    //region SurfaceHolder Callback 回调方法
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            JCLoger.debug("*** 没有添加SurfaceHolder的Callback");
        }
        if (!isHasSurface) {
            isHasSurface = true;
            thread = new Thread(new OpenCameraRunnable(aty, holder));
            thread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        JCLoger.debug("surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
    }

    private class OpenCameraRunnable implements Runnable {
        private Activity activity;
        SurfaceHolder holder;

        public OpenCameraRunnable(Activity activity, SurfaceHolder holder) {
            this.activity = activity;
            this.holder = holder;
        }

        @Override
        public void run() {
            try {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initCamera(holder);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
