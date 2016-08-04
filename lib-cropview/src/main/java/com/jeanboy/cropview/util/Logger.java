package com.jeanboy.cropview.util;

@SuppressWarnings("unused")
public class Logger {
    private static final String TAG = "CropView";
    public static boolean enabled = false;

    public static void e(String msg) {
        if (!enabled) return;
        android.util.Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable e) {
        if (!enabled) return;
        android.util.Log.e(TAG, msg, e);
    }
}
