package com.flowerfat.initapp.ui.tour;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseFragment;
import com.flowerfat.initapp.base.BasePopup;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.temp.DialogManager;
import com.flowerfat.initapp.temp.TourDetailEditDialog;
import com.flowerfat.initapp.temp.TourItemMoreMenuPopup;
import com.flowerfat.initapp.ui.adapter.TourDayAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2016/9/7.
 */
public class TourDayFragment extends BaseFragment{

    @BindView(R.id.oneday_recyclerview)
    RecyclerView mRecyclerview;

    private TourDayAdapter mAdapter;
    private TourDayFragmentModel mModel;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_tour_one_day;
    }

    @Override
    protected void main() {
        initRecyclerView();
        // 数据层
        mModel = new TourDayFragmentModel();
        showList(mModel.getTourDayList());
    }

    private void initRecyclerView() {
        mAdapter = new TourDayAdapter();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnClickListener((v, position) -> {
            if (position >= 0)
                itemEditDialogShow(position);
            else {
                moreDialogShow(v, position);
            }
        });
        mAdapter.setOnLongClickListener(position -> {
            deleteDialogShow(position);
        });
        mRecyclerview.setAdapter(mAdapter);
    }

    public void showList(List<TourDetail> tourDetails) {
        mAdapter.clear();
        mAdapter.addAll(tourDetails);
        mAdapter.detectState();
    }

    private void moreDialogShow(View v, int position) {
        String phoneStr = mAdapter.getData(position + 1000).getPhone();
        TourItemMoreMenuPopup moreMenuPopup = new TourItemMoreMenuPopup(getActivity(), phoneStr);
        moreMenuPopup.showOnAnchor(v, BasePopup.VerticalPosition.ABOVE, BasePopup.HorizontalPosition.LEFT);
        moreMenuPopup.setOnMoreMenuItemClickListener(new TourItemMoreMenuPopup.OnMoreMenuItemClickListener() {
            @Override
            public void onCallClick(String phoneStr) {
                callDialogShow(phoneStr);
            }

            @Override
            public void onDeleteClick() {
                deleteDialogShow(position);
            }

            @Override
            public void onCancelClick() {

            }
        });
    }

    public void callDialogShow(String phoneStr){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否拨打：" + phoneStr)
                .setPositiveButton("拨打", (dialog, which) -> {

                }).show();
    }

    public void deleteDialogShow(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否删除该项？")
                .setPositiveButton("删除", (dialog, which) -> {
                    mAdapter.removeItem(position);
                    mModel.deleteTourDetail(position);
                    showList(mModel.getTourDayList());
                    mAdapter.detectState();
                }).show();
    }

    public void itemEditDialogShow(int position) {
        TourDetail tourDetail = mAdapter.getData(position);
        TourDetailEditDialog dialogManager = new TourDetailEditDialog(getActivity(), tourDetail);
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
                mModel.deleteTourDetail(position);
                showList(mModel.getTourDayList());
                mAdapter.detectState();
            }
        });
    }



    void itemAddDialogShow() {
        TourDetailEditDialog dialogManager = new TourDetailEditDialog(getActivity(), null);
        dialogManager.setDialogListener(new DialogManager.OnDialogListener<TourDetail>() {
            @Override
            public void onSure(TourDetail data) {
                // 这个adapter还真是方便
                showList(mModel.addTourDetail(data));
            }

            @Override
            public void onCancel() {
            }
        });
    }

}
