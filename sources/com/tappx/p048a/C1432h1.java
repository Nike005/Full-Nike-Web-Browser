package com.tappx.p048a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: com.tappx.a.h1 */
public class C1432h1 {

    /* renamed from: a */
    private final Context f1889a;

    public C1432h1(Context context) {
        this.f1889a = context;
    }

    /* renamed from: a */
    public boolean mo15841a() {
        if (!C1366d3.m2453a(this.f1889a, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f1889a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }
}
