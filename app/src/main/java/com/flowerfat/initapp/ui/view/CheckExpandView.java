package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.TourSettingItem;
import com.flowerfat.tomorrow.ui.view.ExpandLayout;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void main() {

    }

    public void setTitle(String title) {
        ((TextView) getPressView()).setText(title);
    }

    public void setup(TourSettingItem item) {
        setTitle(item.getTitle());
        LinearLayout expandLayout = (LinearLayout) getExpandView();
        HashMap<String, Boolean> items = item.getItems();
        if (items == null)
            return;
        for (Map.Entry<String, Boolean> entry : items.entrySet()) {
            expandLayout.addView(checkBoxSetup(entry.getKey(), entry.getValue()));
        }
    }

    public CheckBox checkBoxSetup(String name, boolean isChecked) {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText(name);
        checkBox.setChecked(isChecked);
        return checkBox;
    }
}
