package com.yandex.metrica.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.C1807a;
import com.yandex.metrica.impl.C1830ai;
import com.yandex.metrica.impl.p050ob.C2003bp;
import com.yandex.metrica.impl.p050ob.C2018cc;
import com.yandex.metrica.impl.p050ob.C2019cd;
import com.yandex.metrica.impl.p050ob.C2026ci;
import com.yandex.metrica.impl.p050ob.C2057da;
import com.yandex.metrica.impl.p050ob.C2063df;
import com.yandex.metrica.impl.p050ob.C2064dg;
import com.yandex.metrica.impl.p050ob.C2065dh;
import com.yandex.metrica.impl.p050ob.C2066di;
import com.yandex.metrica.impl.p050ob.C2067dj;
import com.yandex.metrica.impl.p050ob.C2069dl;
import com.yandex.metrica.impl.p050ob.C2190r;

/* renamed from: com.yandex.metrica.impl.bd */
public class C1882bd extends C1830ai {

    /* renamed from: a */
    private final C2018cc f3085a;

    public C1882bd(Context context) {
        this.f3085a = new C2018cc(C2003bp.m5024a(context).mo17287b());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public SparseArray<C1830ai.C1831a> mo16800a() {
        return new SparseArray<C1830ai.C1831a>() {
            {
                put(29, new C1884a((byte) 0));
                put(39, new C1885b((byte) 0));
                put(46, new C1886c());
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo16799a(C2064dg dgVar) {
        int a = dgVar.mo17585a();
        return a == -1 ? this.f3085a.mo17401a(-1) : a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16802a(C2064dg dgVar, int i) {
        this.f3085a.mo17408b(i).mo17398h();
        dgVar.mo17586b().mo17551k();
    }

    /* renamed from: com.yandex.metrica.impl.bd$a */
    private static class C1884a implements C1830ai.C1831a {
        private C1884a() {
        }

        /* synthetic */ C1884a(byte b) {
            this();
        }

        /* renamed from: a */
        public void mo16804a(Context context) {
            String a = new C2066di(context).mo17592a((String) null);
            if (!TextUtils.isEmpty(a) && TextUtils.isEmpty(C2026ci.m5230a().mo17477c(context, a))) {
                C2066di.m5379b(context);
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.bd$b */
    private static class C1885b implements C1830ai.C1831a {
        private C1885b() {
        }

        /* synthetic */ C1885b(byte b) {
            this();
        }

        /* renamed from: a */
        public void mo16804a(Context context) {
            C2063df dfVar = new C2063df(context, context.getPackageName());
            SharedPreferences a = C2069dl.m5401a(context, "_boundentrypreferences");
            String string = a.getString(C2063df.f3477c.mo17604a(), (String) null);
            long j = a.getLong(C2063df.f3478d.mo17604a(), -1);
            if (string != null && j != -1) {
                dfVar.mo17564a(new C1807a.C1808a(string, j)).mo17551k();
                a.edit().remove(C2063df.f3477c.mo17604a()).remove(C2063df.f3478d.mo17604a()).apply();
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.bd$c */
    static class C1886c implements C1830ai.C1831a {
        C1886c() {
        }

        /* renamed from: a */
        public void mo16804a(Context context) {
            C2018cc ccVar = new C2018cc(C2003bp.m5024a(context).mo17287b());
            C2067dj djVar = new C2067dj(context);
            if (djVar.mo17602a()) {
                ccVar.mo17406a(true);
                djVar.mo17603b();
            }
            C2065dh dhVar = new C2065dh(context, context.getPackageName());
            long a = dhVar.mo17588a(0);
            if (a != 0) {
                ccVar.mo17403a(a);
            }
            dhVar.mo17589a();
            C2063df dfVar = new C2063df(context, C2190r.m5777a(context.getPackageName()).toString());
            CounterConfiguration.C1774a b = dfVar.mo17569b();
            if (b != CounterConfiguration.C1774a.UNDEFINED) {
                ccVar.mo17404a(b);
            }
            String b2 = dfVar.mo17570b((String) null);
            if (!TextUtils.isEmpty(b2)) {
                ccVar.mo17409b(b2);
            }
            dfVar.mo17578e().mo17573c().mo17551k();
            ccVar.mo17398h();
            C2057da daVar = new C2057da(context);
            daVar.mo17521a();
            daVar.mo17522b();
            C2026ci.m5230a().mo17477c(context, new C2019cd(C2003bp.m5024a(context).mo17290d(), context.getPackageName()).mo17415a(""));
        }
    }
}
