package com.flowerfat.initapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.flowerfat.initapp.InitApplication;

/**
 * Created by 明明大美女 on 2016/5/12.
 * TODO
 */
public class SpManager {

    private static final String SP_NAME = "init";
    public static final String SP_TOUR_SETTING_LIST = "tour_setting_list";
    public static final String SP_TOUR_HISTORY = "tour_history";
    public static final String SP_TOUR_NOW = "tour_now";
    public static final String SP_IS_FIRST_TOUR = "is_first_tour";

    private static SpManager sInstance;

    private SharedPreferences mSP;

    public SpManager() {
        mSP = InitApplication.get().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SpManager getInstance() {
        if (sInstance == null) {
            synchronized (SpManager.class) {
                if (sInstance == null) {
                    sInstance = new SpManager();
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
