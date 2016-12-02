package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.utils.GsonUtil;
import com.flowerfat.initapp.utils.SpManager;
import com.flowerfat.tomorrow.ui.view.ExpandLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 明明大美女 on 2016/9/24.
 */

public class CheckExpandView extends ExpandLayout implements View.OnClickListener {

    private String titleKey;
    private HashMap<String, Boolean> items;
    private int completedNumber;

    private TextView completeNumberTv;

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
        completeNumberTv = (TextView) getmView().findViewById(R.id.check_expand_number);
        getPressView().setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TourSettingItemPopup popup = new TourSettingItemPopup(getContext());
//                popup.showAsDropDown(v); TODO
                return true;
            }
        });
    }

    public void setup(String itemTitle) {
        titleKey = itemTitle;
        // 通过title，获取数据
        String spData = SpManager.getInstance().getString(itemTitle);
        items = GsonUtil.fromJsonHashMap(spData);
        if (items == null)
            return;
        // 添加View
        LinearLayout expandLayout = (LinearLayout) getExpandView();
        for (Map.Entry<String, Boolean> entry : items.entrySet()) {
            expandLayout.addView(checkBoxSetup(entry.getKey(), entry.getValue()));
            if (entry.getValue())
                completedNumber++;
        }
        // 设置标题
        setTitle(itemTitle);
        // 设置完成数量
        setNumber();
    }

    public void setTitle(String title) {
        ((TextView) getmView().findViewById(R.id.check_expand_title)).setText(title);
    }

    public void setNumber() {
        completeNumberTv.setText(completedNumber + "/" + items.size());
    }


    public CheckBox checkBoxSetup(String name, boolean isChecked) {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText(name);
        checkBox.setChecked(isChecked);
        checkBox.setOnClickListener(this);
        return checkBox;
    }

    @Override
    public void onClick(View v) {
        // 当点击时，更新sp数据
        CheckBox checkBox = (CheckBox) v;
        items.put(checkBox.getText().toString(), checkBox.isChecked());
        SpManager.getInstance().put(titleKey, GsonUtil.toJson(items));
        // 更新完成数
        if (checkBox.isChecked())
            completedNumber++;
        else
            completedNumber--;
        setNumber();
    }
}
