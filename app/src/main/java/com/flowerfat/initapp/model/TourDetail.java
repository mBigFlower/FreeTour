package com.flowerfat.initapp.model;

/**
 * Created by 明明大美女 on 2016/11/1.
 */

public class TourDetail {

    private String title;
    private String time;
    private String address;
    private String phone;
    private int money ;
    private String desctription;
    private int trafficWay;
    private int type;

    public TourDetail() {
        title = time = address = phone = desctription = "";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }


    public void setTrafficWay(int trafficWay) {
        this.trafficWay = trafficWay;
    }
    public int getTrafficWay() {
        return DefaultValue.TrafficWay.getTrafficWayRes(trafficWay);
    }
}
