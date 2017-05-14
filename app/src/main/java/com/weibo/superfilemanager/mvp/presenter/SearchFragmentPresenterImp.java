package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.mvp.contract.SearchFragmentContract;

/**
 * Created by weibo on 17-5-7.
 */

public class SearchFragmentPresenterImp implements SearchFragmentContract.SearchFragmentPresenter {
  private SearchFragmentContract.SearchFragmentView mSearchFragmentView;

  public SearchFragmentPresenterImp(SearchFragmentContract.SearchFragmentView searchFragmentView) {
    mSearchFragmentView = searchFragmentView;
    mSearchFragmentView.setPresenter(this);
  }

  @Override public void onStart() {

  }
}
