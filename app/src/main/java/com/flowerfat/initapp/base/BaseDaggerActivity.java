package com.flowerfat.initapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.InitApplication;

/**
 * Created by 明明大美女 on 2016/5/8.
 */
public abstract class BaseDaggerActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(InitApplication.get().getAppComponent());
        main();
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public void main() {

    }
}
