package com.appsgeyser.sdk.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.google.gson.JsonSyntaxException;

public class PushStarterReceiver extends BroadcastReceiver {
    private static final String ACTION_QUICKBOOT = "android.intent.action.QUICKBOOT_POWERON";
    private static final String ACTION_QUICKBOOT_HTC = "com.htc.intent.action.QUICKBOOT_POWERON";

    public void onReceive(Context context, Intent intent) {
        String prefString;
        if (intent != null && context != null) {
            String action = intent.getAction();
            if ((action.equals("android.intent.action.BOOT_COMPLETED") || action.equals(ACTION_QUICKBOOT) || action.equals("android.intent.action.REBOOT") || action.equals(ACTION_QUICKBOOT_HTC)) && (prefString = new PreferencesCoder(context).getPrefString(Constants.PREFS_SERVER_RESPONSE, (String) null)) != null) {
                ConfigPhp parseFromJson = ConfigPhp.parseFromJson(prefString);
                if (parseFromJson.isPushNotificationsActive()) {
                    try {
                        OneSignalCreator.init(context);
                    } catch (JsonSyntaxException unused) {
                        Log.d("OneSignalStarterRcv", "unexpected JsonSyntaxException");
                    }
                }
                if (parseFromJson.isInactivityReminderEnabled()) {
                    Intent intent2 = new Intent(context, AlarmService.class);
                    intent2.putExtra(AlarmService.DAYS_INACTIVITY, parseFromJson.getInactivityDaysPeriod());
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(intent2);
                    } else {
                        context.startService(intent2);
                    }
                }
            }
        }
    }
}
