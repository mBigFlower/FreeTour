package com.flowerfat.initapp.ui.aboutus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

import butterknife.BindView;

public class AboutUsActivity extends BaseActivity {


    @BindView(R.id.about_us_toolbar)
    Toolbar toolbar ;

    public static void launch(Context startActivity){
        startActivity.startActivity(new Intent(startActivity, AboutUsActivity.class));
//        CustomTabsIntent.launch
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_about_us;
    }

    @Override
    public void main() {
        super.main();
        initToolbar();
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
