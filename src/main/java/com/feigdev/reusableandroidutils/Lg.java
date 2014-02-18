package com.feigdev.reusableandroidutils;

import android.util.Log;

/**
 * Created by ejf3 on 2/2/14.
 */
public class Lg {
    public enum LogLevel {
        NONE,
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR
    };
    private static LogLevel LOG_LEVEL = LogLevel.WARNING;

    public static boolean isVerbose() {
        if (LOG_LEVEL == LogLevel.VERBOSE)
            return true;
        return false;
    }

    public static boolean isDebug() {
        if (LOG_LEVEL == LogLevel.VERBOSE
                || LOG_LEVEL == LogLevel.DEBUG)
            return true;
        return false;
    }

    public static boolean isInfo() {
        if (LOG_LEVEL == LogLevel.VERBOSE
                || LOG_LEVEL == LogLevel.DEBUG
                || LOG_LEVEL == LogLevel.INFO)
            return true;
        return false;
    }

    public static boolean isWarning() {
        if (LOG_LEVEL == LogLevel.VERBOSE
                || LOG_LEVEL == LogLevel.DEBUG
                || LOG_LEVEL == LogLevel.INFO
                || LOG_LEVEL == LogLevel.WARNING)
            return true;
        return false;
    }

    public static boolean isError() {
        if (LOG_LEVEL == LogLevel.VERBOSE
                || LOG_LEVEL == LogLevel.DEBUG
                || LOG_LEVEL == LogLevel.INFO
                || LOG_LEVEL == LogLevel.WARNING
                || LOG_LEVEL == LogLevel.ERROR)
            return true;
        return false;
    }

    public static void v(String tag, String message) {
        if (isVerbose())
            Log.v(tag, message);
    }

    public static void d(String tag, String message) {
        if (isDebug())
            Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        if (isInfo())
            Log.i(tag, message);
    }

    public static void w(String tag, String message) {
        if (isWarning())
            Log.w(tag, message);
    }

    public static void w(String tag, String message, Exception e) {
        if (isWarning()) {
            if (null != e)
                Log.w(tag, message, e);
            else
                Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (isError())
            Log.e(tag, message);
    }

    public static void e(String tag, String message, Exception e) {
        if (isError()) {
            if (null != e)
                Log.e(tag, message, e);
            else
                Log.e(tag, message);
        }
    }


}
