package com.flowerfat.initapp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseHeaderAdapter;
import com.flowerfat.initapp.base.BaseViewHolder;
import com.flowerfat.initapp.model.TourDay;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.ui.adapter.TourDayAdapter.TourDeatilsHeaderVH;
import com.flowerfat.initapp.ui.adapter.TourDayAdapter.TourDeatilsVH;
import com.flowerfat.initapp.ui.view.TimeAxisView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/11/1.
 */

public class TourDayAdapter extends BaseHeaderAdapter<TourDetail, TourDeatilsHeaderVH, TourDeatilsVH> {

    private int specialPosition;

    public TourDayAdapter() {
        specialPosition = -1;
    }

    public TourDayAdapter(List<TourDetail> data) {
        this.data = data;
    }


    @Override
    public TourDeatilsHeaderVH initHeaderView(ViewGroup parent) {
        return new TourDayAdapter.TourDeatilsHeaderVH(parent, R.layout.item_tour_detail_header);
    }

    @Override
    public TourDeatilsVH initMainView(ViewGroup parent) {
        return new TourDayAdapter.TourDeatilsVH(parent, R.layout.item_tour_detail);
    }

    @Override
    public void onHeaderBindViewHolder(TourDeatilsHeaderVH holder, int position) {

    }

    @Override
    public void onMainBindViewHolder(TourDeatilsVH holder, int position) {
        // 都是监听器
        if (onClickListener != null) {
            holder.moreIv.setOnClickListener(v -> onClickListener.onClick(v, -1000 + position));
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
        specialPosition = size;
        notifyDataSetChanged();
    }


    public class TourDeatilsHeaderVH extends BaseViewHolder<TourDay> {

        @BindView(R.id.item_header_content)
        TextView contentTv;

        public TourDeatilsHeaderVH(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        @Override
        public void setData(TourDay data, int position) {

        }

        @OnClick(R.id.item_header_hotel)
        void change() {
            if (contentTv.getVisibility() == View.VISIBLE) {
                contentTv.setVisibility(View.GONE);
            } else
                contentTv.setVisibility(View.VISIBLE);
        }
    }

    public class TourDeatilsVH extends BaseViewHolder<TourDetail> {

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

        public TourDeatilsVH(ViewGroup parent, @LayoutRes int resId) {
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
            if(data.getTrafficWay() != 0) {
                axisView.setAxisBitmap(data.getTrafficWay());
            }
        }
    }

}
