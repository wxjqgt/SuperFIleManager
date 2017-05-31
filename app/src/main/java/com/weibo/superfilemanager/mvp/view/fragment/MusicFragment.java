package com.weibo.superfilemanager.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.nispok.snackbar.Snackbar;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.mvp.base.BaseFragment;
import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.contract.MusicFragmentContract;
import com.weibo.superfilemanager.mvp.model.Mp3Info;
import com.weibo.superfilemanager.mvp.presenter.MusicFragmentPresenterImp;
import com.weibo.superfilemanager.mvp.view.activity.MainActivity;
import com.weibo.superfilemanager.mvp.view.recyclerView.CommonAdapter;
import com.weibo.superfilemanager.mvp.view.recyclerView.OnRecyclerViewItemClickListener;
import com.weibo.superfilemanager.mvp.view.recyclerView.ViewHolder;
import java.util.List;

public class MusicFragment extends BaseFragment implements MusicFragmentContract.MusicFragmentView {

  private MusicFragmentContract.MusicFragmentPresenter mMusicFragmentPresenter;
  private MainActivity mMainActivity;

  private RecyclerView mRecyclerView_music;

  private List<Mp3Info> mMp3Infos = null;

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
          }
        };
    mRecyclerView_music.setLayoutManager(new LinearLayoutManager(mMainActivity));
    mRecyclerView_music.setAdapter(adapter);
    this.mMp3Infos = mp3Infos;
  }

  @Override protected void listener() {
    mRecyclerView_music.addOnItemTouchListener(
        new OnRecyclerViewItemClickListener(mRecyclerView_music) {
          @Override public void onItemClickLitener(RecyclerView.ViewHolder viewHolder) {
            final Mp3Info mp3Info = mMp3Infos.get(viewHolder.getAdapterPosition());
            AlertDialog alertDialog = new AlertDialog.Builder(mMainActivity).setCancelable(true)
                .setIcon(R.mipmap.ic_music_note_black_24dp)
                .setTitle("Music Message detail")
                .setMessage("Path = " + mp3Info.getUrl())
                .setNeutralButton("exit", new DialogInterface.OnClickListener() {
                  @Override public void onClick(DialogInterface dialog, int which) {
                    Snackbar.with(mMainActivity)
                        .text("exit!")
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .show(mMainActivity);
                  }
                })
                .show();
          }
        });
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
