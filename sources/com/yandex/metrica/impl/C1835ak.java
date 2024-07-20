package com.yandex.metrica.impl;

import com.yandex.metrica.impl.p050ob.C2045cq;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.ak */
public abstract class C1835ak {

    /* renamed from: d */
    protected String f2943d;

    /* renamed from: e */
    protected String f2944e;

    /* renamed from: f */
    protected int f2945f = 1;

    /* renamed from: g */
    protected byte[] f2946g;

    /* renamed from: h */
    protected int f2947h;

    /* renamed from: i */
    protected byte[] f2948i;

    /* renamed from: j */
    protected Map<String, List<String>> f2949j;

    /* renamed from: k */
    protected boolean f2950k = false;

    /* renamed from: l */
    protected int f2951l = -1;

    /* renamed from: com.yandex.metrica.impl.ak$a */
    static final class C1836a {

        /* renamed from: a */
        static final long f2952a = TimeUnit.SECONDS.toMillis(5);

        /* renamed from: b */
        static final long f2953b = TimeUnit.SECONDS.toMillis(15);
    }

    /* renamed from: b */
    public abstract boolean mo16821b();

    /* renamed from: c */
    public abstract boolean mo16822c();

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public abstract C2045cq mo16823d();

    /* renamed from: f */
    public void mo16825f() {
    }

    /* renamed from: g */
    public void mo16826g() {
    }

    /* renamed from: n */
    public boolean mo16833n() {
        return false;
    }

    /* renamed from: o */
    public abstract boolean mo16834o();

    /* renamed from: p */
    public long mo16835p() {
        return 0;
    }

    /* renamed from: e */
    public void mo16824e() {
        this.f2951l++;
    }

    /* renamed from: h */
    public String mo16827h() {
        return this.f2943d;
    }

    /* renamed from: a */
    public void mo16816a(String str) {
        this.f2943d = str;
    }

    /* renamed from: i */
    public int mo16828i() {
        return this.f2945f;
    }

    /* renamed from: j */
    public byte[] mo16829j() {
        return this.f2946g;
    }

    /* renamed from: a */
    public void mo16818a(byte[] bArr) {
        this.f2945f = 2;
        this.f2946g = bArr;
    }

    /* renamed from: k */
    public int mo16830k() {
        return this.f2947h;
    }

    /* renamed from: a */
    public void mo16815a(int i) {
        this.f2947h = i;
    }

    /* renamed from: b */
    public void mo16820b(byte[] bArr) {
        this.f2948i = bArr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public Map<String, List<String>> mo16831l() {
        return this.f2949j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16817a(Map<String, List<String>> map) {
        this.f2949j = map;
    }

    /* renamed from: m */
    public String mo16832m() {
        return this.f2944e;
    }

    /* renamed from: b */
    public void mo16819b(String str) {
        this.f2944e = str;
    }

    /* renamed from: a */
    public String mo16814a() {
        return getClass().getName();
    }

    /* renamed from: q */
    public int mo16836q() {
        return this.f2951l;
    }

    /* renamed from: r */
    public boolean mo16837r() {
        return this.f2950k;
    }
}
