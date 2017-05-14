package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.app.FileManagerApp;
import com.weibo.superfilemanager.mvp.contract.MusicFragmentContract;
import com.weibo.superfilemanager.mvp.model.Mp3Info;
import com.weibo.superfilemanager.mvp.view.fragment.MusicFragment;
import com.weibo.superfilemanager.rxjava2.RxSchedulersHelper;
import com.weibo.superfilemanager.util.FileUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

/**
 * Created by weibo on 17-5-7.
 */

public class MusicFragmentPresenterImp implements MusicFragmentContract.MusicFragmentPresenter {

  private MusicFragmentContract.MusicFragmentView mMusicFragmentView;
  private MusicFragment mMusicFragment;

  public MusicFragmentPresenterImp(MusicFragmentContract.MusicFragmentView mMusicFragmentView) {
    this.mMusicFragmentView = mMusicFragmentView;
    mMusicFragment = (MusicFragment) mMusicFragmentView;
    mMusicFragmentView.setPresenter(this);
  }

  @Override public void onStart() {
    Observable.just(FileUtil.getMp3InfoList(FileManagerApp.mContext))
        .compose(mMusicFragment.<ArrayList<Mp3Info>>bindToLifecycle())
        .compose(RxSchedulersHelper.<ArrayList<Mp3Info>>io_mainO())
        .subscribe(new Consumer<ArrayList<Mp3Info>>() {
          @Override public void accept(ArrayList<Mp3Info> mp3Infos) throws Exception {
            mMusicFragmentView.viewData(mp3Infos);
          }
        });
  }
}
