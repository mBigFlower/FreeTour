package com.flowerfat.initapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 明明大美女 on 2016/5/11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Application getApplication();

}
