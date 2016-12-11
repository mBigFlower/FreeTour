package com.flowerfat.initapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/12/1.
 *
 */

public class Tour {

    private String title;
    private String time;
    private String creatTime;
    private String place;
    private int money;
    private List<TourDay> tourDays = new ArrayList<>();

    public Tour(String title, String time, String creatTime, String place, int money, List<TourDay> tourDays) {
        this.title = title;
        this.time = time;
        this.creatTime = creatTime;
        this.place = place;
        this.money = money;
        this.tourDays = tourDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<TourDay> getTourDays() {
        return tourDays;
    }

    public void setTourDays(List<TourDay> tourDays) {
        this.tourDays = tourDays;
    }
}
