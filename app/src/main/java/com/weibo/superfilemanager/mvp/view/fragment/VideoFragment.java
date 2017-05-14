package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.VideoFragmentContract;

public class VideoFragment extends BaseFragment implements VideoFragmentContract.VideoFragmentView {

  private VideoFragmentContract.VideoFragmentPresenter mVideoFragmentPresenter;

  public VideoFragment() {
    // Required empty public constructor
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_video;
  }

  public static VideoFragment newInstance() {
    VideoFragment fragment = new VideoFragment();
    return fragment;
  }

  @Override public void setPresenter(BasePresenter presenter) {
    mVideoFragmentPresenter = (VideoFragmentContract.VideoFragmentPresenter) presenter;
  }
}
