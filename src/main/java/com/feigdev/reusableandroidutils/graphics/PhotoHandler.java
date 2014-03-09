package com.feigdev.reusableandroidutils.graphics;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import com.feigdev.reusableandroidutils.SimpleFileUtils;

/**
 * http://www.vogella.com/tutorials/AndroidCamera/article.html
 */
public class PhotoHandler implements Camera.PictureCallback {
    private static final String TAG = "PhotoHandler";
    private PhotoCallback photoCallback;


    public PhotoHandler(PhotoCallback photoCallback) {
        super();
        this.photoCallback = photoCallback;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.d(TAG, "onPictureTaken");
        new PersistData().execute(data);
    }

    private class PersistData extends AsyncTask<byte[], Void, String> {

        @Override
        protected String doInBackground(byte[]... params) {
            Log.d(TAG, "PersistData");

            if (null == params || null == params[0])
                return null;

            String photoFile = "Picture_" + System.currentTimeMillis() + ".jpg";

            return SimpleFileUtils.writeRelative(photoFile, params[0]);
        }

        @Override
        protected void onPostExecute(String params) {
            photoCallback.pictureTaken(params);
        }
    }



}
