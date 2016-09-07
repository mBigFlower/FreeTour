package com.flowerfat.initapp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flowerfat.initapp.R;

/**
 * Created by 明明大美女 on 2016/9/7.
 */
public class TourFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rv = inflater.inflate(
                R.layout.fragment_tour, container, false);
        return rv;
    }

}
