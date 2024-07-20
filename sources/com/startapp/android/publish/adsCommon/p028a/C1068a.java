package com.startapp.android.publish.adsCommon.p028a;

import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.adsCommon.a.a */
/* compiled from: StartAppSDK */
public class C1068a implements Comparable<C1068a> {

    /* renamed from: a */
    private long f1003a = System.currentTimeMillis();

    /* renamed from: b */
    private AdPreferences.Placement f1004b;

    /* renamed from: c */
    private String f1005c;

    public C1068a(AdPreferences.Placement placement, String str) {
        this.f1004b = placement;
        this.f1005c = str == null ? "" : str;
    }

    /* renamed from: a */
    public AdPreferences.Placement mo14649a() {
        return this.f1004b;
    }

    /* renamed from: b */
    public String mo14650b() {
        return this.f1005c;
    }

    /* renamed from: a */
    public int compareTo(C1068a aVar) {
        long j = this.f1003a - aVar.f1003a;
        if (j > 0) {
            return 1;
        }
        return j == 0 ? 0 : -1;
    }

    public String toString() {
        return "AdDisplayEvent [displayTime=" + this.f1003a + ", placement=" + this.f1004b + ", adTag=" + this.f1005c + "]";
    }
}
