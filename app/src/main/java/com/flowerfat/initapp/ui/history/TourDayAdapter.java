package com.flowerfat.initapp.ui.history;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseAdapter;
import com.flowerfat.initapp.base.BaseViewHolder;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.ui.view.TimeAxisView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2016/11/1.
 */

public class TourDayAdapter extends BaseAdapter<TourDetail, TourDayAdapter.TourDeatilsViewHolder> {

    private int specialPosition;

    public TourDayAdapter() {
        specialPosition = -1;
    }

    public TourDayAdapter(List<TourDetail> data) {
        this.data = data;
    }

    @Override
    public TourDeatilsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TourDeatilsViewHolder(parent, R.layout.item_tour_detail);
    }

    @Override
    public void onBindViewHolder(TourDeatilsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (onClickListener != null) {
            holder.moreIv.setOnClickListener(v -> onClickListener.onClick(v, -1000+position));
        }

        if (position == specialPosition) {
            holder.titleTv.setTextColor(0xFFF15A59);
            holder.axisView.makeChoose(true);
        } else {
            holder.titleTv.setTextColor(0xFFA4A4A4);
            holder.axisView.makeChoose(false);
        }
    }

    public void detectState() {
        // 获取时间
        int timeNow = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 100 + Calendar.getInstance().get(Calendar.MINUTE);
        // 进行比对
        int size = data.size();
        int time;
        for (int i = 0; i < size; i++) {
            time = Integer.parseInt(data.get(i).getTime().replace(":", ""));
            if (timeNow < time) {
                specialPosition = i;
                notifyDataSetChanged();
                return;
            }
        }
        specialPosition = size ;
        notifyDataSetChanged();
    }

    public class TourDeatilsViewHolder extends BaseViewHolder<TourDetail> {

        @BindView(R.id.item_time_axis_left_axis)
        TimeAxisView axisView;
        @BindView(R.id.item_time_axis_time)
        TextView timeTv;
        @BindView(R.id.item_time_axis_title)
        TextView titleTv;
        @BindView(R.id.item_time_axis_addr)
        TextView addressTv;
        @BindView(R.id.item_time_axis_desc)
        TextView descriptionTv;
        @BindView(R.id.item_time_axis_more)
        ImageView moreIv;
        @BindView(R.id.item_time_axis_phone)
        ImageView phoneIv;

        public TourDeatilsViewHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        @Override
        public void setData(TourDetail data, int position) {
            titleTv.setText(data.getTitle());
            timeTv.setText(data.getTime());
            addressTv.setText(data.getAddress());
            descriptionTv.setText(data.getDesctription());
            if ((position & 1) == 1) {
                itemView.setBackgroundColor(0xFFF3F3F3);
            }

            if (data.getPhone().length() > 0) {
                phoneIv.setVisibility(View.VISIBLE);
            } else {
                phoneIv.setVisibility(View.GONE);
            }
        }
    }

}
