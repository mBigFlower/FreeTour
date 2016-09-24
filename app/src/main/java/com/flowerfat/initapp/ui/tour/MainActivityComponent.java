package com.flowerfat.initapp.ui.tour;


import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.ui.ActivityScope;

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
