package com.flowerfat.initapp.ui.feedback;

import android.content.Context;
import android.content.Intent;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {

    public static void launch(Context startActivity){
        startActivity.startActivity(new Intent(startActivity, FeedbackActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_feedback;
    }

}
