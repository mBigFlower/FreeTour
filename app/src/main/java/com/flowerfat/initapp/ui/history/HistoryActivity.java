package com.flowerfat.initapp.ui.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;
import com.flowerfat.initapp.model.Tour;
import com.flowerfat.initapp.ui.adapter.TourHistoryAdapter;

import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity {


    @BindView(R.id.history_toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_recyclerview)
    RecyclerView mRecyclerView;

    private TourHistoryAdapter mAdapter;
    private HistoryModel mModel;

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
        initRecyclerView();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        mAdapter = new TourHistoryAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnClickListener((v, position) -> {
            Toast.makeText(this, "Click, to History Detail", Toast.LENGTH_SHORT).show();
        });
        mAdapter.setOnLongClickListener(position -> {
            deleteDialogShow(position);
        });
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addItem(new Tour("九寨沟", null, null, null, 0, null));
        mAdapter.addItem(new Tour("天安门", null, null, null, 0, null));
    }

    public void deleteDialogShow(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否删除该项记录？")
                .setPositiveButton("删除", (dialog, which) -> {
                    mModel.deleteTour(position);
                    // 下面两种方法，幸福二选一
                    mAdapter.removeItem(position);
//                    showHistoryList(mModel.getTourList());
                }).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showHistoryList(List<Tour> tourList) {
        mAdapter.clear();
        mAdapter.addAll(tourList);
    }

}
