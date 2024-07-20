package com.appsgeyser.sdk.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import com.appsgeyser.sdk.configuration.PreferencesCoder;

public class VersionManager {
    private static final String APP_VERSION_ALIAS = "app_version_prefix";
    public static final int DEFAULT_VERSION = -1;

    public static int getPreviousVersion(Context context) {
        return new PreferencesCoder(context).getPrefInt(APP_VERSION_ALIAS, -1);
    }

    public static int getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static void updateVersion(Context context, int i) {
        new PreferencesCoder(context).savePrefInt(APP_VERSION_ALIAS, i);
    }
}
