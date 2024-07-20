package com.startapp.p005a.p006a.p011e;

import com.startapp.p005a.p006a.p007a.C0802c;
import java.io.DataInput;

/* renamed from: com.startapp.a.a.e.a */
/* compiled from: StartAppSDK */
public class C0816a extends C0819d {

    /* renamed from: a */
    private final int f351a;

    /* renamed from: b */
    private final int f352b;

    public C0816a(int i, int i2) {
        this.f351a = i;
        this.f352b = i2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0802c mo13701a(DataInput dataInput) {
        C0802c cVar = new C0802c((long) (this.f351a * this.f352b));
        mo13707a(dataInput, cVar, (long) cVar.mo13677b());
        return cVar;
    }
}
