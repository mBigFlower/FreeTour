package com.flowerfat.initapp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseHeaderAdapter;
import com.flowerfat.initapp.base.BaseViewHolder;
import com.flowerfat.initapp.model.TourDay;
import com.flowerfat.initapp.model.TourDetail;
import com.flowerfat.initapp.ui.adapter.TourDayAdapter.TourDetailsVH;
import com.flowerfat.initapp.ui.view.TimeAxisView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/11/1.
 *
 * TODO 在BaseAdapter的基础上，如何我的列表也有两种类型？？？ 及扩展性。
 */

public class TourDayAdapter extends BaseHeaderAdapter<TourDetail, TourDayAdapter.TourDetailsHeaderVH, TourDetailsVH> {

    public static final int TYPE_SPECIAL = 2;

    private int specialPosition;
    // the header's data
    private String hotelStr;
    private String placeStr;
    private String contentStr;

    public TourDayAdapter() {
        specialPosition = -1;
        hotelStr = "hotel";
    }

    public TourDayAdapter(List<TourDetail> data) {
        this.data = data;
    }

    public TourDayAdapter(String hotelStr, String placeStr, String contentStr) {
        this.hotelStr = hotelStr;
        this.placeStr = placeStr;
        this.contentStr = contentStr;
    }

    @Override
    public TourDetailsHeaderVH initHeaderView(ViewGroup parent) {
        return new TourDetailsHeaderVH(parent, R.layout.item_tour_detail_header);
    }

    @Override
    public TourDetailsVH initMainView(ViewGroup parent) {
        return new TourDetailsVH(parent, R.layout.item_tour_detail);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_SPECIAL) {

        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onHeaderBindViewHolder(TourDetailsHeaderVH holder, int position) {
        // add the listener , and make sure the position
        holder.rootLayout.setOnClickListener(v -> onClickListener.onClick(v, 1000 + position));
    }

    @Override
    public void onMainBindViewHolder(TourDetailsVH holder, int position) {
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

    /**
     * 更新红色的位置
     */
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

    /**
     * 更新header的内容
     * @param hotelStr hotel name to sleep
     * @param placeStr place to travel
     * @param contentStr description for this day
     */
    public void updateHeader(String hotelStr, String placeStr, String contentStr) {
        if (!TextUtils.isEmpty(hotelStr))
            this.hotelStr = "Hotel: " + hotelStr;
        if (!TextUtils.isEmpty(placeStr))
            this.placeStr = "Place: " + placeStr;
        if (!TextUtils.isEmpty(contentStr))
            this.contentStr = contentStr;
        notifyItemChanged(0);
    }


    public class TourDetailsHeaderVH extends BaseViewHolder<TourDay> {

        @BindView(R.id.item_header_root)
        FrameLayout rootLayout ;
        @BindView(R.id.item_header_content)
        TextView contentTv;
        @BindView(R.id.item_header_hotel)
        TextView hotelTv;
        @BindView(R.id.item_header_place)
        TextView placeTv;

        public TourDetailsHeaderVH(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        @Override
        public void setData(TourDay data, int position) {
            hotelTv.setText(hotelStr);
            placeTv.setText(placeStr);
            contentTv.setText(contentStr);
        }

        @OnClick(R.id.item_header_hotel)
        void change() {
            if (contentTv.getVisibility() == View.VISIBLE) {
                contentTv.setVisibility(View.GONE);
            } else {
                contentTv.setVisibility(View.VISIBLE);
            }
        }
    }

    public class TourDetailsVH extends BaseViewHolder<TourDetail> {

        @BindView(R.id.item_time_axis_left_axis)
        TimeAxisView axisView;
        @BindView(R.id.item_time_axis_time)
        TextView timeTv;
        @BindView(R.id.item_time_axis_title)
        TextView titleTv;
        @BindView(R.id.item_time_axis_addr)
        TextView addressTv;
        @BindView(R.id.item_time_axis_budget)
        TextView budgetTv;
        @BindView(R.id.item_time_axis_desc)
        TextView descriptionTv;
        @BindView(R.id.item_time_axis_more)
        ImageView moreIv;
        @BindView(R.id.item_time_axis_phone)
        ImageView phoneIv;

        public TourDetailsVH(ViewGroup parent, @LayoutRes int resId) {
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
            if (data.getTrafficWay() != 0) {
                axisView.setAxisBitmap(data.getTrafficWay());
            }
            if (data.getBudget() != 0) {
                budgetTv.setText(data.getBudget() + "");
                budgetTv.setVisibility(View.VISIBLE);
            } else {
                budgetTv.setVisibility(View.INVISIBLE);
            }
        }
    }

}
