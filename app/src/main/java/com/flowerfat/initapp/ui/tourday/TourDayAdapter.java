package com.flowerfat.initapp.ui.tourday;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseAdapter;
import com.flowerfat.initapp.base.BaseViewHolder;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.ui.view.TimeAxisView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 明明大美女 on 2016/11/1.
 */

public class TourDayAdapter extends BaseAdapter<TourDetail, TourDayAdapter.TourDeatilsViewHolder> {


    public TourDayAdapter() {

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
            holder.moreIv.setOnClickListener(s-> onClickListener.onClick(position));
        }
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
            if (position == 1) {
                titleTv.setTextColor(0xFFF15A59);
                axisView.makeChoose(true);
            }
        }
    }

}
