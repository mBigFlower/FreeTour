package com.flowerfat.initapp.temp;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by 明明大美女 on 2016/11/4.
 */

public class DialogManager<T> {

    public OnDialogListener listener;
    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;
    protected View customView;

    private boolean isPosButtonAutoCancel;

    public DialogManager(Context context) {
        mBuilder = new AlertDialog.Builder(context);
    }

    public void show() {
        mBuilder.show();
    }

    public DialogManager setTitle(CharSequence title) {
        mBuilder.setTitle(title);
        return this;
    }

    public DialogManager setView(int layoutResId) {
        return setView(LayoutInflater.from(mBuilder.getContext()).inflate(layoutResId, null));
    }

    public DialogManager setView(View view) {
        return setView(view, false);
    }

    /**
     * 在设置自定义View的时候，可选择是否使用butterKnife
     * @param layoutResId
     * @param isButterKnife
     * @return
     */
    public DialogManager setView(int layoutResId, boolean isButterKnife) {
        return setView(LayoutInflater.from(mBuilder.getContext()).inflate(layoutResId, null), isButterKnife);
    }

    public DialogManager setView(View view, boolean isButterKnife) {
        customView = view;
        mBuilder.setView(view);
        if (isButterKnife)
            bindButterKnife();
        return this;
    }

    public DialogManager addPositiverButton(CharSequence text) {
        return addPositiverButton(text, true);
    }

    /**
     * 在设置button的时候，可选择：点击button是否自动取消Dialog
     * @param text
     * @param isPosButtonAutoCancel
     * @return
     */
    public DialogManager addPositiverButton(CharSequence text, boolean isPosButtonAutoCancel) {
        setPositonButtonCancelable(isPosButtonAutoCancel);
        mBuilder.setPositiveButton(text, (dialog, which) -> {
            T data = positiveClick();
            if (listener != null && data != null) {
                listener.onSure(data);
            }
        });
        return this;
    }

    public DialogManager addNegativeButton(CharSequence text) {
        mBuilder.setNegativeButton(text, (dialog, which) -> {
            if (listener != null) {
                negativeClick();
                listener.onCancel();
            }
        });
        return this;
    }

    public DialogManager addOnCancelListener() {
        mBuilder.setOnCancelListener(v -> {
            if (listener != null)
                listener.onCancel();
        });
        return this;
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public void showToast(String message) {
        Toast.makeText(mDialog.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //////////////////////////////////////////////
    // ContentView相关
    // 通过此部分，获得dialog中的内容。
    //////////////////////////////////////////////

    protected T positiveClick() {
        return null;
    }

    protected void negativeClick() {

    }

    /**
     * 若继承此类，可以通过该方法，添加ButterKnifede的绑定
     * 该方法不要在构造函数中调用，因为那时的customView尚未设置
     */
    public void bindButterKnife() {
        if (customView == null) {
            throw new RuntimeException("Please setView first, if you want to use the ButterKnife");
        }
        ButterKnife.bind(this, customView);
    }

    /**
     * 不使用ButterKinfe，直接获取View（PS：具体使用时再强转）
     *
     * @param idRes
     * @return
     */
    public View getView(@IdRes int idRes) {
        if (customView == null) {
            throw new RuntimeException("Please setView first, if you want to use the layout yourself");
        }
        return customView.findViewById(idRes);
    }

    public View getCustomView() {
        return customView;
    }

    /**
     * 设置这个的目的：有些时候，我们要对dialog中的输入进行判断，如果判断失败，则不取消dialog
     *
     * @param isPosButtonAutoCancel
     */
    public void setPositonButtonCancelable(boolean isPosButtonAutoCancel) {
        this.isPosButtonAutoCancel = isPosButtonAutoCancel;
    }

    /**
     * 当dialog显示的时候，判断是否需要禁用 button的点击消失
     */
    public void doPosButtonCancelable() {
        if (!isPosButtonAutoCancel) {
            mDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                T data = positiveClick();
                if (listener != null && data != null) {
                    dismiss();
                    listener.onSure(data);
                }
            });
        }
    }


    protected void main() {

    }

    //////////////////////////////////////////////
    // 接口
    //////////////////////////////////////////////
    public DialogManager setDialogListener(OnDialogListener listener) {
        this.listener = listener;
        mDialog = mBuilder.show();
        doPosButtonCancelable();
        main();
        return this;
    }

    public interface OnDialogListener<T> {
        void onSure(T object);

        void onCancel();
    }
}
