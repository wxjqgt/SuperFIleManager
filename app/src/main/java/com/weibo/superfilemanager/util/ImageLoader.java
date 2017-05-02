package com.weibo.superfilemanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/6.
 */
public final class ImageLoader {

  public static void load(Context context, int res, int errorRes, ImageView imageView) {
    Picasso.with(context.getApplicationContext()).load(res).error(errorRes).into(imageView);
  }

  public static void load(Context context, String url, int errorRes, ImageView imageView) {
    Picasso.with(context.getApplicationContext()).load(url).error(errorRes).into(imageView);
  }

  public static Bitmap getBitmap(Context context, int errorRes, String url) {
    try {
      return Picasso.with(context.getApplicationContext()).load(url).error(errorRes).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
