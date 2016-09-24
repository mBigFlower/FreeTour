package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.flowerfat.initapp.R;
import com.flowerfat.tomorrow.ui.view.ExpandLayout;

/**
 * Created by 明明大美女 on 2016/9/24.
 */

public class CheckExpandView extends ExpandLayout {

    public CheckExpandView(Context context) {
        super(context);
    }

    public CheckExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int initLayoutRes() {
        return R.layout.view_checkbox_list;
    }

    @Override
    public int initPressRes() {
        return R.id.view_check_expand_press;
    }

    @Override
    public int initExpandRes() {
        return R.id.view_check_expand_layout;
    }
}
