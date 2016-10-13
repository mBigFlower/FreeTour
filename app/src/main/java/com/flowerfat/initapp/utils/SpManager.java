package com.flowerfat.initapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 明明大美女 on 2016/5/12.
 * TODO
 */
public class SpManager {

    private static final String SP_NAME = "init";
    public static final String SP_TOUR_SETTING_LIST = "tour_setting_list";

    private static SpManager sInstance;

    private SharedPreferences mSP;

    public SpManager(Context context) {
        mSP = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SpManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SpManager.class) {
                if (sInstance == null) {
                    sInstance = new SpManager(context);
                }
            }
        }
        return sInstance;
    }

    /////////////////////////////////////////////
    public void put(String key, String value) {
        mSP.edit().putString(key, value).commit();
    }

    public void put(String key, int value) {

    }

    public void put(String key, boolean value) {

    }

    public String getString(String key) {
        return mSP.getString(key, null);
    }


}
