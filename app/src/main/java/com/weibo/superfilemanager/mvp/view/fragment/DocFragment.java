package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;

public class DocFragment extends BaseFragment {

  public DocFragment() {
    // Required empty public constructor
  }

  public static DocFragment newInstance() {
    DocFragment fragment = new DocFragment();
    return fragment;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_doc;
  }
}
