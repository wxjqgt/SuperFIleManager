package com.weibo.superfilemanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/6.
 */
public final class ImageLoader {

    public static void load(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }

    public static void load(Context context, int res, ImageView imageView) {
        Picasso.with(context).load(res).into(imageView);
    }

    public static void load(Fragment fragment, String url, ImageView imageView) {
        Picasso.with(fragment.getContext()).load(url).into(imageView);
    }

    public static void load(Fragment fragment, int res, ImageView imageView) {
        Picasso.with(fragment.getContext()).load(res).into(imageView);
    }
    public static Bitmap getBitmap(Context context,String url){
        try {
            return Picasso.with(context).load(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
