package com.flowerfat.initapp.temp;

import android.content.Context;
import android.text.TextUtils;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.TourDay;
import com.flowerfat.initapp.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2017/1/18.
 */

public class TourHeaderDialog extends DialogManager<TourDay> {

    private TourDay mTourDay ;

    @BindView(R.id.tour_header_edit_hotelEt)
    MaterialEditText hotelEt;
    @BindView(R.id.tour_header_edit_placeEt)
    MaterialEditText placeEt;
    @BindView(R.id.tour_header_edit_introduceEt)
    MaterialEditText introduceEt;

    public TourHeaderDialog(Context context, TourDay tourDay) {
        super(context);
        mTourDay = tourDay ;
        init();
    }

    @Override
    protected void main() {
        super.main();
        hotelEt.setText(mTourDay.getHotel());
        placeEt.setText(mTourDay.getPlace());
        introduceEt.setText("Happy new Year !");
        // 让hotelEt获得焦点
        Utils.setEditCursorLast(hotelEt);
    }


    private void init() {
        this.setView(R.layout.layout_tour_header_edit, true)
                .setTitle("Edit The Header")
                .addPositiverButton("sure", false)
                .addNegativeButton("cancel");
    }

    @Override
    protected TourDay positiveClick() {
        if(checkInput()) {
            return null ;
        }
//        dismiss();
        return mTourDay;
    }

    private boolean checkInput() {
        String hotelStr = hotelEt.getText().toString().trim();
        if (!TextUtils.isEmpty(hotelStr)) {
            mTourDay.setHotel(hotelStr);
        }
        String placeStr = placeEt.getText().toString().trim();
        if (!TextUtils.isEmpty(placeStr)) {
            mTourDay.setPlace(placeStr);
        }
        return false;
    }
}
