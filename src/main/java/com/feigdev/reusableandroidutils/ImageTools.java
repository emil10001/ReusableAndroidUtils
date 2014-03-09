package com.feigdev.reusableandroidutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import com.nbadal.gifencoder.AnimatedGifEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by ejf3 on 3/8/14.
 */
public class ImageTools {
    private static final String TAG = "ImageTools";

    public static void makeGif(ArrayList<String> listOfFiles, String outGif){
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (String filename : listOfFiles){
            File f = new File(filename);
            if (!f.exists())
                continue;

            bitmaps.add(generatePic(filename));
        }
        byte[] bytes = generateGIF(bitmaps);
        if (null == bytes)
            return;

        try{
            FileOutputStream outStream = new FileOutputStream(outGif);
            outStream.write(bytes);
            outStream.close();
            Log.d(TAG, "wrote file " + outGif);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] generateGIF(ArrayList<Bitmap> bitmaps) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.setDelay(250);
        encoder.start(bos);
        for (Bitmap bitmap : bitmaps) {
            encoder.addFrame(bitmap);
        }
        encoder.finish();
        return bos.toByteArray();
    }

    public static Bitmap generatePic(String filename) {
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
        Bitmap bmp = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length,options);
            Log.d(TAG, "generated image");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getPic OutOfMemoryError =(");
            return null;
        } catch (Exception e) {
            Log.w(TAG, "getPic blew up =(");
            return null;
        }

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

            if (null != bmp2 && (getBitmapSize(bmp) < getBitmapSize(bmp2)))
                bmp = bmp2;

            Log.d(TAG, "generated image 2");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "getPic2 OutOfMemoryError =(");
        } catch (Exception e) {
            Log.w(TAG, "getPic2 blew up =(");
        }

        return bmp;
    }

    public static byte[] bitmapToByteArray(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static int getBitmapSize(Bitmap data) {
        return data.getByteCount();
    }
}
