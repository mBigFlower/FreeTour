package com.flowerfat.initapp;

import android.app.Application;

/**
 * Created by 明明大美女 on 2016/4/11.
 */
public class InitApplication extends Application {

    private static InitApplication mApplication = null;
    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this; // 单例
    }

    public static InitApplication getInstance() {
        return mApplication;
    }

}
