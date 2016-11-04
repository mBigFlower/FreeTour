package com.flowerfat.initapp.ui.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.history_toolbar)
    Toolbar toolbar;

    public static void launch(Context startActivity) {
        startActivity.startActivity(new Intent(startActivity, HistoryActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_history;
    }

    @Override
    public void main() {
        super.main();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 不显示title，使用我们自定义的
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
