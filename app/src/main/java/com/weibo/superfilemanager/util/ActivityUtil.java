package com.weibo.superfilemanager.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

/**
 * Created by weibo on 17-5-13.
 */

public final class ActivityUtil {
  public static void launchActivity(Activity from, Class to) {
    from.startActivity(new Intent(from, to));
  }

  public static void launchActivity(Activity from, Class to, View view, String element) {
    ActivityOptionsCompat optionsCompat =
        ActivityOptionsCompat.makeSceneTransitionAnimation(from, view, element);
    ActivityCompat.startActivity(from, new Intent(from, to), optionsCompat.toBundle());
  }
}
