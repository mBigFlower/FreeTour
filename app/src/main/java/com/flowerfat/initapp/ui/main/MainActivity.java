package com.flowerfat.initapp.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseActivity;
import com.flowerfat.initapp.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

//    .--,       .--,
//   ( (  \.---./  ) )
//    '.__/o   o\__.'
//       {=  ^  =}
//        >  -  <
//       /       \
//      //       \\
//     //|   .   |\\
//     "'\       /'"_.-~^`'-.
//        \  _  /--'         `
//      ___)( )(___
//     (((__) (__)))
//
//  高山仰止,景行行止.虽不能至,心向往之。

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar ;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    ViewPagerAdapter mAdapter ;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void main() {
        super.main();

//        setSupportActionBar(mToolbar);

        setupViewPager(mViewPager);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new TourFragment(), "Default");

        viewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.fab)
    void add(){
        mAdapter.addFragment(new TourFragment(), "User Add");
        mAdapter.notifyDataSetChanged();
    }
}
