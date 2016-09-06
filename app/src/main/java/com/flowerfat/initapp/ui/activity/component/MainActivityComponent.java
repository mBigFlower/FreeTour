package com.flowerfat.initapp.ui.activity.component;


import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.ui.activity.ActivityScope;
import com.flowerfat.initapp.ui.activity.MainActivity;
import com.flowerfat.initapp.ui.activity.module.MainActivityModule;
import com.flowerfat.initapp.ui.activity.presenter.MainActivityPresenter;

import dagger.Component;

/**
 * Created by clevo on 2015/6/10.
 */
@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);

    MainActivityPresenter presenter();

}
