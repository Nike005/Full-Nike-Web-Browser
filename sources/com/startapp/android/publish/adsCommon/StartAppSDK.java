package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.content.Context;
import com.startapp.android.publish.common.metaData.MetaDataRequest;

public class StartAppSDK {
    public static void init(Activity activity, String str) {
        init(activity, str, new SDKAdPreferences());
    }

    public static void init(Activity activity, String str, String str2) {
        init(activity, str, str2, new SDKAdPreferences());
    }

    public static void init(Activity activity, String str, SDKAdPreferences sDKAdPreferences) {
        C1174m.m1649a().mo14977a((Context) activity, (String) null, str, sDKAdPreferences, true);
    }

    public static void init(Activity activity, String str, String str2, SDKAdPreferences sDKAdPreferences) {
        C1174m.m1649a().mo14977a((Context) activity, str, str2, sDKAdPreferences, true);
    }

    public static void init(Activity activity, String str, boolean z) {
        init(activity, str, new SDKAdPreferences(), z);
    }

    public static void init(Activity activity, String str, String str2, boolean z) {
        init(activity, str, str2, new SDKAdPreferences(), z);
    }

    public static void init(Activity activity, String str, SDKAdPreferences sDKAdPreferences, boolean z) {
        C1174m.m1649a().mo14977a((Context) activity, (String) null, str, sDKAdPreferences, z);
    }

    public static void init(Activity activity, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        C1174m.m1649a().mo14977a((Context) activity, str, str2, sDKAdPreferences, z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2) {
        init(context, str, str2, new SDKAdPreferences());
    }

    @Deprecated
    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences) {
        C1174m.m1649a().mo14977a(context, str, str2, sDKAdPreferences, true);
    }

    @Deprecated
    public static void init(Context context, String str, boolean z) {
        init(context, (String) null, str, z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2, boolean z) {
        init(context, str, str2, new SDKAdPreferences(), z);
    }

    @Deprecated
    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        C1174m.m1649a().mo14977a(context, str, str2, sDKAdPreferences, z);
    }

    public static void inAppPurchaseMade(Context context) {
        inAppPurchaseMade(context, 0.0d);
    }

    public static void inAppPurchaseMade(Context context, double d) {
        C1166k.m1613b(context, "payingUser", (Boolean) true);
        double floatValue = (double) C1166k.m1607a(context, "inAppPurchaseAmount", Float.valueOf(0.0f)).floatValue();
        Double.isNaN(floatValue);
        C1166k.m1614b(context, "inAppPurchaseAmount", Float.valueOf((float) (floatValue + d)));
        C1174m.m1649a().mo14974a(context, MetaDataRequest.C1224a.IN_APP_PURCHASE);
    }

    public static void startNewSession(Context context) {
        C1174m.m1649a().mo14974a(context, MetaDataRequest.C1224a.CUSTOM);
    }

    public static void addWrapper(Context context, String str, String str2) {
        C1174m.m1649a().mo14976a(context, str, str2);
    }

    public static void enableReturnAds(boolean z) {
        C1174m.m1649a().mo14996e(z);
    }

    private static void pauseServices(Context context) {
        C1174m.m1649a().mo14973a(context);
        C1174m.m1649a().mo14983b(context);
    }

    private static void resumeServices(Context context) {
        C1174m.m1649a().mo14987c(context);
        C1174m.m1649a().mo14991d(context);
    }

    public static void setUserConsent(Context context, String str, long j, boolean z) {
        C1174m.m1649a().mo14975a(context, str, j, z, true);
    }
}
