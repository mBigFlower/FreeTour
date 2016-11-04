package com.flowerfat.initapp.ui.tour;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseFragment;
import com.flowerfat.initapp.ui.adapter.TourMainSettingAdapter;
import com.flowerfat.initapp.utils.GsonUtil;
import com.flowerfat.initapp.utils.SpManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 明明大美女 on 2016/9/7.
 * <p>
 * 这个是每次新建一个自由行攻略必需的设置页
 */
public class TourMainFragment extends BaseFragment {

    @BindView(R.id.tour_main_date)
    TextView mDateTv;
    @BindView(R.id.tour_main_recyclerview)
    RecyclerView mSettingRv;

    TourMainSettingAdapter mAdapter ;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_tour_main;
    }

    @Override
    protected void main() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        // 瀑布流布局
        mSettingRv.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        String content = SpManager.getInstance().getString(SpManager.SP_TOUR_SETTING_LIST);
        List<String> data = GsonUtil.fromJsonList(content);
        mAdapter = new TourMainSettingAdapter(data);
        mSettingRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.tour_main_date)
    void toPrepareAct() {
//        TestPopup choicePopup = new TestPopup(getContext());
//        choicePopup.showAsDropDown(mDateTv);

    }
}
