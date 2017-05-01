package com.weibo.superfilemanager.mvp.contract;

import com.weibo.superfilemanager.mvp.base.BasePresenter;
import com.weibo.superfilemanager.mvp.base.BaseView;

/**
 * Created by weibo on 17-5-1.
 */

public class MainActivityContract {
    public interface MainActivityPresenter extends BasePresenter{

    }
    public interface MainActivityView extends BaseView{
        void loadNavHeadView(String imageUrl);
    }
}
