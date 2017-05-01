package com.flowerfat.initapp.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flowerfat.initapp.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 不显示title，使用我们自定义的
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
