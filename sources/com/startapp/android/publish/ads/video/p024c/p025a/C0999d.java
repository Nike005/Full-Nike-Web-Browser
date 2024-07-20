package com.startapp.android.publish.ads.video.p024c.p025a;

import android.content.Context;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0991b;
import java.util.Comparator;

/* renamed from: com.startapp.android.publish.ads.video.c.a.d */
/* compiled from: StartAppSDK */
public class C0999d extends C0996c {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final double f827c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final int f828d = (this.f823a * this.f824b);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final int f829e;

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static int m967b(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public C0999d(Context context, int i) {
        super(context);
        this.f829e = i;
        double d = (double) this.f823a;
        double d2 = (double) this.f824b;
        Double.isNaN(d);
        Double.isNaN(d2);
        this.f827c = d / d2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Comparator<C0991b> mo14345a() {
        return new Comparator<C0991b>() {
            /* renamed from: a */
            public int compare(C0991b bVar, C0991b bVar2) {
                double doubleValue = C0999d.this.m965a(bVar.mo14328d().intValue(), bVar.mo14330e().intValue(), C0999d.this.f827c, C0999d.this.f828d).doubleValue();
                double doubleValue2 = C0999d.this.m965a(bVar2.mo14328d().intValue(), bVar2.mo14330e().intValue(), C0999d.this.f827c, C0999d.this.f828d).doubleValue();
                if (doubleValue < doubleValue2) {
                    return -1;
                }
                if (doubleValue > doubleValue2) {
                    return 1;
                }
                Integer c = bVar.mo14325c();
                Integer c2 = bVar2.mo14325c();
                if (c == null && c2 == null) {
                    return 0;
                }
                if (c == null) {
                    return 1;
                }
                if (c2 == null) {
                    return -1;
                }
                return C0999d.m967b(Integer.valueOf(Math.abs(C0999d.this.f829e - c.intValue())).intValue(), Integer.valueOf(Math.abs(C0999d.this.f829e - c2.intValue())).intValue());
            }
        };
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public Double m965a(int i, int i2, double d, int i3) {
        double d2 = (double) i;
        double d3 = (double) i2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = (d2 / d3) / d;
        double d5 = (double) (i * i2);
        double d6 = (double) i3;
        Double.isNaN(d5);
        Double.isNaN(d6);
        return Double.valueOf((Math.abs(Math.log(d4)) * 40.0d) + (Math.abs(Math.log(d5 / d6)) * 60.0d));
    }
}
