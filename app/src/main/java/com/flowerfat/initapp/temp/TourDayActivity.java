package com.flowerfat.initapp.temp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;
import com.flowerfat.initapp.model.TourDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 这里会涉及到单个Item的更新，使用notifyItemChanged(position)即可
 * <p>
 * Fab可以做成，滚动时隐藏，不滚动显示（几个一个）
 */
public class TourDayActivity extends BaseActivity {

    @BindView(R.id.oneday_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private TourDayAdapter mAdapter;
    private List<TourDetail> tourDetails = new ArrayList<>();

    public static void launch(Context startActivity) {
        startActivity.startActivity(new Intent(startActivity, TourDayActivity.class));

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_tour_one_day;
    }

    @Override
    public void main() {
        super.main();
        initRecyclerView();
    }

    private void initRecyclerView() {
        TourDetail tourDetail = new TourDetail();
        tourDetail.setTitle("Board Meeting");
        tourDetail.setTime("9:45 AM");
        tourDetail.setAddress("2375 Powell Street");
        tourDetails.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Mark's Birthday Meal");
        tourDetail.setTime("5:00 PM");
        tourDetail.setAddress("Mario's Restaurant");
        tourDetails.add(tourDetail);
        tourDetail = new TourDetail();
        tourDetail.setTitle("Explosions In The Sky");
        tourDetail.setTime("8:15 PM");
        tourDetail.setAddress("The Roxy");
        tourDetails.add(tourDetail);
        tourDetails.add(tourDetail);
        mAdapter = new TourDayAdapter(tourDetails);


        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnClickListener(position -> {
            editPopupShow(position);
        });
    }

    public void editPopupShow(int position) {
        TourDetail tourDetail = mAdapter.getData(position);

        TourDetailEditDialog dialogManager = new TourDetailEditDialog(this, tourDetail);
        dialogManager
                .setTitle("add a item")
                .setView(R.layout.layout_tour_details_edit, true)
                .addPositiverButton("sure", false)
                .addNegativeButton("cancel")
                .addOnCancelListener()
                .setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
                    @Override
                    public void onSure(TourDetail data) {
                        // 这个adapter还真是方便
                        mAdapter.notifyItemChanged(position, data);
                    }

                    @Override
                    public void onCancel() {

                    }
                });

    }

    @OnClick(R.id.fab)
    void fabClick(View view) {
        view.animate().alpha(0).setDuration(300).start();

//        Calendar now = Calendar.getInstance();
//        DatePickerDialog dpd = new DatePickerDialog(
//                TourDayActivity.this, (v, year, monthOfYear, dayOfMonth) -> {
//        },
//                now.get(Calendar.YEAR),
//                now.get(Calendar.MONTH),
//                now.get(Calendar.DAY_OF_MONTH)
//        );
//        dpd.show();
    }
}
