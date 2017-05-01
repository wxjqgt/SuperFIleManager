package com.weibo.superfilemanager.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.Volley_Go.VolleyGo;
import com.weibo.superfilemanager.Volley_Go.client.HttpCallback;
import com.weibo.superfilemanager.constant.UrlConstant;
import com.weibo.superfilemanager.util.ImageLoader;
import com.weibo.superfilemanager.util.StatusBarCompat;
import com.weibo.superfilemanager.util.ViewUtil;
import com.weibo.superfilemanager.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private NavigationView nav_main;
    private DrawerLayout drawwelayout_main;
    private Toolbar toolbar_main;

    @Override
    protected void loadData() {
        VolleyGo.get()
                .url(UrlConstant.IMAGE_URL)
                .callback(new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        try {
                            JSONObject jsonObject = new JSONObject(t);
                            JSONArray array = jsonObject.getJSONArray("results");
                            String imagUrl = array.getJSONObject(0).getString("url");
                            ImageView imageView = ViewUtil.findView(nav_main.getHeaderView(0), R.id.headImage);
                            ImageLoader.load(MainActivity.this, imagUrl, imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).doTask();
    }

    @Override
    protected void initView() {
        nav_main = findView(R.id.nav_main);
        drawwelayout_main = findView(R.id.main_drawerLayout);
        toolbar_main = findView(R.id.toolbar_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_main.setTitleMarginTop(
                StatusBarCompat.getStatusBarHeight(this)
        );
        setSupportActionBar(toolbar_main);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
