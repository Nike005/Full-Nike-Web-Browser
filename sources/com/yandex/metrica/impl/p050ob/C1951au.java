package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;
import com.yandex.metrica.C1796d;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.utils.C2233n;

/* renamed from: com.yandex.metrica.impl.ob.au */
public class C1951au extends C1936af {
    public C1951au(C2198t tVar) {
        super(tVar);
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        mo17182b(hVar);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17182b(C1915h hVar) {
        String k = hVar.mo17132k();
        C1796d a = C2233n.m5974a(k);
        String g = mo17176a().mo17858g();
        C1796d a2 = C2233n.m5974a(g);
        if (!a.equals(a2)) {
            boolean z = true;
            if (TextUtils.isEmpty(a.mo16691a()) && !TextUtils.isEmpty(a2.mo16691a())) {
                hVar.mo17105a(g);
                m4833a(hVar, C2233n.C2234a.LOGOUT);
            } else {
                if (!TextUtils.isEmpty(a.mo16691a()) && TextUtils.isEmpty(a2.mo16691a())) {
                    m4833a(hVar, C2233n.C2234a.LOGIN);
                } else {
                    if (TextUtils.isEmpty(a.mo16691a()) || a.mo16691a().equals(a2.mo16691a())) {
                        z = false;
                    }
                    if (z) {
                        m4833a(hVar, C2233n.C2234a.SWITCH);
                    } else {
                        m4833a(hVar, C2233n.C2234a.UPDATE);
                    }
                }
            }
            mo17176a().mo17843a(k);
        }
        if (!mo17176a().mo17863j().mo16554C()) {
            mo17176a().mo17850c();
        }
    }

    /* renamed from: a */
    private void m4833a(C1915h hVar, C2233n.C2234a aVar) {
        hVar.mo17107c(C2233n.m5975a(aVar));
        mo17176a().mo17853d(hVar);
    }
}
