package com.flowerfat.initapp.temp;

import android.content.Context;
import android.text.TextUtils;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.TourDetail;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2016/11/4.
 */

public class TourDetailEditDialog extends DialogManager<TourDetail> {

    @BindView(R.id.tour_details_edit_titleEt)
    MaterialEditText titleEt;
    @BindView(R.id.tour_details_edit_addressEt)
    MaterialEditText addressEt;

    TourDetail tourDetail;

    public TourDetailEditDialog(Context context, TourDetail tourDetail) {
        super(context);
        if (tourDetail == null)
            this.tourDetail = new TourDetail();
        else
            this.tourDetail = tourDetail;
    }

    @Override
    protected void main() {
        super.main();
        titleEt.setText(tourDetail.getTitle());
        addressEt.setText(tourDetail.getAddress());
    }

    @Override
    protected TourDetail positiveClick() {
        if(!checkInput()) {
            return null ;
        }
        return tourDetail;
    }

    @Override
    protected void negativeClick() {
        super.negativeClick();
    }

    private boolean checkInput() {
        String titleStr = titleEt.getText().toString().trim();
        if(TextUtils.isEmpty(titleStr)) {
            showToast("Title should not be null");
            return false ;
        }
        String addrStr = addressEt.getText().toString().trim();
        if(TextUtils.isEmpty(addrStr)) {
            showToast("Address should not be null");
            return false ;
        }
        tourDetail.setAddress(addrStr);
        tourDetail.setTitle(titleStr);
        return true ;
    }
}
