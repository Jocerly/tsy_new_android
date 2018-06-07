package cn.tsy.base.views.HeaderView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import cn.tsy.base.R;

/**
 * 自定义SmartRefreshLayout，的headerView。
 */
public class JClassicsHeader extends FrameLayout implements RefreshHeader {
    private CircleProgressBar mRotateView;
    private ProgressBar mProgressBar;
    public JClassicsHeader(Context context) {
        super(context);
        this.initView(context, null);
    }

    public JClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public JClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public JClassicsHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View header = LayoutInflater.from(context).inflate(R.layout.abs_header_layout, this);

        mRotateView = header.findViewById(R.id.progress_rotate);
        mProgressBar = header.findViewById(R.id.progress_loading);

        resetView();
    }

    private void resetView() {
        mRotateView.setVisibility(INVISIBLE);
        mProgressBar.setVisibility(INVISIBLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void startProgress() {
        mProgressBar.setVisibility(INVISIBLE);
        mRotateView.setVisibility(VISIBLE);
        mRotateView.reStart();

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mProgressBar.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        mProgressBar.setLayoutParams(params);
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        resetView();
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {
    }

    @Override
    public void onReleasing(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void onReleased(RefreshLayout layout, int height, int extendHeight) {
//        resetView();
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        completeProgress();
        return 500;//延迟500毫秒之后再弹回
    }

    private void completeProgress() {
        mProgressBar.setVisibility(INVISIBLE);
        mRotateView.setVisibility(VISIBLE);
        mRotateView.sucLoad();
    }

    @Override@Deprecated
    public void setPrimaryColors(@ColorInt int ... colors) {
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
                resetView();
            case PullDownToRefresh://开始刷新，预备中
                startProgress();
                break;
            case Refreshing:
            case RefreshReleased://正在刷新
                mProgressBar.setVisibility(VISIBLE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mProgressBar.getLayoutParams();
                params.setMargins(0, 0, 0, 15);//松手后页面有抖动，增加15像素的高度
                mProgressBar.setLayoutParams(params);
                mRotateView.setVisibility(INVISIBLE);
                break;
        }
    }
}
