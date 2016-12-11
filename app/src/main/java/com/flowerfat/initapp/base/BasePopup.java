package com.flowerfat.initapp.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.content.ContentValues.TAG;

/**
 * Created by 明明大美女 on 2016/12/8.
 *
 * This basePopup is copy from the prj below 这个类是copy下面这个链接的
 *      https://github.com/kakajika/RelativePopupWindow
 * I do some change for it , which make the popup's shape is rightEnum 我做了些更改，为了布局大小正确
 *
 * Notice :
 *  1. the method 'BasePopup(View contentView)'  is important !
 *  2. Please sure that your layout's children are same style 请确保你布局的控件样式相同
 *     Or use match_parent for the width 或者宽度用撑满，不然布局可能会很丑
 *     但是，只要你用相同的item就没问题了~
 *
 */

public class BasePopup extends PopupWindow {
    // the layout's width and height
    int measuredLayoutW, measuredLayoutH;

    /* 枚举  */
    @IntDef({
            VerticalPosition.CENTER,
            VerticalPosition.ABOVE,
            VerticalPosition.BELOW,
            VerticalPosition.ALIGN_TOP,
            VerticalPosition.ALIGN_BOTTOM,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface VerticalPosition {
        int CENTER = 0;
        int ABOVE = 1;
        int BELOW = 2;
        int ALIGN_TOP = 3;
        int ALIGN_BOTTOM = 4;
    }

    @IntDef({
            HorizontalPosition.CENTER,
            HorizontalPosition.LEFT,
            HorizontalPosition.RIGHT,
            HorizontalPosition.ALIGN_LEFT,
            HorizontalPosition.ALIGN_RIGHT,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizontalPosition {
        int CENTER = 0;
        int LEFT = 1;
        int RIGHT = 2;
        int ALIGN_LEFT = 3;
        int ALIGN_RIGHT = 4;
    }
    /* the base constructor 基本的构造方法*/
    public BasePopup(Context context) {
        super(context);
        init();
    }

    public BasePopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasePopup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    /* the strong constructor 升级版构造方法*/
    public BasePopup(Context context, @LayoutRes int resLayout) {
        this(LayoutInflater.from(context).inflate(resLayout, null));
    }

    public BasePopup(View contentView) {
        // 这句话，很关键，有了它，下面就能测到宽高了！！！ This is very important !
        setContentView(contentView);
        measureLayout();
        init();
    }

    /**
     * 重新测量布局的高宽
     * 当布局变化时，在popup显示之前调用这个函数
     * reMeasure the layout's width and height.
     * if your layout is changed, please use this before the popup show
     */
    public void measureLayout(){
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        measuredLayoutW = getContentView().getMeasuredWidth();
        measuredLayoutH = getContentView().getMeasuredHeight();
        setWidth(measuredLayoutW);
        setHeight(measuredLayoutH);
        Log.d(TAG, "measureLayout: width-"+measuredLayoutW +" height-"+measuredLayoutH);
    }

    public BasePopup init(){
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return this ;
    }


    /**
     * Show at relative position to anchor View.
     * @param anchor Anchor View
     * @param vertPos Vertical Position Flag
     * @param horizPos Horizontal Position Flag
     */
    public void showOnAnchor(@NonNull View anchor, @VerticalPosition int vertPos, @HorizontalPosition int horizPos) {
        showOnAnchor(anchor, vertPos, horizPos, 0, 0);
    }

    /**
     * Show at relative position to anchor View with translation.
     * @param anchor Anchor View
     * @param vertPos Vertical Position Flag
     * @param horizPos Horizontal Position Flag
     * @param x Translation X
     * @param y Translation Y
     */
    public void showOnAnchor(@NonNull View anchor, @VerticalPosition int vertPos, @HorizontalPosition int horizPos, int x, int y) {
        switch (vertPos) {
            case VerticalPosition.ABOVE:
                y -= measuredLayoutH + anchor.getHeight();
                break;
            case VerticalPosition.ALIGN_BOTTOM:
                y -= measuredLayoutH;
                break;
            case VerticalPosition.CENTER:
                y -= anchor.getHeight()/2 + measuredLayoutH/2;
                break;
            case VerticalPosition.ALIGN_TOP:
                y -= anchor.getHeight();
                break;
            case VerticalPosition.BELOW:
                // Default position.
                break;
        }
        switch (horizPos) {
            case HorizontalPosition.LEFT:
                x -= measuredLayoutW;
                break;
            case HorizontalPosition.ALIGN_RIGHT:
                x -= measuredLayoutW - anchor.getWidth();
                break;
            case HorizontalPosition.CENTER:
                x += anchor.getWidth()/2 - measuredLayoutW/2;
                break;
            case HorizontalPosition.ALIGN_LEFT:
                // Default position.
                break;
            case HorizontalPosition.RIGHT:
                x += anchor.getWidth();
                break;
        }
        PopupWindowCompat.showAsDropDown(this, anchor, x, y, Gravity.NO_GRAVITY);
    }
}
