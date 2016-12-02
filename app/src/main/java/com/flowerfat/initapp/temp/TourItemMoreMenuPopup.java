package com.flowerfat.initapp.temp;

import android.content.Context;
import android.widget.Toast;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseAnimPopup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/12/2.
 *
 * 这个本想弄一个炫酷点的，结果发现因为背景的关系，用RevealBackground不是很方便。
 * 回头研究下path那个层叠的，再来弄背景的。
 *
 * 本例暂且先使用一个渐显的效果
 */

public class TourItemMoreMenuPopup extends BaseAnimPopup {

    private String mPhoneStr ;

    public TourItemMoreMenuPopup(Context context, String phoneStr) {
        super(context);
        mPhoneStr = phoneStr ;
    }

    @Override
    protected void animStart() {
        contentView.animate().alpha(1).setDuration(200).start();
        ButterKnife.bind(this, contentView);
    }

    @Override
    public int initLayout() {
        return R.layout.view_tour_item_more;
    }

    @OnClick(R.id.view_popup_call_bt)
    void call(){
        Toast.makeText(context, "call "+mPhoneStr, Toast.LENGTH_SHORT).show();
    }
}
