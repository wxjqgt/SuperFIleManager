package com.weibo.superfilemanager.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseActivity;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MainActivityContract;
import com.weibo.superfilemanager.mvp.presenter.MainActivityPresenterImp;
import com.weibo.superfilemanager.util.ImageLoader;
import com.weibo.superfilemanager.util.StatusBarCompat;
import com.weibo.superfilemanager.util.ViewUtil;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView {

    private NavigationView nav_main;
    private DrawerLayout drawwelayout_main;
    private Toolbar toolbar_main;

    private MainActivityContract.MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_main.setTitleMarginTop(StatusBarCompat.getStatusBarHeight(this));
        setSupportActionBar(toolbar_main);
        new MainActivityPresenterImp(this);
    }

    @Override
    protected void loadData() {
        mainActivityPresenter.onStart();
    }

    @Override
    public void loadNavHeadView(String imageUrl) {
        ImageView imageView = ViewUtil.findView(nav_main.getHeaderView(0), R.id.headImage);
        ImageLoader.load(MainActivity.this, imageUrl, imageView);
    }

    @Override
    protected void initView() {
        nav_main = findView(R.id.nav_main);
        drawwelayout_main = findView(R.id.main_drawerLayout);
        toolbar_main = findView(R.id.toolbar_main);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mainActivityPresenter = (MainActivityContract.MainActivityPresenter) presenter;
    }
}
