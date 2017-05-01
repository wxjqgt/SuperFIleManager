package com.weibo.superfilemanager.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.weibo.superfilemanager.util.StatusBarCompat;

/**
 * Created by weibo on 17-3-5.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            StatusBarCompat.compat(this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected void loadData() {
    }

    protected <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

}
