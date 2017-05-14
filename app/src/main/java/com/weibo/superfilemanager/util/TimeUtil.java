package com.weibo.superfilemanager.util;

/**
 * Created by weibo on 17-5-14.
 */

public final class TimeUtil {
  //格式化时间
  public static String formattime(long time) {
    String min = time / (1000 * 60) + "";
    String sec = time % (1000 * 60) + "";
    if (min.length() < 2) {
      min = "0" + time / (1000 * 60) + "";
    } else {
      min = time / (1000 * 60) + "";
    }
    if (sec.length() == 4) {
      sec = "0" + time % (1000 * 60) + "";
    } else if (sec.length() == 3) {
      sec = "00" + time % (1000 * 60) + "";
    } else if (sec.length() == 2) {
      sec = "000" + time % (1000 * 60) + "";
    } else if (sec.length() == 1) {
      sec = "0000" + time % (1000 * 60) + "";
    }
    return min + ":" + sec.trim().substring(0, 2);
  }
}
