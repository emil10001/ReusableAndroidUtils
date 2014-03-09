package com.feigdev.reusableandroidutils.graphics;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;

import java.io.File;

/**
 * Modified from original source
 * http://droid-blog.net/2011/10/17/tutorial-how-to-play-animated-gifs-in-android-part-3/
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String gifFilename, String width, String height) {
        super(context);
        try {
            String gifFile = Uri.fromFile(new File(gifFilename)).toString();
            String htmlContent = String.format("<html><body style=\"background-color:#000;\"><img src=\"%s\" width=\"%s\" height=\"%s\"></body></html>",
                    new String[]{gifFile, width, height });
            // use loadDataWithBaseURL so that we don't have any trouble with origins
            // https://developer.android.com/reference/android/webkit/WebView.html#loadDataWithBaseURL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
            loadDataWithBaseURL(gifFile, htmlContent, "text/html", "utf-8", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
