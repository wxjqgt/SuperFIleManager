package com.weibo.superfilemanager.rxjava2.rxbus.annotation;

import com.weibo.superfilemanager.rxjava2.rxbus.event.ThreadMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Android on 2016/6/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
  @ThreadModes
  int thread() default ThreadMode.MAIN_THREAD;
}
