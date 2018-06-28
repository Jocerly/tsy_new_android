package cn.tsy.base.views;

import android.app.AlarmManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import cn.tsy.base.R;

public class TimeDownView extends View {
    private static final String HH = "HH";
    private static final String MM = "mm";
    private static final String SS = "ss";

    private Context mContext;
    private Paint paint1, paint2;
    private String hh = "00", mm = "00", ss = "00", gap = ":";
    private RectF rectF1, rectF2, rectF3;
    private int txtW = 70;
    private int gapW = 30;
    private int baseLineY1, baseLineY2, baseLineY3;
    private long miao = 0;
    private Timer timer;
    private DateFormat df1;
    private DateFormat df2;
    private DateFormat df3;

    public TimeDownView(Context context) {
        super(context);
        initView(context);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setTextSize(context.getResources().getDimensionPixelOffset(R.dimen.font1));
        paint1.setTextAlign(Paint.Align.CENTER);

        paint2 = new Paint();
        paint2.setColor(mContext.getResources().getColor(R.color.wave_color_5));
        paint2.setTextSize(context.getResources().getDimensionPixelOffset(R.dimen.font2));
        paint2.setTextAlign(Paint.Align.CENTER);

        rectF1 = new RectF(0, 0, txtW, txtW);
        rectF2 = new RectF(txtW + gapW, 0, txtW * 2 + gapW, txtW);
        rectF3 = new RectF(txtW * 2 + gapW * 2, 0, txtW * 3 + gapW * 2, txtW);

        Paint.FontMetrics fontMetrics = paint1.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        baseLineY1 = (int) (rectF1.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        baseLineY2 = (int) (rectF2.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        baseLineY3 = (int) (rectF3.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式

        timer = new Timer();
        df1 = createDateFormat(HH);
        df2 = createDateFormat(MM);
        df3 = createDateFormat(SS);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF1, paint2);
        canvas.drawText(hh, rectF1.centerX(), baseLineY1, paint1);
        canvas.drawText(gap, rectF1.centerX() + txtW / 2 + gapW / 2, baseLineY1, paint2);

        canvas.drawRect(rectF2, paint2);
        canvas.drawText(mm, rectF2.centerX(), baseLineY2, paint1);
        canvas.drawText(gap, rectF2.centerX() + txtW / 2 + gapW / 2, baseLineY2, paint2);

        canvas.drawRect(rectF3, paint2);
        canvas.drawText(ss, rectF3.centerX(), baseLineY3, paint1);
    }

    public void startTime(long timeMiao) {
        if (timeMiao < 1000) {
            return;
        }
        miao = timeMiao + 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                miao -= 1000;
                if (miao < 0) {
                    miao = 0;
                    timer.cancel();
                }
                handler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }

    public void setTimeChange() {
        timer.cancel();
        timer = new Timer();
        startTime(miao);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    invalidate();
                    break;
            }
        }
    };

    @Override
    public void invalidate() {
        Date date = new Date(miao);
        hh = df1.format(date);
        mm = df2.format(date);
        ss = df3.format(date);
        super.invalidate();
    }

    private DateFormat createDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        TimeZone gmt = TimeZone.getTimeZone("GMT");//关键所在
        sdf.setTimeZone(gmt);
        sdf.setLenient(true);
        return sdf;
    }

}
