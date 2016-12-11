package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.flowerfat.initapp.base.CustomView;

/**
 * Created by 明明大美女 on 2016/11/2.
 * <p>
 * RecyclerView中，每个Item左侧的轴线
 * 其实该View可用图片代替，但是我有强迫症
 * <p>
 * 圆圈的大小木有去适配，心累，晚饭后再弄
 */

public class TimeAxisView extends CustomView {

    public static final int COLOR_DEFAULT = 0xCCAFAFAF;
    public static final int COLOR_CHOOSED = 0xFFF52D2F;

    private Bitmap iconBitmap;
    private Paint mPaint;
    private int axisColor = 0xCCAFAFAF;
    private int ringRadius = 20;
    private int circleRadius = 10;
    private int lineWidth = 6;

    public TimeAxisView(Context context) {
        super(context);
        init();
    }

    public TimeAxisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeAxisView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(axisColor);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawCircle(canvas);
        drawRing(canvas);
        drawIcon(canvas);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(COLOR_DEFAULT);
        canvas.drawLine(centerX, 0, centerX, centerY - ringRadius, mPaint);
        canvas.drawLine(centerX, centerY + ringRadius, centerX, centerY * 2, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        if (iconBitmap == null) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(axisColor);
            canvas.drawCircle(centerX, centerY, circleRadius, mPaint);
        }
    }

    private void drawRing(Canvas canvas) {
        if (iconBitmap == null) {
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerY, ringRadius, mPaint);
        }
    }

    public void drawIcon(Canvas canvas) {
        if (iconBitmap != null) {
            canvas.drawBitmap(iconBitmap,
                    centerX - iconBitmap.getHeight() / 2, centerY - iconBitmap.getHeight() / 2, mPaint);
        }
    }

    public void makeChoose(boolean isChoosed) {
        this.axisColor = isChoosed ? COLOR_CHOOSED : COLOR_DEFAULT;
        this.invalidate();
    }

    public void setAxisBitmap(@DrawableRes int iconRes) {
        ringRadius = 36;
        iconBitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        this.invalidate();
    }

    public void releaseBitmap() {
        if (iconBitmap != null)
            iconBitmap.recycle();
    }
}
