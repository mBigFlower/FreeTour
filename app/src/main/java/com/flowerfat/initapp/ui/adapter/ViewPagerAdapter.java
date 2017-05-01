package com.flowerfat.initapp.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flowerfat.initapp.InitApplication;
import com.flowerfat.initapp.ui.tour.TourDayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/9/7.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<TourDayFragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(TourDayFragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public void deleteFragment(int index) {
        // 数据库中删除该天
        InitApplication.get().getTourInstance().deleteTourDay(index);
        // 页面中删除该天
        mFragments.remove(index);
        // 标题中删除该天
        mFragmentTitles.remove(mFragmentTitles.size()-1);
        notifyDataSetChanged();
    }

    @Override
    public TourDayFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

}
