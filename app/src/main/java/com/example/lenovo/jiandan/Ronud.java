package com.example.lenovo.jiandan;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by lenovo on 2016/1/24.
 */
public class Ronud extends View{

    private Paint outPaint;
    private Paint twoPaint;
    private int mWidth = 700;
    private int mHeight = 700;
    private int radius = 350;
    private int currentValue;
    public Ronud(Context context) {
        super(context);
        init();
    }

    public Ronud(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Ronud(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outPaint = new Paint();
        twoPaint = new Paint();
        twoPaint.setStrokeWidth(3);
        twoPaint.setTextSize(40);
        twoPaint.setColor(Color.GRAY);
        twoPaint.setTextAlign(Paint.Align.CENTER);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeWidth(5);
        outPaint.setAntiAlias(true);
        outPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int a = (int) (2 * radius - 45);
        outPaint.setColor(Color.GRAY);
        for ( int i=0;i<=180; i++) {
            canvas.save();
            canvas.rotate(i * 2, radius, radius);
            canvas.drawLine(a, radius, 2 * radius, radius, outPaint);
            canvas.restore();
        }
        outPaint.setColor(Color.WHITE);
        twoPaint.setColor(Color.RED);
        canvas.drawCircle(2*radius-70,radius,10,twoPaint);
        for ( int i = 0;i<=currentValue; i++) {
            canvas.save();
            canvas.rotate(i * 2 - 90, radius, radius);
            canvas.drawLine(a, radius, 2 * radius, radius, outPaint);
            canvas.restore();
        }
        twoPaint.setColor(Color.GRAY);
        canvas.drawText("已步行"+currentValue+"步",radius,radius-6,twoPaint);

    }

    public void startAnim(){
        final ValueAnimator anim = ValueAnimator.ofInt(0,60);
        anim.setDuration(1500);
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
        setMeasuredDimension(width,height);
    }
}
