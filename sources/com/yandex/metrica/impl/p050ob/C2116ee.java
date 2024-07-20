package com.yandex.metrica.impl.p050ob;

import android.telephony.SubscriptionInfo;

/* renamed from: com.yandex.metrica.impl.ob.ee */
public final class C2116ee {

    /* renamed from: a */
    private final Integer f3633a;

    /* renamed from: b */
    private final Integer f3634b;

    /* renamed from: c */
    private final boolean f3635c;

    /* renamed from: d */
    private final String f3636d;

    /* renamed from: e */
    private final String f3637e;

    public C2116ee(Integer num, Integer num2, boolean z, String str, String str2) {
        this.f3633a = num;
        this.f3634b = num2;
        this.f3635c = z;
        this.f3636d = str;
        this.f3637e = str2;
    }

    public C2116ee(SubscriptionInfo subscriptionInfo) {
        this.f3633a = Integer.valueOf(subscriptionInfo.getMcc());
        this.f3634b = Integer.valueOf(subscriptionInfo.getMnc());
        this.f3635c = subscriptionInfo.getDataRoaming() != 1 ? false : true;
        this.f3636d = subscriptionInfo.getCarrierName().toString();
        this.f3637e = subscriptionInfo.getIccId();
    }

    /* renamed from: a */
    public Integer mo17700a() {
        return this.f3633a;
    }

    /* renamed from: b */
    public Integer mo17701b() {
        return this.f3634b;
    }

    /* renamed from: c */
    public boolean mo17702c() {
        return this.f3635c;
    }

    /* renamed from: d */
    public String mo17703d() {
        return this.f3636d;
    }

    /* renamed from: e */
    public String mo17704e() {
        return this.f3637e;
    }
}
