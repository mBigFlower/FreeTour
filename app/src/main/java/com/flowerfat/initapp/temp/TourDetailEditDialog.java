package com.flowerfat.initapp.temp;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/11/4.
 */

public class TourDetailEditDialog extends DialogManager<TourDetail> {

    @BindView(R.id.tour_details_edit_titleEt)
    MaterialEditText titleEt;
    @BindView(R.id.tour_details_edit_addressEt)
    MaterialEditText addressEt;
    @BindView(R.id.tour_details_edit_descriptionEt)
    MaterialEditText descriptionEt;
    @BindView(R.id.tour_details_edit_phoneEt)
    MaterialEditText phoneEt;
    @BindView(R.id.tour_details_edit_budgetEt)
    MaterialEditText budgetEt;
    @BindView(R.id.tour_details_edit_time)
    TextView timeTv;

    TourDetail tourDetail;

    private Activity mContext;

    public TourDetailEditDialog(Activity context, TourDetail tourDetail) {
        super(context);
        mContext = context;
        if (tourDetail == null)
            this.tourDetail = new TourDetail();
        else
            this.tourDetail = tourDetail;

        init();
    }

    private void init() {
        this.setView(R.layout.layout_tour_details_edit, true)
                .addPositiverButton("sure", false)
                .addNegativeButton("cancel");
//                .addOnCancelListener();
    }

    @Override
    protected void main() {
        super.main();
        titleEt.setText(tourDetail.getTitle());
        addressEt.setText(tourDetail.getAddress());
        descriptionEt.setText(tourDetail.getDesctription());
        if (tourDetail.getTime().length() > 0)
            timeTv.setText(tourDetail.getTime());
        phoneEt.setText(tourDetail.getPhone());
        // 让titleEt获得焦点
        Utils.setEditCursorLast(titleEt);
        if (tourDetail.getBudget() != 0)
            budgetEt.setText(tourDetail.getBudget() + "");
    }

    @Override
    protected TourDetail positiveClick() {
        if (!checkInput()) {
            return null;
        }
        return tourDetail;
    }

    @Override
    protected void negativeClick() {
        super.negativeClick();
    }

    private boolean checkInput() {
        String titleStr = titleEt.getText().toString().trim();
        if (TextUtils.isEmpty(titleStr)) {
            showToast("Title should not be null");
            return false;
        }
        String addrStr = addressEt.getText().toString().trim();
        if (TextUtils.isEmpty(addrStr)) {
            showToast("Address should not be null");
            return false;
        }
        String descStr = descriptionEt.getText().toString().trim();
        if (TextUtils.isEmpty(descStr)) {
            showToast("Description should not be null");
            return false;
        }
        String phoneStr = phoneEt.getText().toString().trim();
        if (phoneStr.length() > 0 && phoneStr.length() < 7) {
            showToast("Please input the right phone");
            return false;
        }
        if (TextUtils.isEmpty(tourDetail.getTime())) {
            showToast("Please choose the time");
            return false;
        }
        tourDetail.setAddress(addrStr);
        tourDetail.setTitle(titleStr);
        tourDetail.setDesctription(descStr);
        tourDetail.setPhone(phoneStr);
        tourDetail.setBudget(budgetEt.getText().toString().trim());
        return true;
    }

    @OnClick(R.id.tour_details_edit_time)
    void editTime() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
                    String time = makeTimeBeauty(hourOfDay, minute);
                    tourDetail.setTime(time);
                    timeTv.setText(time);
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.show(mContext.getFragmentManager(), "TimeChoose");
    }

    private String makeTimeBeauty(int hour, int minute) {
        String time = hour + ":";
        if (hour < 10)
            time = "0" + time;

        if (minute < 10)
            time = time + "0" + minute;
        else
            time = time + minute;

        return time;
    }
}
