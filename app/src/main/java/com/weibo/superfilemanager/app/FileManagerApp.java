package com.weibo.superfilemanager.app;

import android.app.Application;
import android.content.Context;
import com.orhanobut.logger.Logger;

/**
 * Created by weibo on 17-5-1.
 */

public class FileManagerApp extends Application {

    public static Context mContext;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("sout");
        mContext = this;
    }
}
