package qinshi.mylibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {




    /**
     * Get a thumbnail bitmap.
     * @param uri
     * @return a thumbnail bitmap
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Bitmap getThumbnail(Context context, Uri uri, int thumbnailSize) throws
            FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > thumbnailSize) ? (originalSize / thumbnailSize) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;// optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        System.out.println("要发送的图片的高度是"+bitmap.getHeight());
        System.out.println("要发送的图片的宽度是"+bitmap.getWidth());
        input.close();
        return bitmap;
    }

    /**
     * Resolve the best value for inSampleSize attribute.
     * @param ratio
     * @return
     */
    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }










    public static Bitmap getlittlebm(String path) {

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, option);
        System.out.println("图片的宽度：" + option.outWidth);
        option.inSampleSize = option.outWidth / 10;
        // option.outHeight=option.outHeight/beilv;
        // option.outWidth=50;
        System.out.println(option.outHeight);
        System.out.println(option.outWidth);
        option.inJustDecodeBounds = false;
        option.inInputShareable = true;
        option.inPurgeable = true;
        option.inPreferredConfig = Bitmap.Config.ARGB_4444;
        Bitmap bitmap = BitmapFactory.decodeFile(path, option);
        System.out.println(bitmap.getHeight());
        System.out.println(bitmap.getWidth());
        return bitmap;

    }


    public static String getBitmaPathByUri(Context context, Uri uri) {

        System.out.println(4);
        String img[] = {MediaStore.Images.Media.DATA};

        CursorLoader loader = new CursorLoader(context, uri, img, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);


    }




}
