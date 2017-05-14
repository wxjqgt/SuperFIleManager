package com.weibo.superfilemanager.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import static com.bumptech.glide.Glide.with;

/**
 * Created by Administrator on 2016/5/6.
 */
public final class ImageLoader {

  public static void load(Activity activity, int res, int errorRes, ImageView imageView) {
    with(activity).load(res).error(errorRes).into(imageView);
  }

  public static void load(Context context, String url, int errorRes, ImageView imageView) {
    with(context).load(url).error(errorRes).into(imageView);
  }

  public static void load(Activity activity, String url, int errorRes, ImageView imageView) {
    Glide.with(activity).load(url).error(errorRes).into(imageView);
  }

}
