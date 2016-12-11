package com.flowerfat.initapp.model;

import com.flowerfat.initapp.utils.GsonUtil;
import com.flowerfat.initapp.utils.SpManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/12/11.
 */

public class TourInstance {

    private Tour mTour;
    private List<TourDay> tourDayList = new ArrayList<>();

    public TourInstance() {
        mTour = GsonUtil.g().fromJson(SpManager.getInstance().getString(SpManager.SP_TOUR_NOW), Tour.class);
        if (mTour == null) {
            initFirstTour();
        }
        tourDayList = mTour.getTourDays();
    }

    /**
     * 如果要获取的没有，则需要新加一天，并保存
     * @param index
     * @return
     */
    public TourDay getTourDay(int index) {
        if (index == tourDayList.size()) {
            TourDay tourDay = new TourDay(null, null, -1, new ArrayList<>());
            tourDayList.add(tourDay);
            save();
            return tourDay ;
        } else
            return tourDayList.get(index);
    }

    public int getTourDayNumber() {
        return tourDayList.size();
    }

    public void save() {
        SpManager.getInstance().put(SpManager.SP_TOUR_NOW, GsonUtil.toJson(mTour));
    }

    /**
     * 第一次使用该应用时，添加的默认内容
     */
    private void initFirstTour() {
        List<TourDetail> tourDetailList = new ArrayList<>();

        TourDetail tourDetail = new TourDetail();
        tourDetail.setTitle("Introduction");
        tourDetail.setTime("10:45");
        tourDetail.setAddress("Dong Jing Hot");
        tourDetail.setDesctription("click item to revise");
        tourDetailList.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Long click to delete");
        tourDetail.setTime("16:00");
        tourDetail.setAddress("Mario's Restaurant");
        tourDetail.setPhone("8008208820");
        tourDetail.setTrafficWay(DefaultValue.TrafficWay.TRAFFIC_BIKE);
        tourDetail.setDesctription("click right icon for more");
        tourDetailList.add(tourDetail);

        TourDay tourDay = new TourDay(null, null, -1, tourDetailList);
        tourDayList.add(tourDay);

        mTour = new Tour("FreeTour", null, null, null, -1, tourDayList);
    }
}
