package com.weibo.superfilemanager.mvp.contract;

import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.base.BaseView;
import com.weibo.superfilemanager.mvp.model.Mp3Info;
import java.util.List;

/**
 * Created by weibo on 17-5-7.
 */

public class MusicFragmentContract {
  public interface MusicFragmentPresenter extends BasePresenter {
  }

  public interface MusicFragmentView extends BaseView {
    void viewData(List<Mp3Info> mp3Infos);
  }
}
