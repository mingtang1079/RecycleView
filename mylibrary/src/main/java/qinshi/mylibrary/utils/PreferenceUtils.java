package qinshi.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    static int mode = Context.MODE_PRIVATE;

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static String getPrefString(Context context, String key,
                                       final String defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getString(key, defaultValue);
    }

    public static String getPrefString(Context context, String name,
                                       String key, final String defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);

        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key,
                                     final String value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putString(key, value).commit();
    }

    public static void setPrefString(Context context, String name,
                                     final String key, final String value) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean getPrefBoolean(Context context, String name,
                                         final String key, final boolean defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        return settings.getBoolean(key, defaultValue);
    }

    public static void setPrefBoolean(Context context, String name,
                                      final String key, final boolean value) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context, String key,
                                  int value) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).commit();
    }

    public static void setPrefInt(Context context, String name,
                                  final String key, final int value) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getInt(key, defaultValue);
    }

    public static int getPrefInt(Context context, String name,
                                 final String key, final int defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key,
                                    final float value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putFloat(key, value).commit();
    }

    public static void setPrefFloat(Context context, String name,
                                    final String key, final float value) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        settings.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context, final String key,
                                     final float defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getFloat(key, defaultValue);
    }

    public static float getPrefFloat(Context context, String name,
                                     final String key, final float defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key,
                                      final long value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putLong(key, value).commit();
    }

    public static void setSettingLong(Context context, String name,
                                      final String key, final long value) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key,
                                   final long defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getLong(key, defaultValue);
    }

    public static long getPrefLong(Context context, String name,
                                   final String key, final long defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(name,
                mode);
        return settings.getLong(key, defaultValue);
    }

    public static void clearDefaultPreference(Context context) {
        final SharedPreferences p = PreferenceManager
                .getDefaultSharedPreferences(context);
        final Editor editor = p.edit();
        editor.clear();
        System.out.println("清楚默认的pref数据");
        editor.commit();
    }

    public static void clearPreference(Context context, final String name) {
        final SharedPreferences p = context.getSharedPreferences(name, mode);
        final Editor editor = p.edit();
        editor.clear();
        System.out.println("清楚有名字的的pref数据");
        editor.commit();
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key);
    }

}
