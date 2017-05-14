package com.weibo.superfilemanager.mvp.view.recyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.weibo.superfilemanager.R;
import com.weibo.superfilemanager.util.ImageLoader;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

  private View mView;
  private Context context;

  private SparseArrayCompat<View> views = new SparseArrayCompat<>();

  public ViewHolder(View mView, Context context) {
    super(mView);
    this.mView = mView;
    this.context = context;
  }

  public static ViewHolder createViewHolder(Context context, View itemView) {
    ViewHolder holder = new ViewHolder(itemView, context);
    return holder;
  }

  public <T extends View> T getView(int id) {
    View view = views.get(id);
    if (view == null) {
      view = itemView.findViewById(id);
      views.put(id, view);
    }
    return (T) view;
  }

  public void setText(int id, String text) {
    TextView textView = getView(id);
    textView.setText(text);
  }

  public void setTextAndColor(int id, String text, int color) {
    TextView textView = getView(id);
    textView.setText(text);
    textView.setTextColor(color);
  }

  public void setImageViewSrc(int id, String url) {
    ImageView imageView = getView(id);
    ImageLoader.load(context, url, R.mipmap.head_image, imageView);
  }

  public void setRatingBarStars(int id, int rating) {
    RatingBar ratingBar = getView(id);
    ratingBar.setRating(rating);
  }

  public void setLayoutBgColor(int id, String color) {
    getView(id).setBackgroundColor(Color.parseColor(color));
  }
}
