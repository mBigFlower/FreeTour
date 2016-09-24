package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

import com.flowerfat.initapp.R;

/**
 * Created by 明明大美女 on 2016/9/12.
 */
public class ChoicePopup extends PopupWindow implements RevealBackgroundView.OnStateChangeListener{

    RevealBackgroundView mAnimBackground;
    PopupWindow popupWindow ;

    public ChoicePopup(Context context) {
        final View contentView = LayoutInflater.from(context).inflate(
                R.layout.layout_choice_popup, null);

        mAnimBackground = (RevealBackgroundView) contentView.findViewById(R.id.base_animBack);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void showAsDropDown(View view) {
        popupWindow.showAsDropDown(view);
        mAnimBackground.setFillPaintColor(Color.RED);
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

    @Override
    public void onStateChange(int state) {

    }

}
