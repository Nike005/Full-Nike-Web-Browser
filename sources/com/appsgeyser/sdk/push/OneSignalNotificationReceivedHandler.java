package com.appsgeyser.sdk.push;

import android.util.Log;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import org.json.JSONObject;

class OneSignalNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    OneSignalNotificationReceivedHandler() {
    }

    public void notificationReceived(OSNotification oSNotification) {
        JSONObject jSONObject = oSNotification.payload.additionalData;
        if (jSONObject != null) {
            Log.i("OneSignalData", jSONObject.toString());
        }
    }
}
