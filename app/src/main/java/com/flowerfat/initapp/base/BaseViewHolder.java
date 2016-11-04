package com.flowerfat.initapp.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 明明大美女 on 2016/11/2.
 */

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    protected View itemView ;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int resId) {
        this(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView ;
        ButterKnife.bind(this, itemView);
    }

    public abstract void setData(M data, int position);
}
