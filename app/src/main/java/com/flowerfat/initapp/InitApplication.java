package com.flowerfat.initapp;

import android.app.Application;

import com.flowerfat.initapp.model.TourInstance;
import com.flowerfat.initapp.utils.InitTourSettingItems;

/**
 * Created by 明明大美女 on 2016/4/11.
 */
public class InitApplication extends Application {

    private static InitApplication sInstance ;
    private AppComponent appComponent;
    private TourInstance mTourInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this ;
        mTourInstance = new TourInstance();

        appComponent=DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        InitTourSettingItems.setup(this);
    }

    public static InitApplication get(){
        return sInstance ;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
    public TourInstance getTourInstance() {return mTourInstance;}
}
