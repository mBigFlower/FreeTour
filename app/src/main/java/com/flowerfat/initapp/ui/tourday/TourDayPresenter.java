package com.flowerfat.initapp.ui.tourday;

import com.flowerfat.initapp.model.TourDetail;

import java.util.ArrayList;
import java.util.List;

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
        List<TourDetail> tourDetails = new ArrayList<>();
        TourDetail tourDetail = new TourDetail();
        tourDetail.setTitle("Board Meeting");
        tourDetail.setTime("9:45 AM");
        tourDetail.setAddress("2375 Powell Street");
        tourDetails.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Mark's Birthday Meal");
        tourDetail.setTime("5:00 PM");
        tourDetail.setAddress("Mario's Restaurant");
        tourDetails.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Explosions In The Sky");
        tourDetail.setTime("8:15 PM");
        tourDetail.setAddress("The Roxy");
        tourDetails.add(tourDetail);
        tourDetails.add(tourDetail);
        mTourDayView.showList(tourDetails);
    }
}
