package com.yandex.metrica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1902bo;
import com.yandex.metrica.impl.utils.C2228j;
import java.util.HashSet;
import java.util.Set;

public final class MetricaEventHandler extends BroadcastReceiver {

    /* renamed from: a */
    public static final Set<BroadcastReceiver> f2725a = new HashSet();

    /* renamed from: a */
    static void m3974a(BroadcastReceiver... broadcastReceiverArr) {
        for (BroadcastReceiver add : broadcastReceiverArr) {
            f2725a.add(add);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("referrer");
            if (!C1894bi.m4622a(stringExtra)) {
                C1902bo.m4678b(context).mo17093b(stringExtra);
            }
        }
        for (BroadcastReceiver next : f2725a) {
            C2228j.m5960f().mo17902a(String.format("Sending referrer to %s", new Object[]{next.getClass().getName()}));
            next.onReceive(context, intent);
        }
    }
}
