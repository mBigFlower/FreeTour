package com.flowerfat.initapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/12/1.
 */

public class TourDay {
    private String place;
    private String hotel;
    private int dayMoney;
    private List<TourDetail> tourDetails = new ArrayList<>();

    public TourDay(String place, String hotel, int dayMoney, List<TourDetail> tourDetails) {
        this.place = place;
        this.hotel = hotel;
        this.dayMoney = dayMoney;
        this.tourDetails = tourDetails;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(int dayMoney) {
        this.dayMoney = dayMoney;
    }

    public List<TourDetail> getTourDetails() {
        return tourDetails;
    }

    public void setTourDetails(List<TourDetail> tourDetails) {
        this.tourDetails = tourDetails;
    }
}
