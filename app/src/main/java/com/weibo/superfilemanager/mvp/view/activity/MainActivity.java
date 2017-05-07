package com.weibo.superfilemanager.mvp.view.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseActivity;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MainActivityContract;
import com.weibo.superfilemanager.mvp.presenter.MainActivityPresenterImp;
import com.weibo.superfilemanager.mvp.view.fragment.ApkFragment;
import com.weibo.superfilemanager.mvp.view.fragment.DocFragment;
import com.weibo.superfilemanager.mvp.view.fragment.MusicFragment;
import com.weibo.superfilemanager.mvp.view.fragment.VideoFragment;
import com.weibo.superfilemanager.util.ImageLoader;
import com.weibo.superfilemanager.util.StatusBarCompat;
import com.weibo.superfilemanager.util.ViewUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView {

  private NavigationView nav_main;
  private DrawerLayout drawwelayout_main;
  private Toolbar toolbar_main;
  private Fragment lastFragment;

  private FragmentManager fm;
  private MainActivityContract.MainActivityPresenter mainActivityPresenter;

  @Override protected void loadData() {
    new MainActivityPresenterImp(this);
    mainActivityPresenter.onStart();
  }

  @Override public void loadNavHeadView(String imageUrl) {
    ImageView imageView = ViewUtil.findView(nav_main.getHeaderView(0), R.id.headImage);
    ImageLoader.load(MainActivity.this, imageUrl, R.mipmap.head_image, imageView);
  }

  @Override protected void initView() {
    nav_main = findView(R.id.nav_main);
    drawwelayout_main = findView(R.id.main_drawerLayout);

    toolbar_main = findView(R.id.toolbar_main);
    toolbar_main.setPadding(0, StatusBarCompat.getStatusBarHeight(this) / 2, 0, 0);
  }

  @Override protected void listener() {
    setSupportActionBar(toolbar_main);
    //一定要在设置了setSupportActionBar(toolbar_main)之后设置点击事件才生效
    RxToolbar.navigationClicks(toolbar_main)
        .compose(this.bindToLifecycle())
        .subscribe(new Consumer<Object>() {
          @Override public void accept(@NonNull Object o) throws Exception {
            drawwelayout_main.openDrawer(Gravity.START);
          }
        });

    fm = getSupportFragmentManager();
    //NavigationView一定要在布局的最后，否则监听不起作用，这是个惨痛的教训
    RxNavigationView.itemSelections(nav_main)
        .compose(this.<MenuItem>bindToLifecycle())
        .subscribe(new Consumer<MenuItem>() {
          @Override public void accept(@NonNull MenuItem menuItem) throws Exception {
            fragment(menuItem.getItemId(), menuItem.getTitle().toString());
            drawwelayout_main.closeDrawer(Gravity.START);
          }
        });
  }

  private void fragment(@IdRes int itemId, String tag) {
    if (lastFragment != null) {
      fm.beginTransaction().hide(lastFragment).commit();
    }
    Fragment fragment = fm.findFragmentByTag(tag);
    if (fragment == null) {
      fragment = createFragment(itemId);
      fm.beginTransaction().add(R.id.mainFrame, fragment, tag).addToBackStack(tag).commit();
    } else {
      fm.beginTransaction().show(fragment).commit();
    }
    lastFragment = fragment;
  }

  private Fragment createFragment(@IdRes int itemId) {
    Fragment fragment = null;
    switch (itemId) {
      case R.id.vdeoItem:
        fragment = VideoFragment.newInstance();
        break;
      case R.id.musicItem:
        fragment = MusicFragment.newInstance();
        break;
      case R.id.docItem:
        fragment = DocFragment.newInstance();
        break;
      case R.id.apkItem:
        fragment = ApkFragment.newInstance();
        break;
      default:
        break;
    }
    return fragment;
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override public void setPresenter(BasePresenter presenter) {
    mainActivityPresenter = (MainActivityContract.MainActivityPresenter) presenter;
  }
}
