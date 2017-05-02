package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;

public class MusicFragment extends BaseFragment{

  public MusicFragment() {
    // Required empty public constructor
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_music;
  }

  public static MusicFragment newInstance() {
    MusicFragment fragment = new MusicFragment();
    return fragment;
  }

}
