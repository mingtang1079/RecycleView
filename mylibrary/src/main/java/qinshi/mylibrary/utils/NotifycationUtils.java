package qinshi.mylibrary.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;


/**
 * Created by Administrator on 2015/9/7.
 */
public class NotifycationUtils {


    public static void showNormal(Context context, int icon, String title, String content, int id) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(content)
                .build();
        nm.notify(id, notification);
    }

    public static void clear(Context mContext, int id) {
        NotificationManager nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(id);
    }


    public static void clearAll(Context mContext) {
        NotificationManager nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancelAll();
    }
}
