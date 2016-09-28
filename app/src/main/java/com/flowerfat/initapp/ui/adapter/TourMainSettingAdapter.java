package com.flowerfat.initapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.model.TourSettingItem;
import com.flowerfat.initapp.ui.view.CheckExpandView;

import java.util.List;

/**
 * Created by 明明大美女 on 2016/9/23.
 */

public class TourMainSettingAdapter extends RecyclerView.Adapter<TourMainSettingAdapter.MyViewHolder> {

    private List<TourSettingItem> mDataModels;

    public TourMainSettingAdapter(List<TourSettingItem> dataModels) {
        if (dataModels == null) {
            throw new IllegalArgumentException("DataModel must not be null");
        }
        mDataModels = dataModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour_main_setting, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mExpandLayout.setup(mDataModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataModels.size();
    }

    public void removeData(int position) {
        mDataModels.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CheckExpandView mExpandLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mExpandLayout = (CheckExpandView) itemView.findViewById(R.id.item_tour_main_expandlayout);
        }


    }
}