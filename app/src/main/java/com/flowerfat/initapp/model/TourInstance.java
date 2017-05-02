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
    private List<TourDay> mTourDayList = new ArrayList<>();

    public TourInstance() {
        mTour = GsonUtil.g().fromJson(SpManager.getInstance().getString(SpManager.SP_TOUR_NOW), Tour.class);
        if (mTour == null) {
            initFirstTour();
        }
        mTourDayList = mTour.getTourDays();
    }

    /**
     * 获取某一天数据
     * 如果要获取的没有，则需要新加一天，并保存
     * @param index
     * @return
     */
    public TourDay getTourDay(int index) {
        if (index == mTourDayList.size()) {
            TourDay tourDay = new TourDay("", "", -1, new ArrayList<>());
            mTourDayList.add(tourDay);
            save();
            return tourDay ;
        } else
            return mTourDayList.get(index);
    }

    public void deleteTourDay(int index){
        mTourDayList.remove(index);
        save();
    }

    /**
     * 获得本次Tour中有几天
     * @return
     */
    public int getTourDayNumber() {
        return mTourDayList.size();
    }

    /**
     * 保存
     */
    public void save() {
        SpManager.getInstance().put(SpManager.SP_TOUR_NOW, GsonUtil.toJson(mTour));
    }

    /**
     * 完成本次 Tour
     * 1. 将本次旅行存到历史中
     * 2. 新建一个旅行
     */
    public void completeTour(){
        completeTourRepository();
        completeTourNewTour();
    }

    /**
     * 完成本次旅行 之 存储部分
     */
    private void completeTourRepository() {
        String history = SpManager.getInstance().getString(SpManager.SP_TOUR_HISTORY);
        List<Tour> tourList = GsonUtil.fromJsonList(history);
        if (tourList == null) {
            tourList = new ArrayList<>();
        }
        tourList.add(mTour);
        SpManager.getInstance().put(SpManager.SP_TOUR_HISTORY, GsonUtil.toJson(tourList));
    }

    /**
     * 完成本次旅行 之 新建一个Tour
     */
    private void completeTourNewTour() {
        mTourDayList = new ArrayList<>();
        mTour = new Tour("FreeTour", null, null, null, -1, mTourDayList);
        save();
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
        tourDetail.setTrafficWay(DefaultValue.TrafficWay.TRAFFIC_CAR);
        tourDetail.setDesctription("click right icon for more");
        tourDetailList.add(tourDetail);

        TourDay tourDay = new TourDay("", "", -1, tourDetailList);
        mTourDayList.add(tourDay);

        mTour = new Tour("FreeTour", null, null, null, -1, mTourDayList);
    }

    public Tour getTour() {
        return mTour;
    }
}
