package com.weibo.superfilemanager.mvp.view.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MusicFragmentContract;
import com.weibo.superfilemanager.mvp.model.Mp3Info;
import com.weibo.superfilemanager.mvp.presenter.MusicFragmentPresenterImp;
import com.weibo.superfilemanager.mvp.view.activity.MainActivity;
import com.weibo.superfilemanager.mvp.view.recyclerView.CommonAdapter;
import com.weibo.superfilemanager.mvp.view.recyclerView.ViewHolder;
import java.util.List;

public class MusicFragment extends BaseFragment implements MusicFragmentContract.MusicFragmentView {

  private MusicFragmentContract.MusicFragmentPresenter mMusicFragmentPresenter;
  private MainActivity mMainActivity;

  private RecyclerView mRecyclerView_music;

  public MusicFragment() {
    new MusicFragmentPresenterImp(this);
  }

  @Override protected void loadData() {
    mMusicFragmentPresenter.onStart();
  }

  @Override public void viewData(List<Mp3Info> mp3Infos) {
    CommonAdapter adapter =
        new CommonAdapter<Mp3Info>(mMainActivity, R.layout.music_recycleview_item_layout,
            mp3Infos) {
          @Override public void convert(ViewHolder holder, Mp3Info mp3Info, int position) {
            holder.setText(R.id.tv_mp3Name, mp3Info.getTitle());
            holder.setText(R.id.tv_mp3Path, mp3Info.getUrl());
          }
        };
    mRecyclerView_music.setLayoutManager(new LinearLayoutManager(mMainActivity));
    mRecyclerView_music.setAdapter(adapter);
  }

  @Override protected void listener() {
  }

  @Override protected void initView() {
    mRecyclerView_music = findView(R.id.musicRecyclerView);
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

  @Override public void onDestroy() {
    super.onDestroy();
    mMusicFragmentPresenter = null;
  }
}
