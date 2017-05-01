package com.flowerfat.initapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/11/2.
 */

public abstract class BaseHeaderAdapter<M, headerVH extends BaseViewHolder,  mainVH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<M> data = new ArrayList<M>();
    protected OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CELL = 1;

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER: {
                return initHeaderView(parent);
            }
            case TYPE_CELL: {
                return initMainView(parent);
            }
        }
        return null;
    }

    public abstract headerVH initHeaderView(ViewGroup parent);

    public abstract mainVH initMainView(ViewGroup parent);

    public abstract void onHeaderBindViewHolder(headerVH holder, int position);

    public abstract void onMainBindViewHolder(mainVH holder, int position);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                holder.setData(null, 0);
                onHeaderBindViewHolder((headerVH)holder, position);
                break;
            case TYPE_CELL:
                holder.setData(data.get(position - 1), position);
                initListener(holder, position-1);
                onMainBindViewHolder((mainVH)holder, position-1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public M getData(int position) {
        return data.get(position);
    }

    public List<M> addItem(M item) {
        data.add(item);
        notifyItemInserted(data.size());
        return data;
    }

    public List<M> addItem(int index, M item) {
        data.add(index, item);
        notifyItemInserted(index);
        return data;
    }

    public List<M> addAll(List<M> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
        return this.data;
    }

    public List<M> removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        return data;
    }

    public void clear() {
        this.data.clear();
        notifyDataSetChanged();
    }
    //////////////////////////////////////////////////////////////////////////
    // 以上是数据相关
    //////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////
    // 以下是监听器相关
    //////////////////////////////////////////////////////////////////////////
    public void initListener(BaseViewHolder holder, int position) {
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(v -> onClickListener.onClick(v, position));
        }
        if (onLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                onLongClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int position);
    }

    public interface OnLongClickListener {
        void onLongClick(int position);
    }
    //////////////////////////////////////////////////////////////////////////
    // 以上是监听器相关
    //////////////////////////////////////////////////////////////////////////

}
