package com.feigdev.reusableandroidutils.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ejf3 on 3/23/14.
 */
public class Get {
    private static final String TAG = "Get";
    private static final Gson GSON = new Gson();

    public static JsonObject jsonObject(String url, Class<JsonObject> jsonClass) throws IOException {
        // Deserialize HTTP response to concrete type.
        return GSON.fromJson(getInputStreamReader(url), jsonClass);
    }

    public static ArrayList jsonArray(String url, Type jsonClass) throws IOException {
        // Deserialize HTTP response to concrete type.
        return GSON.fromJson(getInputStreamReader(url), jsonClass);
    }

    private static InputStreamReader getInputStreamReader(String url) throws IOException{
        // Create request for remote resource.
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);

        HttpResponse response  = httpclient.execute(httpGet);

        if (null == response || null == response.getEntity())
            return null;

        InputStream is = response.getEntity().getContent();
        return new InputStreamReader(is);
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
