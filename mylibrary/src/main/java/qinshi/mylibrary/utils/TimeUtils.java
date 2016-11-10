package qinshi.mylibrary.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/3/30.
 */
public class TimeUtils {


    public static String getTime() {


        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
