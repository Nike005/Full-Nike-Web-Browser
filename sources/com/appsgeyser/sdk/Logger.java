package com.appsgeyser.sdk;

import android.util.Log;
import com.appsgeyser.sdk.configuration.Constants;

public class Logger {
    public static void DebugLog(String str) {
        Log.d(Constants.LOG_DEBUG_TAG, str);
    }

    static void ErrorLog(String str) {
        Log.e(Constants.LOG_ERROR_TAG, str);
    }

    public static void InfoLog(String str) {
        Log.i(Constants.LOG_INFO_TAG, str);
    }
}
