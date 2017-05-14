package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.mvp.contract.VideoFragmentContract;

/**
 * Created by weibo on 17-5-7.
 */

public class VideoFragmentPresenterImp implements VideoFragmentContract.VideoFragmentPresenter {

  private VideoFragmentContract.VideoFragmentView mVideoFragmentView;

  public VideoFragmentPresenterImp(VideoFragmentContract.VideoFragmentView videoFragmentView) {
    this.mVideoFragmentView = videoFragmentView;
    mVideoFragmentView.setPresenter(this);
  }

  @Override public void onStart() {

  }
}
