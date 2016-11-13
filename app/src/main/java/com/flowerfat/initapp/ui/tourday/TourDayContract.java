package com.flowerfat.initapp.ui.tourday;

import com.flowerfat.initapp.base.BasePresenter;
import com.flowerfat.initapp.base.BaseView;
import com.flowerfat.initapp.model.TourDetail;

import java.util.List;

/**
 * Created by 明明大美女 on 2016/11/13.
 */

public interface TourDayContract {

    interface View extends BaseView<Presenter>{
        void showList(List<TourDetail> tourDetails);
        void timeDialogShow();
        void itemEdit(int position);
        void itemAdd();
    }

    interface Presenter extends BasePresenter {
        void initTourDay();
    }
}
