package com.example.lenovo.jiandan;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by lenovo on 2016/1/26.
 */
public class Loading extends View {

    private int mWidth = 700;
    private int mHeight= 700;
    private int centerX= 350;
    private int centerY = 350;
    private int radius = 320;
    private Canvas mCanvas;
    private int currentValue = 0;
    private Bitmap mBitmap;
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Paint onePaint,twoPaint,textPaint;
    public Loading(Context context) {
        super(context);
        init();
    }

    public Loading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Loading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        onePaint = new Paint();
        onePaint.setStyle(Paint.Style.FILL);
        onePaint.setColor(Color.GRAY);
        onePaint.setAntiAlias(true);

        twoPaint = new Paint();
        twoPaint.setStyle(Paint.Style.FILL);
        twoPaint.setColor(Color.parseColor("#33b5e5"));
        twoPaint.setAntiAlias(true);

        textPaint  = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode != MeasureSpec.EXACTLY){
            width = mWidth;
        }
        if(heightMode!= MeasureSpec.EXACTLY){
            height = mHeight;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas.drawCircle(centerX, centerY, radius, onePaint);
        twoPaint.setXfermode(mMode);

        float height = (float) (centerY + radius - currentValue/100.0*2*radius);
        mCanvas.drawRect(centerX - radius, height , centerX + radius, centerY + radius, twoPaint);
        mCanvas.drawText("已下载"+currentValue+"%",centerX,centerY+15,textPaint);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void startAnim(){
        final ValueAnimator anim = ValueAnimator.ofInt(0,60);
        anim.setDuration(2000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                currentValue = value;
                invalidate();
            }
        });
        anim.start();
    }
}
