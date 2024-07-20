package com.yandex.metrica.impl;

import android.content.Context;
import android.util.SparseArray;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.p050ob.C2064dg;

/* renamed from: com.yandex.metrica.impl.ai */
public abstract class C1830ai {

    /* renamed from: com.yandex.metrica.impl.ai$a */
    interface C1831a {
        /* renamed from: a */
        void mo16804a(Context context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo16799a(C2064dg dgVar);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract SparseArray<C1831a> mo16800a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo16802a(C2064dg dgVar, int i);

    /* renamed from: a */
    public void mo16801a(Context context) {
        C2064dg dgVar = new C2064dg(context);
        int a = mo16799a(dgVar);
        int b = mo16803b();
        if (a < b) {
            SparseArray<C1831a> a2 = mo16800a();
            while (a <= b) {
                C1831a aVar = a2.get(a);
                if (aVar != null) {
                    aVar.mo16804a(context);
                }
                a++;
            }
            mo16802a(dgVar, b);
            dgVar.mo17551k();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo16803b() {
        return YandexMetrica.getLibraryApiLevel();
    }
}
