package com.startapp.android.publish.adsCommon.p034g.p036b;

import android.content.Context;
import android.os.Build;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.adsCommon.g.b.b */
/* compiled from: StartAppSDK */
public class C1144b {

    /* renamed from: a */
    private Context f1204a;

    /* renamed from: b */
    private List<String> f1205b = new ArrayList();

    public C1144b(Context context) {
        this.f1204a = context.getApplicationContext();
    }

    /* renamed from: a */
    public boolean mo14929a() {
        boolean z = this.f1205b.contains("calendar") && Build.VERSION.SDK_INT >= 14 && C1261c.m2031a(this.f1204a, "android.permission.WRITE_CALENDAR");
        C1270g.m2078a("MraidNativeFeatureManager", 3, "isCalendarSupported " + z);
        return z;
    }

    /* renamed from: b */
    public boolean mo14931b() {
        boolean contains = this.f1205b.contains("inlineVideo");
        C1270g.m2078a("MraidNativeFeatureManager", 3, "isInlineVideoSupported " + contains);
        return contains;
    }

    /* renamed from: c */
    public boolean mo14932c() {
        boolean z = this.f1205b.contains("sms") && C1261c.m2031a(this.f1204a, "android.permission.SEND_SMS");
        C1270g.m2078a("MraidNativeFeatureManager", 3, "isSmsSupported " + z);
        return z;
    }

    /* renamed from: d */
    public boolean mo14933d() {
        boolean contains = this.f1205b.contains("storePicture");
        C1270g.m2078a("MraidNativeFeatureManager", 3, "isStorePictureSupported " + contains);
        return contains;
    }

    /* renamed from: e */
    public boolean mo14934e() {
        boolean z = this.f1205b.contains("tel") && C1261c.m2031a(this.f1204a, "android.permission.CALL_PHONE");
        C1270g.m2078a("MraidNativeFeatureManager", 3, "isTelSupported " + z);
        return z;
    }

    /* renamed from: f */
    public List<String> mo14935f() {
        return this.f1205b;
    }

    /* renamed from: a */
    public boolean mo14930a(String str) {
        return mo14935f().contains(str);
    }
}
