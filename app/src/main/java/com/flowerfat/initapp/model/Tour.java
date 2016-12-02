package com.flowerfat.initapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/12/1.
 *
 * 这个Tour应该弄一个单例，每次进应用，加载之（直到完成，换新的Tour）
 */

public class Tour {
    String title ;
    String time ;
    String creatTime;
    String place ;
    int money ;
    List<TourDay> tourDays = new ArrayList<>();
}
