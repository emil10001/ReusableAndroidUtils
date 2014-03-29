package com.feigdev.reusableandroidutils.platform;

import android.os.Build;

/**
 * Created by ejf3 on 2/14/14.
 */
public class PlatformUtils {

    public static boolean isAboveKitKat() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT);
    }

    public static boolean isGlass() {
        String model = Build.MODEL;
        if (model.toLowerCase().contains("glass"))
            return true;
        return false;
    }
}
