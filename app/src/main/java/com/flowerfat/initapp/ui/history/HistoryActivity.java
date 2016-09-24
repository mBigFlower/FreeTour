package com.flowerfat.initapp.ui.history;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.test)
    TextView textView ;

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
        textView.setText(" 123 ");
    }
}
