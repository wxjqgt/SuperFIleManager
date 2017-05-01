package com.weibo.superfilemanager.mvp.view.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseActivity;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MainActivityContract;
import com.weibo.superfilemanager.mvp.presenter.MainActivityPresenterImp;
import com.weibo.superfilemanager.util.ImageLoader;
import com.weibo.superfilemanager.util.StatusBarCompat;
import com.weibo.superfilemanager.util.ViewUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView {

    private NavigationView nav_main;
    private DrawerLayout drawwelayout_main;
    private Toolbar toolbar_main;

    private MainActivityContract.MainActivityPresenter mainActivityPresenter;

    @Override
    protected void loadData() {
        new MainActivityPresenterImp(this);
        mainActivityPresenter.onStart();
    }

    @Override
    public void loadNavHeadView(String imageUrl) {
        ImageView imageView = ViewUtil.findView(nav_main.getHeaderView(0), R.id.headImage);
        ImageLoader.load(MainActivity.this, imageUrl, R.mipmap.head_image, imageView);
    }

    @Override
    protected void initView() {
        nav_main = findView(R.id.nav_main);
        drawwelayout_main = findView(R.id.main_drawerLayout);

        toolbar_main = findView(R.id.toolbar_main);
        toolbar_main.setPadding(0, StatusBarCompat.getStatusBarHeight(this) / 2, 0, 0);
    }

    @Override
    protected void listener() {
        setSupportActionBar(toolbar_main);
        //一定要在设置了setSupportActionBar(toolbar_main)之后设置点击事件才生效
        RxToolbar.navigationClicks(toolbar_main)
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        drawwelayout_main.openDrawer(Gravity.START);
                    }
                });
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
