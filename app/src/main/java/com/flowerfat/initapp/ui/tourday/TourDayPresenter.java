package com.flowerfat.initapp.ui.tourday;

import com.flowerfat.initapp.model.TourDetail;

import javax.inject.Inject;

/**
 * Created by 明明大美女 on 2016/11/13.
 */

public class TourDayPresenter implements TourDayContract.Presenter {

    private final TourDayContract.View mTourDayView;
    private final TourDayModel mTourDayModel;

    @Inject
    public TourDayPresenter(TourDayContract.View tourDayView, TourDayModel tourDayModel) {
        this.mTourDayView = tourDayView;
        this.mTourDayModel = tourDayModel;
    }

    @Override
    public void start() {
        initTourDay();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initTourDay() {
        mTourDayView.showList(mTourDayModel.getTourDayList());
    }

    @Override
    public void addTourDetail(TourDetail tourDetail) {
        mTourDayView.showList(mTourDayModel.addTourDetail(tourDetail));
    }

    @Override
    public void deleteTourDetail(int index) {
        mTourDayModel.deleteTourDetail(index);
    }

}
