package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;

public final class AppsgeyserSDK {

    public interface OfferWallEnabledListener {
        void isOfferWallEnabled(boolean z);
    }

    public interface OnAboutDialogEnableListener {
        void onDialogEnableReceived(boolean z);
    }

    public interface OnInAppPurchasesEnableListener {
        void onInAppPurchasesEnableReceived(boolean z);
    }

    public static void takeOff(Activity activity, String str, String str2, String str3) {
        InternalEntryPoint.getInstance().takeOff(activity, str, str2, str3);
    }

    public static void enablePull() {
        InternalEntryPoint.getInstance().enablePull();
    }

    public static FastTrackAdsController getFastTrackAdsController() {
        return InternalEntryPoint.getInstance().getFastTrackAdsController();
    }

    public static void onPause(Context context) {
        InternalEntryPoint.getInstance().onPause(context);
    }

    public static void onResume(Context context) {
        InternalEntryPoint.getInstance().onResume(context);
    }

    public static void onDestroy(Context context) {
        InternalEntryPoint.getInstance().onDestroy(context);
    }

    public static void showAboutDialog(Activity activity) {
        InternalEntryPoint.getInstance().showAboutDialog(activity);
    }

    public static void launchDisableAdsBillingFlow(Activity activity) {
        InternalEntryPoint.getInstance().launchDisableAdsBillingFlow(activity);
    }

    public static void isAboutDialogEnabled(Context context, OnAboutDialogEnableListener onAboutDialogEnableListener) {
        InternalEntryPoint.getInstance().getNewConfigPhp(context, onAboutDialogEnableListener);
    }

    public static void areInAppPurchasesEnabled(Context context, OnInAppPurchasesEnableListener onInAppPurchasesEnableListener) {
        InternalEntryPoint.getInstance().getNewConfigPhp(context, onInAppPurchasesEnableListener);
    }

    public static void isOfferWallEnabled(Context context, OfferWallEnabledListener offerWallEnabledListener) {
        InternalEntryPoint.getInstance().checkIsOfferWallEnabled(context, offerWallEnabledListener);
    }

    public static void addNetworkListener(Context context, OnNetworkStateChangedListener onNetworkStateChangedListener) {
        InternalEntryPoint.getInstance().addNetworkListener(onNetworkStateChangedListener, context);
    }

    public static void setApplicationInstance(Application application) {
        InternalEntryPoint.getInstance().setApplication(application);
    }

    public static boolean isConsentRequestProcessActive() {
        return InternalEntryPoint.getInstance().isConsentRequestProcessActive();
    }

    public static void setAfterConsentRequestCompletedListener(InternalEntryPoint.AfterConsentRequestListener afterConsentRequestListener) {
        InternalEntryPoint.getInstance().setAfterConsentRequestCompletedListener(afterConsentRequestListener);
    }

    public static String getAdditionalJsCode() {
        return InternalEntryPoint.getInstance().getAdditionalJsCode();
    }

    public static String getUserCountrySIM(Context context) {
        return InternalEntryPoint.getInstance().getUserCountrySIM(context);
    }

    public static String getUserCountryLocale(Context context) {
        return InternalEntryPoint.getInstance().getUserCountryLocale(context);
    }
}
