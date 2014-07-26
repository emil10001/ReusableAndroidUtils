package io.hearty.utils.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;
import io.nbadal.gifencoder.AnimatedGifEncoder;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ejf3 on 3/8/14.
 */
public class ImageTools {
    private static final String TAG = "ImageTools";

    public static void makeGif(ArrayList<String> listOfFiles, String outGif) {
        if (null == listOfFiles || null == outGif)
            return;

        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (String filename : listOfFiles) {
            File f = new File(filename);
            if (!f.exists())
                continue;

            bitmaps.add(generatePic(filename));
        }
        byte[] bytes = generateGIF(bitmaps);
        if (null == bytes)
            return;

        try {
            FileOutputStream outStream = new FileOutputStream(outGif);
            outStream.write(bytes);
            outStream.close();
            Log.d(TAG, "wrote file " + outGif);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Bitmap bitmap : bitmaps){
            bitmap.recycle();
        }
    }

    public static byte[] generateGIF(ArrayList<Bitmap> bitmaps) {
        if (null == bitmaps)
            return null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.setRepeat(0);
        encoder.setDelay(250);
        encoder.start(bos);
        for (Bitmap bitmap : bitmaps) {
            encoder.addFrame(bitmap);
        }
        encoder.finish();
        return bos.toByteArray();
    }

    public static Bitmap generatePic(String filename) {
        if (null == filename)
            return null;

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeFile(filename);
            Log.d(TAG, "generated image");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getPic OutOfMemoryError =(");
            return null;
        } catch (Exception e) {
            Log.w(TAG, "getPic blew up =(");
            return null;
        }

        return bmp;
    }

    public static Bitmap shrinkBitmap(byte[] data) {
        if (null == data)
            return null;

        Bitmap bmp = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
            Log.d(TAG, "generated image");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getPic OutOfMemoryError =(");
            return null;
        } catch (Exception e) {
            Log.w(TAG, "getPic blew up =(");
            return null;
        }

        return shrinkBitmap(bmp);
    }

    public static Bitmap shrinkBitmap(Bitmap bmp) {
        if (null == bmp)
            return null;

        try {
            int width = bmp.getWidth();
            int height = bmp.getHeight();

            int newWidth = 1200;

            float scaleWidth = ((float) newWidth) / width;
            float newHeight = height * scaleWidth;
            float scaleHeight = newHeight / height;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);

            if (null != bmp2 && (getBitmapSize(bmp) < getBitmapSize(bmp2))) {
                bmp.recycle();
                bmp = bmp2;
            } else
                bmp2.recycle();

            Log.d(TAG, "generated image 2");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getPic2 OutOfMemoryError =(");
        } catch (Exception e) {
            Log.w(TAG, "getPic2 blew up =(");
        }

        return bmp;
    }

    public static byte[] shrinkBitmapToBytes(Bitmap bmp) {
        return bitmapToByteArray(shrinkBitmap(bmp));
    }

    public static byte[] bitmapToByteArray(Bitmap bmp) {
        if (null == bmp)
            return null;

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] getImageBytesFromFile(String filename) {
        Bitmap bmp = generatePic(filename);
        byte[] bytes = bitmapToByteArray(bmp);
        bmp.recycle();
        return bytes;
    }

    public static int getBitmapSize(Bitmap data) {
        return data.getByteCount();
    }

    // https://groups.google.com/forum/#!msg/android-developers/JwZG-Tchlog/iaLxLqeGEnQJ
    public static Bitmap getBitmapFromUri(Context context, Uri streamUri) {
        if (null == context || null == streamUri)
            return null;

        ContentResolver cr = context.getContentResolver();
        InputStream is;

        try {
            is = cr.openInputStream(streamUri);
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMimeType(String filename) {
        return getMimeType(getImageBytesFromFile(filename));
    }

    public static String getMimeType(Bitmap bmp) {
        return getMimeType(bitmapToByteArray(bmp));
    }

    // http://stackoverflow.com/a/19739471/974800
    public static String getMimeType(byte[] data) {
        if (null == data)
            return null;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            String mime = options.outMimeType;
            Log.d(TAG, "getMimeTypeOfBitmap: " + mime);
            return mime;
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getMimeTypeOfBitmap OutOfMemoryError =(");
        } catch (Exception e) {
            Log.w(TAG, "getMimeTypeOfBitmap blew up =(");
        }

        return null;
    }
}
