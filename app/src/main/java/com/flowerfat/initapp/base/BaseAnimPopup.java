package com.flowerfat.initapp.base;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.ui.view.RevealBackgroundPopView;

/**
 * Created by 明明大美女 on 2016/10/11.
 *
 * 这个类还不是很好用，以后有需要再来完善。
 * 关于动画的方法，可以参考最下面注释的类的操作，比现在的仅仅背景渐变要好
 */

public abstract class BaseAnimPopup implements RevealBackgroundPopView.OnStateChangeListener {

    RevealBackgroundPopView mAnimBackground;
    public PopupWindow popupWindow;

    View mainView;
    public View contentView;
    public Context context;
    int viewWidth, viewHeight;

    public abstract int initLayout();

    public BaseAnimPopup(Context context) {
        this.context = context;
        mainView = LayoutInflater.from(context).inflate(R.layout.layout_base_popup, null);
        mAnimBackground = (RevealBackgroundPopView) mainView.findViewById(R.id.base_animBack);

        initUserLayout();
        initPopup();
    }

    private void initUserLayout() {
        contentView = LayoutInflater.from(context).inflate(
                initLayout(), null);
        contentView.setAlpha(0);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        viewWidth = contentView.getMeasuredWidth();
        viewHeight = contentView.getMeasuredHeight();
        ((FrameLayout) mainView).addView(contentView);
    }

    private void initPopup() {
        popupWindow = new PopupWindow(mainView, viewWidth, viewHeight, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void showAtLocation(View view) {
        final int[] vLocation = new int[2];
        view.getLocationOnScreen(vLocation);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, vLocation[0] - viewWidth, vLocation[1] - viewHeight);
        mAnimBackground.setOnStateChangeListener(this);
        mAnimBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mAnimBackground.getViewTreeObserver().removeOnPreDrawListener(this);
//                mAnimBackground.startFromLocation();
                // 动画开始
                animStart();
                return true;
            }
        });
    }

    protected abstract void animStart();

    public void dismiss() {
//        mAnimBackground.finishFromLocation();
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackgroundPopView.STATE_START_FINISHED) {
            contentView.animate().alpha(1).setDuration(200).start();
        } else if (state == RevealBackgroundPopView.STATE_END_FINISHED) {
            popupWindow.dismiss();
        }
    }

    public View getMainView() {
        return mainView;
    }



}
