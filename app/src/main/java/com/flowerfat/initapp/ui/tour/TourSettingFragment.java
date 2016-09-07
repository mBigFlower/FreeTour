package com.flowerfat.initapp.ui.tour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flowerfat.initapp.R;

/**
 * Created by 明明大美女 on 2016/9/7.
 *
 * 这个是每次新建一个自由行攻略必需的设置页
 *
 *
 */
public class TourSettingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rv = inflater.inflate(
                R.layout.fragment_tour_setting, container, false);
        return rv;
    }
}
