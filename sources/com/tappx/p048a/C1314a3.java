package com.tappx.p048a;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tappx.sdk.android.TrackInstallReceiver;
import java.util.Set;

/* renamed from: com.tappx.a.a3 */
public class C1314a3 {
    /* renamed from: b */
    private void m2219b(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("token");
            if (stringExtra == null) {
                return;
            }
            if (!stringExtra.isEmpty()) {
                char c = 65535;
                if (stringExtra.hashCode() == 149971738) {
                    if (stringExtra.equals("TAPPX_INSTALL_GETCLASS")) {
                        c = 0;
                    }
                }
                if (c == 0) {
                    C1467j0.m2869a("BroadcastReceiverStackTrace: Start", new Object[0]);
                    for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                        try {
                            if (stackTraceElement.getMethodName().equalsIgnoreCase("onReceive")) {
                                C1467j0.m2869a("BroadcastReceiverStackTrace: " + context.getPackageName() + "/" + stackTraceElement.getClassName() + " :: " + stackTraceElement.getMethodName(), new Object[0]);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            C1467j0.m2869a("no string token", new Object[0]);
            C1467j0.m2871b("ERROR01: " + e.getMessage(), new Object[0]);
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m2220c(Context context, Intent intent) {
        Bundle bundle;
        Set<String> keySet;
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, TrackInstallReceiver.class.getName()), 128);
            if (receiverInfo != null && (bundle = receiverInfo.metaData) != null && (keySet = bundle.keySet()) != null) {
                for (String str : keySet) {
                    if (str != null) {
                        if (!str.isEmpty()) {
                            try {
                                ((BroadcastReceiver) Class.forName(bundle.getString(str)).newInstance()).onReceive(context, intent);
                            } catch (InstantiationException e) {
                                C1467j0.m2871b(e.getMessage(), new Object[0]);
                            } catch (IllegalAccessException e2) {
                                C1467j0.m2871b(e2.getMessage(), new Object[0]);
                            } catch (ClassNotFoundException e3) {
                                C1467j0.m2871b(e3.getMessage(), new Object[0]);
                            }
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e4) {
            C1467j0.m2871b(e4.getMessage(), new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo15527a(Context context, Intent intent) {
        m2219b(context, intent);
        C1332c.m2291a(context).mo15588m().mo16289a(intent);
        m2220c(context, intent);
    }
}
