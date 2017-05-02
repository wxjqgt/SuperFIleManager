package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;

public class VideoFragment extends BaseFragment{

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

}
