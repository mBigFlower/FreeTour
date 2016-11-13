package com.flowerfat.initapp.ui.tourday;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseDaggerActivity;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.temp.DialogManager;
import com.flowerfat.initapp.temp.TourDetailEditDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 这里会涉及到单个Item的更新，使用notifyItemChanged(position)即可
 * <p>
 * Fab可以做成，滚动时隐藏，不滚动显示（几个一个）
 */
public class TourDayActivity extends BaseDaggerActivity implements TourDayContract.View{

    @Inject
    TourDayPresenter presenter ;

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
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTourDayActivityComponent.builder()
                .tourDayActivityModule(new TourDayActivityModule(this, new TourDayModel()))
                .build().inject(this);
    }

    @Override
    public void main() {
        super.main();
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 放这里？放到main()?
        presenter.start();
    }

    private void initRecyclerView() {
        mAdapter = new TourDayAdapter();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnClickListener(position -> {
            itemEdit(position);
        });
    }

    @OnClick(R.id.fab)
    void fabClick(View view) {
        view.animate().alpha(0).setDuration(300).start();
        TourDetailEditDialog dialogManager = new TourDetailEditDialog(this, null);
        dialogManager.setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
            @Override
            public void onSure(TourDetail data) {
                // 这个adapter还真是方便
                mAdapter.addItem(data);
                view.animate().alpha(1).setDuration(300).start();
                timeDialogShow();
            }

            @Override
            public void onCancel() {
                view.animate().alpha(1).setDuration(300).start();
            }
        });
    }

    @Override
    public void showList(List<TourDetail> tourDetails) {
        mAdapter.clear();
        mAdapter.addAll(tourDetails);
    }

    @Override
    public void timeDialogShow() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
                    Toast.makeText(this, hourOfDay + " " + minute + " " + second, Toast.LENGTH_SHORT).show();
                },
                now.get(Calendar.HOUR),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.show(getFragmentManager(), "TimeChoose");
    }

    @Override
    public void itemEdit(int position) {
        TourDetail tourDetail = mAdapter.getData(position);

        TourDetailEditDialog dialogManager = new TourDetailEditDialog(this, tourDetail);
        dialogManager.setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
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

    @Override
    public void itemAdd() {

    }

    @Override
    public void setPresenter(TourDayContract.Presenter presenter) {
        // 这里如果使用Dagger2的话，可以不用，如果不使用Dagger2，则如下设置：
        // 额，原谅我忘了怎么弄了
    }
}
