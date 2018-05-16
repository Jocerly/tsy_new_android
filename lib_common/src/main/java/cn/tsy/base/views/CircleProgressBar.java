package cn.tsy.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.tsy.base.R;

/**
 * 圆形进度加载
 * Created by zxy on 2018/4/27.
 */

public class CircleProgressBar extends View {

    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private RectF mRect;
    private int mProgress = 0;
    //目标值，想改多少就改多少
    private int mTargetProgress = 100;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;
    //attrs
    private boolean isShowTxt = false;
    private int mInitCircleColor;
    private int mCircleColor;
    private float mStrokeWidth = 5;//圆环宽度
    private float mRadius = 45;//园半径
    private float mHalfStrokeWidth;

    private OnLoadListener onLoadListener;


    public CircleProgressBar(Context context) {
        super(context);
        init(null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    //完成相关参数初始化
    private void init(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgBar, 0, 0);
        if (arr != null) {
            isShowTxt = arr.getBoolean(R.styleable.CircleProgBar_is_show_txt, false);
            mInitCircleColor = arr.getColor(R.styleable.CircleProgBar_circle_color_init, Color.RED);
            mCircleColor = arr.getColor(R.styleable.CircleProgBar_circle_color, Color.RED);
            mRadius = arr.getInt(R.styleable.CircleProgBar_circle_radiu, 45);
            mStrokeWidth = arr.getInt(R.styleable.CircleProgBar_circle_radiu, 5);
            mHalfStrokeWidth = mStrokeWidth / 2;
        }

        mBackPaint = new Paint();
        mBackPaint.setColor(mInitCircleColor);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(mCircleColor);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);

        if (isShowTxt) {
            mTextPaint = new Paint();
            mTextPaint.setColor(Color.GREEN);
            mTextPaint.setAntiAlias(true);
            mTextPaint.setTextSize(80);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
        }
    }

    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    public void reStart() {
        mProgress = 0;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initRect();
        float angle = mProgress / (float) mMax * 360;
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
        if (isShowTxt) {
            canvas.drawText(mProgress + "%", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth, mTextPaint);
        }
        if (mProgress < mTargetProgress) {
            mProgress += 1;
            invalidate();
        } else if (mProgress == mTargetProgress) {
            if (onLoadListener != null) {
                onLoadListener.sucLoad();
            }
        }
    }

    public int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            //自己计算
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }

        return result;
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }


    public interface OnLoadListener {
        void sucLoad();
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
}
