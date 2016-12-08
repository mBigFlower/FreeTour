package com.flowerfat.initapp.temp;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BasePopup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/12/2.
 *
 */

public class TourItemMoreMenuPopup extends BasePopup {

    @BindView(R.id.view_popup_call_bt)
    Button callBt ;

    private OnMoreMenuItemClickListener onItemClickListener;
    private String mPhoneStr;

    public TourItemMoreMenuPopup(Context context, String phoneStr) {
        super(context, R.layout.view_tour_item_more);
        ButterKnife.bind(this, getContentView());
        mPhoneStr = phoneStr;
        // 这里根据有无电话来判断，是否显示该条目，隐藏后，重测布局
        if(TextUtils.isEmpty(phoneStr)) {
            callBt.setVisibility(View.GONE);
            measureLayout();
        }
    }

    @OnClick(R.id.view_popup_call_bt)
    void call(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onCallClick(mPhoneStr);
        }
        dismiss();
    }

    @OnClick(R.id.view_popup_delete_bt)
    void delete(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onDeleteClick();
        }
        dismiss();
    }

    @OnClick(R.id.view_popup_cancel_bt)
    void cancel() {
        if (onItemClickListener != null) {
            onItemClickListener.onCancelClick();
        }
        dismiss();
    }

    public void setOnMoreMenuItemClickListener(OnMoreMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnMoreMenuItemClickListener {
        void onCallClick(String phoneStr);

        void onDeleteClick();

        void onCancelClick();
    }
}
