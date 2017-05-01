package com.flowerfat.initapp.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseAdapter;
import com.flowerfat.initapp.base.BaseViewHolder;
import com.flowerfat.initapp.model.Tour;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 明明大美女 on 2016/12/13.
 */

public class TourHistoryAdapter extends BaseAdapter<Tour, TourHistoryAdapter.TourHistoryVH> {

    private Context mContext ;
    public TourHistoryAdapter(Context context){
        mContext = context ;
    }

    @Override
    public TourHistoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TourHistoryVH(parent, R.layout.item_history);
    }

    public class TourHistoryVH extends BaseViewHolder<Tour> {

        @BindView(R.id.item_history_title)
        TextView titleTv ;
        @BindView(R.id.item_history_pic)
        ImageView pictureIv ;
        public TourHistoryVH(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
        }

        @Override
        public void setData(Tour data, int position) {
            titleTv.setText(data.getTitle());
            Glide.with(mContext)
                    .load(R.drawable.big1)
                    .bitmapTransform(new BlurTransformation(mContext), new CropCircleTransformation(mContext))
                    .into(pictureIv);
        }
    }
}
