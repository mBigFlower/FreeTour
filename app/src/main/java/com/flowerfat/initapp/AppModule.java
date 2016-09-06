package com.flowerfat.initapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 明明大美女 on 2016/5/11.
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){
        this.mApplication=application;
    }

    // 所有的单例都通过这种方式，添加到这个类里
    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

}
