package com.feigdev.reusableandroidutils.net;

import android.util.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by ejf3 on 3/23/14.
 */
public class FileUpload {
    private static String TAG = "FileUpload";

    public static int putFile(String url, byte[] bytes) throws IOException{
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-Type","application/octet-stream");

        ByteArrayEntity entity = new ByteArrayEntity(bytes);

        httpPut.setEntity(entity);

        Header[] headers = httpPut.getAllHeaders();
        for (Header header : headers)
            Log.d(TAG, header.toString());

        HttpResponse response = httpclient.execute(httpPut);

        if (null == response)
            return 0;

        Log.d(TAG, "response: " + response.getStatusLine());
        HttpEntity responseEntity = response.getEntity();

        if (null != responseEntity)
            Log.d(TAG, "" + Get.convertStreamToString(responseEntity.getContent()));

        if (null == response.getStatusLine())
            return 0;

        return response.getStatusLine().getStatusCode();
    }

}
