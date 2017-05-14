package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.DocFragmentContract;

public class DocFragment extends BaseFragment implements DocFragmentContract.DocFragmentView {

  private DocFragmentContract.DocFragmentPresenter mDocFragmentPresenter;

  public DocFragment() {
    // Required empty public constructor
  }

  @Override public void setPresenter(BasePresenter presenter) {
    mDocFragmentPresenter = (DocFragmentContract.DocFragmentPresenter) presenter;
  }

  public static DocFragment newInstance() {
    DocFragment fragment = new DocFragment();
    return fragment;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_doc;
  }
}
