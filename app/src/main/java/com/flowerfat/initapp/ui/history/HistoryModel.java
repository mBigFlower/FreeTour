package com.flowerfat.initapp.ui.history;

import com.flowerfat.initapp.model.Tour;
import com.flowerfat.initapp.utils.GsonUtil;
import com.flowerfat.initapp.utils.SpManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/12/13.
 */

public class HistoryModel {

    List<Tour> mTourList = new ArrayList();

    public HistoryModel() {
        mTourList = GsonUtil.fromJsonList(SpManager.getInstance().getString(SpManager.SP_TOUR_HISTORY));
    }

    public List<Tour> getTourList(){
        return mTourList;
    }

    public void deleteTour(int index){
        mTourList.remove(index);
    }
}
