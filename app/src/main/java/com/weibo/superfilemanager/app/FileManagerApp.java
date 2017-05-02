package com.weibo.superfilemanager.app;

import android.app.Application;
import com.orhanobut.logger.Logger;

/**
 * Created by weibo on 17-5-1.
 */

public class FileManagerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("sout");
    }
}
