package com.startapp.android.publish.ads.video.p024c.p025a.p026a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.video.c.a.a.e */
/* compiled from: StartAppSDK */
public class C0994e {

    /* renamed from: a */
    private String f793a;

    /* renamed from: b */
    private List<String> f794b;

    /* renamed from: c */
    private List<String> f795c;

    /* renamed from: a */
    public String mo14339a() {
        return this.f793a;
    }

    /* renamed from: a */
    public void mo14340a(String str) {
        this.f793a = str;
    }

    /* renamed from: b */
    public List<String> mo14341b() {
        if (this.f794b == null) {
            this.f794b = new ArrayList();
        }
        return this.f794b;
    }

    /* renamed from: c */
    public List<String> mo14342c() {
        if (this.f795c == null) {
            this.f795c = new ArrayList();
        }
        return this.f795c;
    }

    public String toString() {
        return "VASTVideoClicks [clickThrough=" + this.f793a + ", clickTracking=[" + this.f794b + "], customClick=[" + this.f795c + "] ]";
    }
}
