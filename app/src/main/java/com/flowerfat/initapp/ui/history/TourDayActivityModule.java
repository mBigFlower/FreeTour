package com.flowerfat.initapp.ui.history;


import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class TourDayActivityModule {

    private final TourDayContract.View mTourDayView;
    private final TourDayModel mTourDayModel;

    public TourDayActivityModule(TourDayContract.View tourDayView, TourDayModel tourDayModel) {
        this.mTourDayView = tourDayView;
        this.mTourDayModel = tourDayModel;
    }

    @Provides
    TourDayContract.View provideTourDayView() {
        return mTourDayView;
    }


    @Provides
    TourDayModel provideTourDayModel() {
        return mTourDayModel;
    }

}
