package com.flowerfat.initapp.ui.tour;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flowerfat.initapp.AppComponent;
import com.flowerfat.initapp.R;
import com.flowerfat.initapp.base.BaseDaggerActivity;
import com.flowerfat.initapp.ui.aboutus.AboutUsActivity;
import com.flowerfat.initapp.ui.adapter.ViewPagerAdapter;
import com.flowerfat.initapp.ui.feedback.FeedbackActivity;
import com.flowerfat.initapp.ui.history.HistoryActivity;
import com.flowerfat.initapp.ui.history.TourDayActivity;
import com.flowerfat.initapp.ui.toursetting.TourSettingActivity;

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

public class MainActivity extends BaseDaggerActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.main_tour_top_layout)
    LinearLayout mTopLayout ;

    ViewPagerAdapter mAdapter;

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

        setupDrawLayout();
        setupViewPager();

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 初始化DrawLayout及左上角
     */
    private void setupDrawLayout() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(mNavigationView);

        collapsingToolbar.setTitle("Tour Title");
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 初始化 ViewPager
     */
    private void setupViewPager() {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new TourDayFragment(), "Day0");

        mViewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.fab)
    void add() {
        mAdapter.getItem(mViewPager.getCurrentItem()).itemAddDialogShow();
    }

    @OnClick(R.id.main_tour_top_layout)
    void editTour(){
        Toast.makeText(this, "Yeah", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.main_setting:
                TourSettingActivity.launch(this);
                return true;
            case R.id.main_add_one_day:
                addOneDay();
                return true;
            case R.id.main_complete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定完成本次旅行？")
                        .setPositiveButton("确定", (dialog, which) -> {
                            Toast.makeText(this, "Complete this tour", Toast.LENGTH_SHORT).show();
                        }).show();
                return true;
            default:
                Toast.makeText(this, "Click Default", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addOneDay(){
        mAdapter.addFragment(new TourDayFragment(), "Day " + mAdapter.getCount());
        mAdapter.notifyDataSetChanged();
        // 增加一页后，跳转到该新页面
        mViewPager.setCurrentItem(mAdapter.getCount());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 左侧导航栏点击
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_tour:

                break;
            case R.id.nav_history:
                HistoryActivity.launch(this);
                break;
            case R.id.nav_setting:
                TourDayActivity.launch(this);
                break;
            case R.id.nav_aboutus:
                AboutUsActivity.launch(this);
                break;
            case R.id.nav_feedback:
                FeedbackActivity.launch(this);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
