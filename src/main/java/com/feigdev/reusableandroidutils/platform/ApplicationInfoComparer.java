package com.feigdev.reusableandroidutils.platform;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.Comparator;

/**
 * Created by ejf3 on 3/27/14.
 */
public class ApplicationInfoComparer implements Comparator<ApplicationInfo> {
    private PackageManager pm1;

    public ApplicationInfoComparer(PackageManager pm) {
        pm1 = pm;
    }

    @Override
    public int compare(ApplicationInfo x, ApplicationInfo y) {
        if (null == pm1)
            return 0;

        if (null == x || null == x.loadLabel(pm1))
            return -1;

        if (null == y || null == y.loadLabel(pm1))
            return 1;

        try {
            return compare(x.loadLabel(pm1).toString(), y.loadLabel(pm1).toString());
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    //  Compares two strings lexicographically. Returns an integer indicating whether this string is greater than (result is > 0),
    //  equal to (result is = 0), or less than (result is < 0) the argument.
    private static int compare(String a, String b) {
        return a.compareTo(b);
    }
}