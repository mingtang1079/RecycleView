package qinshi.mylibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class BaseApplication extends Application {
    public static Application mAppContext = null;


    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;

    }


    public static Context getInstance() {
        return mAppContext;
    }
}
