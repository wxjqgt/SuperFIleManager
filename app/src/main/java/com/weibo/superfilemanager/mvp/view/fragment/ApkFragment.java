package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.ApkFragmentContract;

public class ApkFragment extends BaseFragment implements ApkFragmentContract.ApkFragmentView {

  private ApkFragmentContract.ApkFragmentPresenter mApkFragmentPresenter;

  public ApkFragment() {
    // Required empty public constructor
  }

  public static ApkFragment newInstance() {
    ApkFragment fragment = new ApkFragment();
    return fragment;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_apk;
  }

  @Override public void setPresenter(BasePresenter presenter) {
    mApkFragmentPresenter = (ApkFragmentContract.ApkFragmentPresenter) presenter;
  }
}
