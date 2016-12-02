package com.flowerfat.initapp.ui.toursetting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;
import com.flowerfat.initapp.ui.adapter.TourMainSettingAdapter;
import com.flowerfat.initapp.utils.GsonUtil;
import com.flowerfat.initapp.utils.SpManager;

import java.util.List;

import butterknife.BindView;

public class TourSettingActivity extends BaseActivity {

    @BindView(R.id.tour_main_date)
    TextView mDateTv;
    @BindView(R.id.tour_main_recyclerview)
    RecyclerView mSettingRv;

    TourMainSettingAdapter mAdapter ;

    public static void launch(Context startActivity) {
        startActivity.startActivity(new Intent(startActivity, TourSettingActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_tour_main;
    }

    @Override
    public void main() {
        super.main();
        initRecyclerView();
    }

    private void initRecyclerView() {
        // 瀑布流布局
        mSettingRv.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        String content = SpManager.getInstance().getString(SpManager.SP_TOUR_SETTING_LIST);
        List<String> data = GsonUtil.fromJsonList(content);
        mAdapter = new TourMainSettingAdapter(data);
        mSettingRv.setAdapter(mAdapter);
    }
}
