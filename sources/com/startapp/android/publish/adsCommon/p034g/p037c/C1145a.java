package com.startapp.android.publish.adsCommon.p034g.p037c;

import com.appnext.core.C4924Ad;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.startapp.android.publish.adsCommon.g.c.a */
/* compiled from: StartAppSDK */
public final class C1145a {

    /* renamed from: c */
    private static final List<String> f1206c = Arrays.asList(new String[]{C4924Ad.ORIENTATION_PORTRAIT, C4924Ad.ORIENTATION_LANDSCAPE, "none"});

    /* renamed from: a */
    public boolean f1207a;

    /* renamed from: b */
    public int f1208b;

    public C1145a() {
        this(true, 2);
    }

    public C1145a(boolean z, int i) {
        this.f1207a = z;
        this.f1208b = i;
    }

    /* renamed from: a */
    public static int m1559a(String str) {
        int indexOf = f1206c.indexOf(str);
        if (indexOf != -1) {
            return indexOf;
        }
        return 2;
    }
}
