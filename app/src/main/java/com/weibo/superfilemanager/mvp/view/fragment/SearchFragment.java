package com.weibo.superfilemanager.mvp.view.fragment;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.SearchFragmentContract;

/**
 * Created by weibo on 17-5-14.
 */

public class SearchFragment extends BaseFragment
    implements SearchFragmentContract.SearchFragmentView {

  private SearchFragmentContract.SearchFragmentPresenter mSearchFragmentPresenter;

  public SearchFragment() {
  }

  @Override protected void initView() {

  }

  @Override protected void listener() {

  }

  @Override protected void loadData() {

  }

  @Override public void setPresenter(BasePresenter presenter) {
    mSearchFragmentPresenter = (SearchFragmentContract.SearchFragmentPresenter) presenter;
  }

  public static SearchFragment newInstance() {
    SearchFragment fragment = new SearchFragment();
    return fragment;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_search;
  }
}
