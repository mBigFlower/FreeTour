package com.flowerfat.tomorrow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 明明大美女 on 2016/9/24.
 */

public abstract class ExpandLayout extends FrameLayout {

    private View mView;
    private View pressView;
    private View expandView;
    private boolean isExpand = true;

    public ExpandLayout(Context context) {
        this(context, null);
    }

    public ExpandLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ExpandLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = LayoutInflater.from(context).inflate(initLayoutRes(), this, true);
        init();
    }

    public abstract int initLayoutRes();

    public abstract int initPressRes();

    public abstract int initExpandRes();

    public abstract void main();



    private void init() {
        initTitle();
        initExpandLayout();
        main();
    }

    private void initTitle() {
        pressView = mView.findViewById(initPressRes());
        pressView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                expandView.setVisibility(isExpand ? View.GONE : View.VISIBLE);
                isExpand = !isExpand;
            }
        });
    }

    private void initExpandLayout() {
        expandView = mView.findViewById(initExpandRes());
    }


    public View getmView() {
        return mView;
    }

    public View getPressView() {
        return pressView;
    }

    public View getExpandView() {
        return expandView;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand){
        this.isExpand = isExpand;
        expandView.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }
}
