package qinshi.mylibrary.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by drakeet on 8/17/15.
 */
public class ShareUtils {

    public static void share(Context context, int text) {
        shared(context, context.getString(text));
    }

    public static void share(Context context, String text) {
        shared(context, text);
    }

    public static void shared(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, extraText);
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, "分享"));
    }

    public static void shared(Context context, String extraText, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, extraText);
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, title));
    }
}
