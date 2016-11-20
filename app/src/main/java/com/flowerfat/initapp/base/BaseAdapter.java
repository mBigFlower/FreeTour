package com.flowerfat.initapp.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/11/2.
 */

public abstract class BaseAdapter<M, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<M> data = new ArrayList<M>();
    protected OnClickListener onClickListener;

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public interface OnClickListener {
        void onClick(int position);
    }

    public M getData(int position) {
        return data.get(position);
    }

    public List<M> addItem(M item){
        data.add(item);
        notifyItemInserted(data.size());
        return data ;
    }
    public List<M> addItem(int index, M item){
        data.add(index, item);
        notifyItemInserted(index);
        return data ;
    }

    public List<M> addAll(List<M> data){
        this.data.addAll(data);
        notifyDataSetChanged();
        return this.data ;
    }

    public List<M> removeItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
        return data ;
    }

    public void clear(){
        this.data.clear();
        notifyDataSetChanged();
    }
}
