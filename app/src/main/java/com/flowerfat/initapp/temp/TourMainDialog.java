package com.flowerfat.initapp.temp;

import android.content.Context;
import android.text.TextUtils;

import com.flowerfat.initapp.InitApplication;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.Tour;
import com.flowerfat.initapp.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2017/5/1.
 */

public class TourMainDialog extends DialogManager<Tour> {

    @BindView(R.id.tour_main_edit_title)
    MaterialEditText titleEt;


    private Tour mTour ;

    public TourMainDialog(Context context) {
        super(context);
        mTour = InitApplication.get().getTourInstance().getTour() ;
        init();
    }


    private void init() {
        this.setView(R.layout.layout_tour_main_edit, true)
                .setTitle("Edit The Tour")
                .addPositiverButton("sure", false)
                .addNegativeButton("cancel");
    }

    @Override
    protected void main() {
        super.main();
        titleEt.setText(mTour.getTitle());
        Utils.setEditCursorLast(titleEt);
    }


    @Override
    protected Tour positiveClick() {
        if(checkInput()) {
            return null ;
        }
//        dismiss();
        InitApplication.get().getTourInstance().save();
        return mTour;
    }

    private boolean checkInput() {
        String titleStr = titleEt.getText().toString().trim();
        if (!TextUtils.isEmpty(titleStr)) {
            mTour.setTitle(titleStr);
        }
        return false;
    }
}
