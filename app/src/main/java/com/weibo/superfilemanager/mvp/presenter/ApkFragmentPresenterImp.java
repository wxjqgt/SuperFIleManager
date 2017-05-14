package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.mvp.contract.ApkFragmentContract;

/**
 * Created by weibo on 17-5-7.
 */

public class ApkFragmentPresenterImp implements ApkFragmentContract.ApkFragmentPresenter {

  private ApkFragmentContract.ApkFragmentView mApkFragmentView;

  public ApkFragmentPresenterImp(ApkFragmentContract.ApkFragmentView apkFragmentView) {
    mApkFragmentView = apkFragmentView;
    mApkFragmentView.setPresenter(this);
  }

  @Override public void onStart() {

  }
}
