package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 明明大美女 on 2016/10/17.
 */

public class HideTextItem extends TextView {

    public HideTextItem(Context context) {
        super(context);
    }

    public HideTextItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    boolean isHide ;
    private void init() {
        setOnClickListener(v -> {
            if(isHide) {
                v.animate().translationX(24).setDuration(500).start();
            } else {
                v.animate().translationX(240).setDuration(500).start();
            }
            isHide = !isHide;
        });
    }

    public HideTextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


}
