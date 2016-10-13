package com.flowerfat.initapp.base;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.ui.view.RevealBackgroundView;

/**
 * Created by 明明大美女 on 2016/10/11.
 */

public abstract class BaseAnimPopup implements RevealBackgroundView.OnStateChangeListener {

    RevealBackgroundView mAnimBackground;
    public PopupWindow popupWindow;

    View mainView, contentView;
    public Context context;

    public abstract int initLayout();

    public BaseAnimPopup(Context context) {
        this.context = context;
        mainView = LayoutInflater.from(context).inflate(
                R.layout.layout_base_popup, null);

        mAnimBackground = (RevealBackgroundView) mainView.findViewById(R.id.base_animBack);

        popupWindow = new PopupWindow(mainView,
                ViewGroup.LayoutParams.WRAP_CONTENT, 500, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        contentView = LayoutInflater.from(context).inflate(
                initLayout(), null);
        ((FrameLayout) mainView).addView(contentView);
        contentView.setVisibility(View.GONE);
    }

    public void showAsDropDown(View view) {
        popupWindow.showAsDropDown(view);
//        mAnimBackground.setFillPaintColor(Color.RED);
        mAnimBackground.setOnStateChangeListener(this);

        mAnimBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mAnimBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                mAnimBackground.startFromLocation(new int[]{300, 300});
                // 动画开始
//                animStart();
                return true;
            }
        });
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackgroundView.STATE_FINISHED) {
            contentView.setVisibility(View.VISIBLE);
        }
    }

    public View getMainView() {
        return mainView;
    }
}
