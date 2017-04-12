package com.touchtechnologies.startdagger.utils;

import android.util.Log;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class LogUtil {

    private static final boolean hidden = false;

    //private static final boolean hidden = true;

    public static void d(String tag, String message) {
        if (!hidden) Log.d(tag, String.valueOf(message));
    }

    public static void e(String tag, String message) {
        if (!hidden) Log.e(tag, String.valueOf(message));
    }

    public static void w(String tag, String message) {
        if (!hidden) Log.w(tag, String.valueOf(message));
    }

    public static void v(String tag, String message) {
        if (!hidden) Log.v(tag, String.valueOf(message));
    }

    public static void i(String tag, String message) {
        if (!hidden) Log.i(tag, String.valueOf(message));
    }

}
