package com.flowerfat.initapp.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseAnimPopup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/10/11.
 */

public class TourSettingItemPopup extends BaseAnimPopup implements View.OnLongClickListener{

    @BindView(R.id.setting_item_add_layout)
    LinearLayout addLinearLayout;
    @BindView(R.id.setting_item_add_edit)
    EditText addEditText;

    public TourSettingItemPopup(Context context) {
        super(context);

        ButterKnife.bind(this, getMainView());
    }

    @Override
    protected void animStart() {

    }

    @Override
    public int initLayout() {
        return R.layout.layout_setting_item;
    }

    @OnClick(R.id.setting_item_cancel)
    void cancel() {
        dismiss();
    }

    @OnClick(R.id.setting_item_add)
    void add() {
        String addContent = addEditText.getText().toString().trim();
        if ("".equals(addContent)) {
            return;
        }
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(addContent);
        checkBox.setOnLongClickListener(this);
        addLinearLayout.addView(checkBox);
        addEditText.setText("");
    }

    @OnClick(R.id.setting_item_sure)
    void sure() {
        // TODO 保存， 并更新UI，使用eventbus？还是用rxjava的bus？用监听器？
        dismiss();
    }


    @Override
    public boolean onLongClick(View v) {
        addLinearLayout.removeView(v);
        return true;
    }
}
