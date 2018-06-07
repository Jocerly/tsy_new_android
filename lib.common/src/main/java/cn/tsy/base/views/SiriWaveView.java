package cn.tsy.base.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.tsy.base.R;
import cn.tsy.base.uitls.WaveUtil;

public class SiriWaveView extends View {

    private double phase = 0;
    private boolean run = false;
    private int ratio;
    private int width;
    private int width_2;
    private int width_4;
    private int height;
    private int height_2;
    private float MAX;
    private float amplitude;
    private float speed;
    private int frequency;

    public SiriWaveView(Context context) {
        super(context);
        init();
    }

    public SiriWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ratio = 1;
        width = ratio * WaveUtil.getScreenWidth(getContext());
        height = width / 4;
        width_2 = width / 2;
        width_4 = width / 4;
        height_2 = height / 2;
        MAX = (height_2) - 4;
        amplitude = 1;
        speed = 0.2f;
        frequency = 6;
        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        run = true;
        if (run == false)
            return;
        phase = (phase + Math.PI * speed) % (2f * Math.PI);
        Log.i("zeng", "phase:" + phase);
        clear(canvas);
        _drawLine(canvas, -2, R.color.wave_color_1);
        _drawLine(canvas, -6, R.color.wave_color_1);
        _drawLine(canvas, 4, R.color.wave_color_1);
        _drawLine(canvas, 2, R.color.wave_color_1);
        _drawLine(canvas, 1, R.color.wave_color_1);
        postInvalidateDelayed(20);
    }

    public void _drawLine(Canvas canvas, int attenuation, int color) {
        Path path = new Path();
        path.moveTo(0, height / 2);
        Paint paint = new Paint();
        paint.reset();
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);// 设置空心
        if (attenuation == 1) {
            paint.setStrokeWidth(2);
        }
        paint.setColor(getResources().getColor(color));
        float i = -2f;
        while ((i += 0.01) <= 2f) {
            float y = _ypos(i, attenuation);
            if (Math.abs(i) >= 1.90f)
                y = height_2;
            path.lineTo(_xpos(i), y);
        }
        canvas.drawPath(path, paint);
    }

    public float _xpos(float i) {
        return width_2 + i * width_4;
    }

    public float _ypos(float i, int attenuation) {
        float att = (MAX * amplitude) / attenuation;
        return (float) (height_2 + _globAttFunc(i) * att * Math.sin(frequency * i - phase));
    }

    public double _globAttFunc(float x) {
        return Math.pow(4 / (4 + Math.pow(x, 4)), 4);
    }

    public void clear(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawPaint(paint);
    }

    public void start() {
        phase = 0;
        invalidate();
    }

}
