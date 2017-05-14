package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.mvp.contract.DocFragmentContract;

/**
 * Created by weibo on 17-5-7.
 */

public class DocFragmentPresenterImp implements DocFragmentContract.DocFragmentPresenter {

  private DocFragmentContract.DocFragmentView mDocFragmentView;

  public DocFragmentPresenterImp(DocFragmentContract.DocFragmentView docFragmentView) {
    mDocFragmentView = docFragmentView;
    mDocFragmentView.setPresenter(this);
  }

  @Override public void onStart() {

  }
}
