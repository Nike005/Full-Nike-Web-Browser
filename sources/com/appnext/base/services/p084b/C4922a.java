package com.appnext.base.services.p084b;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.services.p083a.C4918a;
import com.appnext.base.services.p083a.C4919b;
import com.appnext.base.services.p083a.C4920c;
import java.util.List;

/* renamed from: com.appnext.base.services.b.a */
public class C4922a {

    /* renamed from: eM */
    private static volatile C4922a f4679eM;

    /* renamed from: eN */
    private C4920c f4680eN;

    public C4922a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f4680eN = new C4919b(context);
            } else {
                this.f4680eN = new C4918a(context);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: d */
    public static C4922a m6693d(Context context) {
        if (f4679eM == null) {
            synchronized (C4922a.class) {
                if (f4679eM == null) {
                    f4679eM = new C4922a(context);
                }
            }
        }
        return f4679eM;
    }

    /* renamed from: a */
    public final void mo41070a(C4887c cVar, boolean z) {
        this.f4680eN.mo41066a(cVar, z, (Bundle) null);
    }

    /* renamed from: c */
    public final void mo41071c(C4887c cVar) {
        this.f4680eN.mo41067c(cVar);
    }

    /* renamed from: h */
    public final void mo41072h(List<C4887c> list) {
        this.f4680eN.mo41068h(list);
    }
}
