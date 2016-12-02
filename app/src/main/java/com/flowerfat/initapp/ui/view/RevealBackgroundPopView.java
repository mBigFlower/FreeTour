package com.flowerfat.initapp.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Miroslaw Stanek on 18.01.15.
 */
public class RevealBackgroundPopView extends View {
    public static final int STATE_NOT_STARTED = 0;
    public static final int STATE_FILL_STARTED = 1;
    public static final int STATE_START_FINISHED = 2;
    public static final int STATE_END_FINISHED = 3;
    public static final int STATE_ACT_FINISH = 4;

    private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();
    public static final int FILL_TIME = 400;
    private static final int DELAY_TIME = 100;

    private int state = STATE_NOT_STARTED;

    private Paint fillPaint;
    private int currentRadius;
    ObjectAnimator revealAnimator;

    private int startLocationX;
    private int startLocationY;


    private OnStateChangeListener onStateChangeListener;

    public RevealBackgroundPopView(Context context) {
        super(context);
        init();
    }

    public RevealBackgroundPopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RevealBackgroundPopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RevealBackgroundPopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.WHITE);
    }

    public void setFillPaintColor(int color) {
        fillPaint.setColor(color);
    }

    public void startFromLocation() {
        changeState(STATE_FILL_STARTED);
        startLocationX = getWidth();
        startLocationY = getHeight();
        revealAnimator = ObjectAnimator.ofInt(this, "currentRadius", 0, getWidth() + getHeight()).setDuration(FILL_TIME);
        revealAnimator.setInterpolator(INTERPOLATOR);
        revealAnimator.setStartDelay(DELAY_TIME);
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                changeState(STATE_START_FINISHED);
            }
        });
        revealAnimator.start();
    }

    public void finishFromLocation(){
        changeState(STATE_FILL_STARTED);
        revealAnimator = ObjectAnimator.ofInt(this, "currentRadius", getWidth() + getHeight(), 0).setDuration(FILL_TIME);
        revealAnimator.setInterpolator(INTERPOLATOR);
        revealAnimator.setStartDelay(DELAY_TIME);
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                changeState(STATE_END_FINISHED);
            }
        });
        revealAnimator.start();
    }

    public void setToFinishedFrame() {
        changeState(STATE_END_FINISHED);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (state == STATE_START_FINISHED) {
            fillPaint.getColor();
            canvas.drawRect(0, 0, 0, 0, fillPaint);
        } else {
            canvas.drawCircle(startLocationX, startLocationY, currentRadius, fillPaint);
        }
    }

    private void changeState(int state) {
        if (this.state == state) {
            return;
        }

        this.state = state;
        if (onStateChangeListener != null) {
            onStateChangeListener.onStateChange(state);
        }
    }

    public void setCurrentRadius(int radius) {
        this.currentRadius = radius;
        invalidate();
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
    public static interface OnStateChangeListener {
        void onStateChange(int state);
    }
}
