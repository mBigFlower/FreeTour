package com.flowerfat.initapp.ui.tour;

import com.flowerfat.initapp.model.TourDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/11/13.
 *
 * 这里欠缺一个SP的存数据
 */

public class TourDayFragmentModel {


    private List<TourDetail> tourDetails = new ArrayList<>();

    public TourDayFragmentModel() {
        TourDetail tourDetail = new TourDetail();
        tourDetail.setTitle("Introduction");
        tourDetail.setTime("10:45");
        tourDetail.setAddress("Dong Jing Hot");
        tourDetail.setDesctription("click item to revise");
        tourDetails.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Long click to delete");
        tourDetail.setTime("16:00");
        tourDetail.setAddress("Mario's Restaurant");
        tourDetail.setPhone("15828433284");
        tourDetail.setDesctription("click right icon for more");
        tourDetails.add(tourDetail);
    }

    public List<TourDetail> getTourDayList() {
        Collections.sort(tourDetails, new timeSort());
        return tourDetails;
    }

    /**
     * 添加一个细节。并且更新adapter
     * @param newTourDetail
     * @return
     */
    public List<TourDetail> addTourDetail(TourDetail newTourDetail) {
        // TODO 这里的时间不能为空，且格式要正确，不然会崩~
        int newTime = Integer.parseInt(newTourDetail.getTime().replace(":", ""));
        int size = tourDetails.size();
        for (int i = 0; i < size; i++) {
            int time = Integer.parseInt(tourDetails.get(i).getTime().replace(":", ""));
            if (newTime < time) {
                tourDetails.add(i, newTourDetail);
                return tourDetails;
            }
        }
        tourDetails.add(size, newTourDetail);
        return tourDetails;
    }

    public void deleteTourDetail(int index){
        tourDetails.remove(index);
    }

    // 排序
    public class timeSort implements Comparator<TourDetail> {
        @Override
        public int compare(TourDetail detail1, TourDetail detail2) {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            try {
                Date dt1 = format.parse(detail1.getTime());
                Date dt2 = format.parse(detail2.getTime());
                if (dt1.getTime() > dt2.getTime()) {
                    return 1;
                } else if (dt1.getTime() < dt2.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
}
