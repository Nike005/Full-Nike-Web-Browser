package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.C1830ai;
import com.yandex.metrica.impl.p050ob.C2014bz;
import com.yandex.metrica.impl.p050ob.C2062de;
import com.yandex.metrica.impl.p050ob.C2064dg;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.f */
public class C1910f extends C1830ai {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C2014bz f3160a;

    public C1910f(C2014bz bzVar) {
        this.f3160a = bzVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public SparseArray<C1830ai.C1831a> mo16800a() {
        return new SparseArray<C1830ai.C1831a>() {
            {
                put(46, new C1912a(C1910f.this.f3160a));
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo16799a(C2064dg dgVar) {
        return (int) this.f3160a.mo17331c(-1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16802a(C2064dg dgVar, int i) {
        this.f3160a.mo17340f((long) i);
        dgVar.mo17587c().mo17551k();
    }

    /* renamed from: com.yandex.metrica.impl.f$a */
    static class C1912a implements C1830ai.C1831a {

        /* renamed from: a */
        private C2014bz f3162a;

        /* renamed from: a */
        private static boolean m4721a(long j, long j2, long j3) {
            return j != j3 && j2 == j3;
        }

        public C1912a(C2014bz bzVar) {
            this.f3162a = bzVar;
        }

        /* renamed from: a */
        public void mo16804a(Context context) {
            C2062de deVar = new C2062de(context);
            if (C1897bk.m4653a((Map) deVar.mo17559c())) {
                return;
            }
            if (this.f3162a.mo17327a((String) null) == null || this.f3162a.mo17329b((String) null) == null) {
                String b = deVar.mo17557b((String) null);
                if (m4722a(b, this.f3162a.mo17329b((String) null))) {
                    this.f3162a.mo17342g(b);
                }
                String a = deVar.mo17553a();
                if (m4722a(a, this.f3162a.mo17326a())) {
                    this.f3162a.mo17346k(a);
                }
                String a2 = deVar.mo17554a((String) null);
                if (m4722a(a2, this.f3162a.mo17327a((String) null))) {
                    this.f3162a.mo17341f(a2);
                }
                String c = deVar.mo17558c((String) null);
                if (m4722a(c, this.f3162a.mo17333c((String) null))) {
                    this.f3162a.mo17343h(c);
                }
                String d = deVar.mo17560d((String) null);
                if (m4722a(d, this.f3162a.mo17335d((String) null))) {
                    this.f3162a.mo17344i(d);
                }
                String e = deVar.mo17561e((String) null);
                if (m4722a(e, this.f3162a.mo17339e((String) null))) {
                    this.f3162a.mo17345j(e);
                }
                long a3 = deVar.mo17552a(-1);
                if (m4721a(a3, this.f3162a.mo17324a(-1), -1)) {
                    this.f3162a.mo17334d(a3);
                }
                long b2 = deVar.mo17555b(-1);
                if (m4721a(b2, this.f3162a.mo17328b(-1), -1)) {
                    this.f3162a.mo17338e(b2);
                }
                this.f3162a.mo17398h();
                deVar.mo17556b().mo17551k();
            }
        }

        /* renamed from: a */
        private static boolean m4722a(String str, String str2) {
            return !TextUtils.isEmpty(str) && TextUtils.isEmpty(str2);
        }
    }
}
