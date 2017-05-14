package com.weibo.superfilemanager.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * @version 1.0
 *          封装了线程调度
 *          Created by Android on 2016/6/16.
 */
public final class RxSchedulersHelper {

  public static <T> FlowableTransformer<T, T> io_mainF() {
    return new FlowableTransformer<T, T>() {
      @Override public Publisher<T> apply(@NonNull Flowable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static <T> ObservableTransformer<T, T> io_mainO() {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource apply(@NonNull Observable upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static <T> FlowableTransformer<T, T> computation_mainF() {
    return new FlowableTransformer<T, T>() {
      @Override public Publisher<T> apply(@NonNull Flowable<T> upstream) {
        return upstream.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static <T> ObservableTransformer<T, T> computation_mainO() {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource apply(@NonNull Observable upstream) {
        return upstream.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }
}
