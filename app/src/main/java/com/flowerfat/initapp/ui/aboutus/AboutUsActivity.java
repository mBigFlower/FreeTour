package com.flowerfat.initapp.ui.aboutus;

import android.content.Context;
import android.content.Intent;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {

    public static void launch(Context startActivity){
        startActivity.startActivity(new Intent(startActivity, AboutUsActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_about_us;
    }

}
