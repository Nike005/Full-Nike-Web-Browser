package com.truenet.android.p049a;

import android.content.Context;
import android.net.NetworkInfo;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.e */
/* compiled from: StartAppSDK */
public final class C1749e {

    /* renamed from: a */
    private final NetworkInfo f2661a;

    /* renamed from: b */
    private final boolean f2662b;

    /* renamed from: c */
    private final boolean f2663c;

    /* renamed from: d */
    private final boolean f2664d;

    /* renamed from: e */
    private final String f2665e;

    /* renamed from: f */
    private final Context f2666f;

    public C1749e(Context context) {
        NetworkInfo networkInfo;
        String typeName;
        NetworkInfo networkInfo2;
        C2928h.m6157b(context, "context");
        this.f2666f = context;
        NetworkInfo activeNetworkInfo = C1753h.m3862a(context, "android.permission.ACCESS_NETWORK_STATE") ? C1748d.m3851a(this.f2666f).getActiveNetworkInfo() : null;
        this.f2661a = activeNetworkInfo;
        boolean z = false;
        boolean isConnected = activeNetworkInfo != null ? activeNetworkInfo.isConnected() : false;
        this.f2662b = isConnected;
        NetworkInfo networkInfo3 = this.f2661a;
        this.f2663c = networkInfo3 != null && isConnected && networkInfo3.getType() == 1;
        NetworkInfo networkInfo4 = this.f2661a;
        if (networkInfo4 != null && this.f2662b && networkInfo4.getType() == 0) {
            z = true;
        }
        this.f2664d = z;
        String str = "";
        if (!z ? !(!this.f2663c || (networkInfo = this.f2661a) == null || (typeName = networkInfo.getTypeName()) == null) : !((networkInfo2 = this.f2661a) == null || (typeName = networkInfo2.getSubtypeName()) == null)) {
            str = typeName;
        }
        this.f2665e = str;
    }

    /* renamed from: a */
    public final String mo16518a() {
        return this.f2665e;
    }
}
