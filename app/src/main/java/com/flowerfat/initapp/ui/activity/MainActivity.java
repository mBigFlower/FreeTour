package com.flowerfat.initapp.ui.activity;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;
import com.flowerfat.initapp.ui.activity.component.DaggerMainActivityComponent;
import com.flowerfat.initapp.ui.activity.module.MainActivityModule;

//    .--,       .--,
//   ( (  \.---./  ) )
//    '.__/o   o\__.'
//       {=  ^  =}
//        >  -  <
//       /       \
//      //       \\
//     //|   .   |\\
//     "'\       /'"_.-~^`'-.
//        \  _  /--'         `
//      ___)( )(___
//     (((__) (__)))
//
//  高山仰止,景行行止.虽不能至,心向往之。

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

    }


    @Override
    public void main() {
        super.main();
    }
}
