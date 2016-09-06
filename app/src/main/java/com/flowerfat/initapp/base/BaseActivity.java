package com.flowerfat.initapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.InitApplication;

import butterknife.ButterKnife;

/**
 * Created by 明明大美女 on 2016/5/8.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        setupActivityComponent(InitApplication.get().getAppComponent());
        main();
    }

    protected abstract int getLayoutResID();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public void main() {

    }
}
