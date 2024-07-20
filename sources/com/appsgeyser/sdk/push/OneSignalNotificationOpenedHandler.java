package com.appsgeyser.sdk.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

class OneSignalNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private static final String MESSAGE_KEY = "msg";
    private final Context context;

    OneSignalNotificationOpenedHandler(Context context2) {
        this.context = context2;
    }

    public void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult) {
        if (oSNotificationOpenResult.notification.payload.additionalData != null) {
            String str = oSNotificationOpenResult.notification.payload.title;
            String optString = oSNotificationOpenResult.notification.payload.additionalData.optString("msg");
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(optString)) {
                MessageViewer.launchWithContent(this.context, str, optString);
                return;
            }
            return;
        }
        startActivity();
    }

    private void startActivity() {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        launchIntentForPackage.addFlags(67108864);
        launchIntentForPackage.addFlags(268435456);
        launchIntentForPackage.addFlags(2097152);
        this.context.startActivity(launchIntentForPackage);
    }
}
