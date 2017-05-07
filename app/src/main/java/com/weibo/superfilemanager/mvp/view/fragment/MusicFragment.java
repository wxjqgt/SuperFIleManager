package com.weibo.superfilemanager.mvp.view.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MusicFragmentContract;
import com.weibo.superfilemanager.mvp.presenter.MusicFragmentPresenterImp;
import com.weibo.superfilemanager.mvp.view.activity.MainActivity;

public class MusicFragment extends BaseFragment implements MusicFragmentContract.MusicFragmentView {

  private MusicFragmentContract.MusicFragmentPresenter mMusicFragmentPresenter;
  private MainActivity mMainActivity;

  private RecyclerView mRecyclerView_music;

  @Override protected void initView() {
    mRecyclerView_music = findView(R.id.musicRecyclerView);
  }

  @Override protected void loadData() {
    mMusicFragmentPresenter.onStart();
  }

  public MusicFragment() {
    new MusicFragmentPresenterImp(this);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.mMainActivity = (MainActivity) context;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_music;
  }

  public static MusicFragment newInstance() {
    MusicFragment fragment = new MusicFragment();
    return fragment;
  }

  @Override public void setPresenter(BasePresenter presenter) {
    mMusicFragmentPresenter = (MusicFragmentContract.MusicFragmentPresenter) presenter;
  }
}
