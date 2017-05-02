package com.flowerfat.initapp.model;

import com.flowerfat.initapp.R;

/**
 * Created by 明明大美女 on 2016/12/11.
 */

public class DefaultValue {



    public static class TrafficWay {
        public static final int TRAFFIC_NULL = 0;
        public static final int TRAFFIC_PLANE = 1;
        public static final int TRAFFIC_BUS = 2;
        public static final int TRAFFIC_BIKE = 3;
        public static final int TRAFFIC_CAR = 4;
        public static final int TRAFFIC_TRAIN = 5;
        public static final int TRAFFIC_TEXI = 6;

        public static int getTrafficWayRes(int trafficWay){
            switch (trafficWay) {
                case TRAFFIC_NULL:
                    break;
                case TRAFFIC_PLANE:
                    return R.drawable.ic_traffic_airplane_ltgray_18dp;
                case TRAFFIC_BUS:
                    return R.drawable.ic_traffic_bus_ltgray_18dp;
                case TRAFFIC_BIKE:
                    return R.drawable.ic_traffic_bike_ltgray_18dp;
                case TRAFFIC_CAR:
                    return R.drawable.ic_traffic_car_ltgray_18dp;
                case TRAFFIC_TRAIN:
                    return R.drawable.ic_traffic_train_ltgray_18dp;
                case TRAFFIC_TEXI: // 这个还没找图片
                    return R.drawable.ic_traffic_car_ltgray_18dp;
                default:
                    break;
            }
            return 0;
        }

        public static int getTrafficWayRes(int trafficWay, boolean isChoosed){
            if(isChoosed) {
                switch (trafficWay) {
                    case TRAFFIC_NULL:
                        break;
                    case TRAFFIC_PLANE:
                        return R.drawable.ic_traffic_airplane_pink_18dp;
                    case TRAFFIC_BUS:
                        return R.drawable.ic_traffic_bus_pink_18dp;
                    case TRAFFIC_BIKE:
                        return R.drawable.ic_traffic_bike_pink_18dp;
                    case TRAFFIC_CAR:
                        return R.drawable.ic_traffic_car_pink_18dp;
                    case TRAFFIC_TRAIN:
                        return R.drawable.ic_traffic_train_pink_18dp;
                    case TRAFFIC_TEXI: // 这个还没找图片
                        return R.drawable.ic_traffic_car_pink_18dp;
                    default:
                        break;
                }
                return 0;
            } else {
                return getTrafficWayRes(trafficWay);
            }
        }
    }
}
