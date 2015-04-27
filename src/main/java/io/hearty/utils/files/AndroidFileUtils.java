package io.hearty.utils.files;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.*;

/**
 * Created by ejf3 on 3/9/14.
 */
public class AndroidFileUtils extends SimpleFileUtils {
    private static final String TAG = "SimpleFileUtils";

    public static File getFileInDir(File dir, String filename) {
        return new File(dir, filename);
    }

    public static File getFileInDefaultDir(String filename) {
        return new File(getDir(), filename);
    }

    public static File getDir() {
        String sdDir = Environment.getExternalStorageDirectory().toString();
        File relativeDir = new File(sdDir, "FeigdevUtils");

        if (!relativeDir.exists())
            relativeDir.mkdirs();

        Log.d(TAG, relativeDir.toString() + " exists? " + relativeDir.exists());
        return relativeDir;
    }

    public static File getCacheDir(Context c){
        File cacheDir = c.getExternalCacheDir();

        if (null == cacheDir)
            cacheDir = c.getCacheDir();

        if (!cacheDir.exists())
            cacheDir.mkdirs();

        return cacheDir;
    }

    public static String getSdDir() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static String writeRelative(String relativeFilename, byte[] data) {
        File relativeDir = getDir();

        if (!relativeDir.exists()) {
            Log.e(TAG, "Couldn't make directory");
            return null;
        }

        String filename = relativeDir.getPath() + File.separator + relativeFilename;
        write(filename,data);
        return filename;
    }

    public static String writeCache(Context context, String relativeFilename, byte[] data) {
        File relativeDir = getCacheDir(context);

        if (!relativeDir.exists()) {
            Log.e(TAG, "Couldn't make directory");
            return null;
        }

        String filename = relativeDir.getPath() + File.separator + relativeFilename;
        write(filename,data);
        return filename;
    }

}
