package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.flowerfat.initapp.R;

/**
 * Created by 明明大美女 on 2016/9/12.
 */
public class TestPopup implements RevealBackgroundPopView.OnStateChangeListener{

    private View contentView ;

    RevealBackgroundPopView mAnimBackground;
    PopupWindow popupWindow ;

    public TestPopup(Context context) {
        contentView = LayoutInflater.from(context).inflate(
                R.layout.view_tour_item_more, null);

//        mAnimBackground = (RevealBackgroundView) contentView.findViewById(R.id.base_animBack);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

    }

    public void showAbove(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
//        mAnimBackground.setFillPaintColor(Color.RED);
//        mAnimBackground.setOnStateChangeListener(this);

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 100, 200);
//        mAnimBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                mAnimBackground.getViewTreeObserver().removeOnPreDrawListener(this);
//                mAnimBackground.startFromLocation(location);
//
//                // 动画开始
////                animStart();
//                return true;
//            }
//        });
    }

    @Override
    public void onStateChange(int state) {

    }

}
