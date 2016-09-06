package com.flowerfat.initapp.ui.activity.module;


import com.flowerfat.initapp.ui.activity.ActivityScope;
import com.flowerfat.initapp.ui.activity.MainActivity;
import com.flowerfat.initapp.ui.activity.presenter.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }


    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(MainActivity mainActivity) {
        return new MainActivityPresenter(mainActivity);
    }

}
