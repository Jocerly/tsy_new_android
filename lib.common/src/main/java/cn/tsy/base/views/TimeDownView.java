package cn.tsy.base.views;

import android.app.AlarmManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import cn.tsy.base.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class TimeDownView extends RelativeLayout {
    private Context mContext;
    private static final String TIME = "HH:mm:ss";
    private String time = "00:00:00";
    private DateFormat df;
    private TextView mText;
    private CountDownTimer downTimer;

    private int txtColor, txtBg, txtSize;

    public TimeDownView(Context context) {
        super(context);
        initView(context, null);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        txtColor = ta.getInt(R.styleable.TimeView_txt_color, Color.RED);
        txtBg = ta.getInt(R.styleable.TimeView_txt_bg, Color.WHITE);
        txtSize = ta.getInt(R.styleable.TimeView_txt_size, 13);

        mText = new TextView(mContext);
        mText.setTextColor(txtColor);
        mText.setTextSize(txtSize);
        mText.setBackgroundColor(txtBg);
        mText.setText(time);
        addView(mText);

        df = createDateFormat(TIME);
    }

    public void startTime(long timeMiao) {
        if (timeMiao < 1000) {
            return;
        }
        downTimer = new CountDownTimer(timeMiao, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                invalidate(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                invalidate(0);
            }
        }.start();
    }

    public void invalidate(long miao) {
        Date date = new Date(miao);
        mText.setText(df.format(date));
    }

    private DateFormat createDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        TimeZone gmt = TimeZone.getTimeZone("GMT");//关键所在
        sdf.setTimeZone(gmt);
        sdf.setLenient(true);
        return sdf;
    }

}
