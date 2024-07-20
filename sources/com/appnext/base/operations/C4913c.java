package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.C4878a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4906j;

/* renamed from: com.appnext.base.operations.c */
public abstract class C4913c extends C4910a {
    /* renamed from: aD */
    public final void mo41037aD() {
    }

    public C4913c(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* renamed from: aC */
    public final void mo41036aC() {
        boolean z = false;
        try {
            Object a = C4906j.m6593a(C4899d.f4628fo, C4899d.C4900a.Boolean);
            if (a != null && (a instanceof Boolean)) {
                z = !((Boolean) a).booleanValue();
            }
            if (mo41038aE()) {
                if (!z) {
                    mo41041av();
                    return;
                }
            }
            mo41033a(new C4878a(C4878a.C4879a.NoPermission$1d8b5b4a));
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: aG */
    public C4899d.C4900a mo41040aG() {
        return C4899d.C4900a.String;
    }
}
