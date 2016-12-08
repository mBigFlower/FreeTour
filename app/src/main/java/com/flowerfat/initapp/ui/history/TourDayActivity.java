package com.flowerfat.initapp.ui.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseDaggerActivity;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.temp.DialogManager;
import com.flowerfat.initapp.temp.TourDetailEditDialog;
import com.flowerfat.initapp.ui.adapter.TourDayAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 这里会涉及到单个Item的更新，使用notifyItemChanged(position)即可
 * <p>
 * Fab可以做成，滚动时隐藏，不滚动显示（几个一个）
 */
public class TourDayActivity extends BaseDaggerActivity implements TourDayContract.View {

    @Inject
    TourDayPresenter presenter;

    @BindView(R.id.oneday_recyclerview)
    RecyclerView mRecyclerview;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar ;

    private TourDayAdapter mAdapter;

    public static void launch(Context startActivity) {
        startActivity.startActivity(new Intent(startActivity, TourDayActivity.class));
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_tour_one_day;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTourDayActivityComponent.builder()
                .tourDayActivityModule(new TourDayActivityModule(this, new TourDayModel()))
                .build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 放这里？放到main()?
        presenter.start();
    }

    @Override
    public void main() {
        super.main();
        initToolbar();
        initRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        mAdapter = new TourDayAdapter();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnClickListener((v,position) -> {
            if (position >= 0)
                itemEditDialogShow(position);
            else {
                phoneDialogShow(position);
            }
        });
        mAdapter.setOnLongClickListener(position -> {
            Toast.makeText(this, "Long Click", Toast.LENGTH_SHORT).show();
        });
        mRecyclerview.setAdapter(mAdapter);
    }

    private void phoneDialogShow(int position){
        String phoneStr = mAdapter.getData(position + 1000).getPhone();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否拨打："+phoneStr)
                .setPositiveButton("拨打", (dialog, which) -> {

        }).show();
    }

    void fabClick(View view) {
        itemAddDialogShow();
    }

    @Override
    public void showList(List<TourDetail> tourDetails) {
        mAdapter.clear();
        mAdapter.addAll(tourDetails);
        mAdapter.detectState();
    }

    @Override
    public void timeDialogShow() {

    }

    @Override
    public void itemEditDialogShow(int position) {
        TourDetail tourDetail = mAdapter.getData(position);
        TourDetailEditDialog dialogManager = new TourDetailEditDialog(this, tourDetail);
        dialogManager.setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
            @Override
            public void onSure(TourDetail data) {
                // 这个adapter还真是方便
                mAdapter.notifyItemChanged(position, data);
                mAdapter.detectState();
            }

            @Override
            public void onCancel() {
                mAdapter.removeItem(position);
                presenter.deleteTourDetail(position);
                mAdapter.detectState();
            }
        });
    }

    @Override
    public void itemAddDialogShow() {
        TourDetailEditDialog dialogManager = new TourDetailEditDialog(this, null);
        dialogManager.setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
            @Override
            public void onSure(TourDetail data) {
                // 这个adapter还真是方便
                presenter.addTourDetail(data);
            }

            @Override
            public void onCancel() {
            }
        });
    }

    @Override
    public void setPresenter(TourDayContract.Presenter presenter) {
        // 这里如果使用Dagger2的话，可以不用，如果不使用Dagger2，则如下设置：
        // 额，原谅我忘了怎么弄了
    }
}
