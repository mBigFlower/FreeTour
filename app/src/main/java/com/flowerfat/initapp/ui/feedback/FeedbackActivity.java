package com.flowerfat.initapp.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

import butterknife.BindView;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.feedback_toolbar)
    Toolbar toolbar ;

    public static void launch(Context startActivity){
        startActivity.startActivity(new Intent(startActivity, FeedbackActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_feedback;
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
