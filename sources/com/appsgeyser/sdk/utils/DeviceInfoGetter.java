package com.appsgeyser.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public abstract class DeviceInfoGetter {
    private static final String AMPERSAND = "&";
    private static final String ANDROID_VERSION_KEY = "androidversion";
    private static final String CONNECTION_TYPE_KEY = "connectiontype";
    private static final String DEVICE_NAME_KEY = "devicename";
    private static final String EMPTY_STRING = "";
    private static final String IS_TABLET_KEY = "istablet";
    private static final String MANUFACTURER_KEY = "manufacturer";
    private static final int MAX_SIZE = 9;
    private static final String NETWORK_TYPE_UNKNOWN = "unknowntype";
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String OPERATOR_NAME_KEY = "operator";
    private static final String SCREEN_RESOLUTION = "%sx%s";
    private static final String SCREEN_RESOLUTION_KEY = "screenresolution";
    private static final String SCREEN_SIZE_DPI_KEY = "dpi";
    private static final String UTF_8_ENCODING = "utf-8";

    public static String getDeviceInfo(Context context) {
        try {
            return "&dpi=" + URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), UTF_8_ENCODING) + AMPERSAND + SCREEN_RESOLUTION_KEY + "=" + URLEncoder.encode(getScreenResolution(context), UTF_8_ENCODING) + AMPERSAND + ANDROID_VERSION_KEY + "=" + URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), UTF_8_ENCODING) + AMPERSAND + IS_TABLET_KEY + "=" + URLEncoder.encode(String.valueOf(isTablet(context)), UTF_8_ENCODING) + AMPERSAND + MANUFACTURER_KEY + "=" + URLEncoder.encode(getManufacturer(), UTF_8_ENCODING) + AMPERSAND + DEVICE_NAME_KEY + "=" + URLEncoder.encode(getDeviceName(), UTF_8_ENCODING) + AMPERSAND + CONNECTION_TYPE_KEY + "=" + URLEncoder.encode(getConnectType(context), UTF_8_ENCODING) + AMPERSAND + OPERATOR_NAME_KEY + "=" + URLEncoder.encode(getOperatorName(context), UTF_8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static HashMap<String, String> getDeviceInfoMap(Context context) {
        HashMap<String, String> hashMap = new HashMap<>(9);
        try {
            hashMap.put(SCREEN_SIZE_DPI_KEY, URLEncoder.encode(String.valueOf(getScreenSizeDpi(context)), UTF_8_ENCODING));
            hashMap.put(SCREEN_RESOLUTION_KEY, URLEncoder.encode(getScreenResolution(context), UTF_8_ENCODING));
            hashMap.put(ANDROID_VERSION_KEY, URLEncoder.encode(String.valueOf(getAndroidOsVersionInt()), UTF_8_ENCODING));
            hashMap.put(IS_TABLET_KEY, URLEncoder.encode(String.valueOf(isTablet(context)), UTF_8_ENCODING));
            hashMap.put(MANUFACTURER_KEY, URLEncoder.encode(getManufacturer(), UTF_8_ENCODING));
            hashMap.put(DEVICE_NAME_KEY, URLEncoder.encode(getDeviceName(), UTF_8_ENCODING));
            hashMap.put(CONNECTION_TYPE_KEY, URLEncoder.encode(getConnectType(context), UTF_8_ENCODING));
            hashMap.put(OPERATOR_NAME_KEY, URLEncoder.encode(getOperatorName(context), UTF_8_ENCODING));
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getScreenResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return String.format(SCREEN_RESOLUTION, new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)});
    }

    private static int getScreenSizeDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    private static boolean isTablet(Context context) {
        boolean z = (context.getResources().getConfiguration().screenLayout & 15) == 4;
        boolean z2 = (context.getResources().getConfiguration().screenLayout & 15) == 3;
        if (z || z2) {
            return true;
        }
        return false;
    }

    private static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    private static String getDeviceName() {
        return Build.DEVICE;
    }

    private static int getAndroidOsVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    private static String getConnectType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return NETWORK_TYPE_WIFI;
            }
            if (type == 0) {
                return activeNetworkInfo.getSubtypeName();
            }
        }
        return NETWORK_TYPE_UNKNOWN;
    }

    private static String getOperatorName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }
}
