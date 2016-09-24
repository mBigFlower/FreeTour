package com.flowerfat.initapp.ui.tour;


import com.flowerfat.initapp.ui.ActivityScope;

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
