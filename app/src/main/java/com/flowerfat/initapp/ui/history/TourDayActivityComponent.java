package com.flowerfat.initapp.ui.history;


import dagger.Component;

@Component(modules = {TourDayActivityModule.class})
public interface TourDayActivityComponent {
    void inject(TourDayActivity tourDayActivity);
}
