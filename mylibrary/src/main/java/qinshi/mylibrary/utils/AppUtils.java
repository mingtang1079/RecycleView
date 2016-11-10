package qinshi.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.LinkedList;
import java.util.List;

/**
 * 跟App相关的辅助类
 *
 * @author zhy
 */
public class AppUtils {
    /**
     * 转载Activity的容器
     */
    private static List<Activity> mActivityList = new LinkedList<Activity>();
    private static AppUtils instance = new AppUtils();

    public static synchronized AppUtils getInstance() {

        if (instance == null)
            instance = new AppUtils();
        return instance;
    }


    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
     *
     * @return
     */

    /**
     * 添加Activity实例到mActivityList中，在onCreate()中调用
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * 从容器中删除多余的Activity实例，在onDestroy()中调用
     *
     * @param activity
     */
    public void delActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    /**
     * 退出程序的方法
     */
    public void exit() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }

        System.exit(0);
    }


}
