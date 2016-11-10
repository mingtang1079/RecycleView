package qinshi.mylibrary.network;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class ImageLoader {


    public static void load(Context context, @DrawableRes int imageRes, ImageView view) {
        Glide.with(context).load(imageRes).crossFade().into(view);
    }



}
