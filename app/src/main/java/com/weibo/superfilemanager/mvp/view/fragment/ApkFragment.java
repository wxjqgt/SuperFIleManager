package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;

public class ApkFragment extends BaseFragment {
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
}
