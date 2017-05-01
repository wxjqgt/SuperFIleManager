package com.weibo.superfilemanager.mvp.presenter;

import com.weibo.superfilemanager.Volley_Go.VolleyGo;
import com.weibo.superfilemanager.Volley_Go.client.HttpCallback;
import com.weibo.superfilemanager.constant.UrlConstant;
import com.weibo.superfilemanager.mvp.contract.MainActivityContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by weibo on 17-5-1.
 */

public class MainActivityPresenterImp implements MainActivityContract.MainActivityPresenter {

    private MainActivityContract.MainActivityView view;

    public MainActivityPresenterImp(MainActivityContract.MainActivityView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void onStart() {
        VolleyGo.get()
                .url(UrlConstant.IMAGE_URL)
                .callback(new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        try {
                            JSONObject jsonObject = new JSONObject(t);
                            JSONArray array = jsonObject.getJSONArray("results");
                            String imagUrl = array.getJSONObject(0).getString("url");
                            view.loadNavHeadView(imagUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).doTask();
    }
}
