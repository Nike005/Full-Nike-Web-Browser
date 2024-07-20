package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.os.Build;
import com.yandex.metrica.impl.utils.C2219d;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.cx */
public class C2053cx {
    /* renamed from: a */
    public List<C2052cw> mo17517a(Context context, List<C2052cw> list) {
        List<C2052cw> a = mo17516a(context).mo17511a();
        if (C2219d.m5933a(a, list)) {
            return null;
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2051cv mo17516a(Context context) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new C2054cy(context);
        }
        return new C2055cz(context);
    }
}
