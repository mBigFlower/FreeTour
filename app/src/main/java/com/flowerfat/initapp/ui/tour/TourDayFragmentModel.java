package com.flowerfat.initapp.ui.tour;

import com.flowerfat.initapp.InitApplication;
import com.flowerfat.initapp.model.TourDay;
import com.flowerfat.initapp.model.TourDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/11/13.
 *
 * 这里欠缺一个SP的存数据
 */

public class TourDayFragmentModel {

    private TourDay mTourDay ;
    private List<TourDetail> tourDetails = new ArrayList<>();

    public TourDayFragmentModel(int pageIndex) {
        mTourDay = InitApplication.get().getTourInstance().getTourDay(pageIndex);
        tourDetails = mTourDay.getTourDetails();
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
//        int newTime = Integer.parseInt(newTourDetail.getTime().replace(":", ""));
//        int size = tourDetails.size();
//        for (int i = 0; i < size; i++) {
//            int time = Integer.parseInt(tourDetails.get(i).getTime().replace(":", ""));
//            if (newTime < time) {
//                tourDetails.add(i, newTourDetail);
//                return tourDetails;
//            }
//        }
//        tourDetails.add(size, newTourDetail);
        tourDetails.add(newTourDetail);
        InitApplication.get().getTourInstance().save();
        return getTourDayList();
    }

    public void deleteTourDetail(int index){
        tourDetails.remove(index);
        InitApplication.get().getTourInstance().save();
    }
    public void editTourDetail(int index, TourDetail data){
        tourDetails.set(index, data);
        InitApplication.get().getTourInstance().save();
    }

    // 排序
    public class timeSort implements Comparator<TourDetail> {
        @Override
        public int compare(TourDetail detail1, TourDetail detail2) {
            try {
                int time1 = Integer.parseInt(detail1.getTime().replace(":", ""));
                int time2 = Integer.parseInt(detail2.getTime().replace(":", ""));
                if (time1 > time2) {
                    return 1;
                } else if (time1 < time2) {
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
