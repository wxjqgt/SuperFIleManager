package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.mvp.contract.MusicFragmentContract;

/**
 * Created by weibo on 17-5-7.
 */

public class MusicFragmentPresenterImp implements MusicFragmentContract.MusicFragmentPresenter {

  private MusicFragmentContract.MusicFragmentView mMusicFragmentView;

  public MusicFragmentPresenterImp(MusicFragmentContract.MusicFragmentView mMusicFragmentView) {
    this.mMusicFragmentView = mMusicFragmentView;
    mMusicFragmentView.setPresenter(this);
  }

  @Override public void onStart() {
    
  }
}
