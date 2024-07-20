package com.p088b.p089a.p090a.p091a.p095c;

import android.content.Context;
import android.os.Handler;
import com.p088b.p089a.p090a.p091a.p092a.C5104b;
import com.p088b.p089a.p090a.p091a.p092a.C5105c;
import com.p088b.p089a.p090a.p091a.p092a.C5106d;
import com.p088b.p089a.p090a.p091a.p092a.C5107e;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p095c.C5121b;
import com.p088b.p089a.p090a.p091a.p100h.C5148a;

/* renamed from: com.b.a.a.a.c.e */
public class C5127e implements C5105c, C5121b.C5123a {

    /* renamed from: a */
    private static C5127e f4982a;

    /* renamed from: b */
    private float f4983b = 0.0f;

    /* renamed from: c */
    private final C5107e f4984c;

    /* renamed from: d */
    private final C5104b f4985d;

    /* renamed from: e */
    private C5106d f4986e;

    /* renamed from: f */
    private C5120a f4987f;

    public C5127e(C5107e eVar, C5104b bVar) {
        this.f4984c = eVar;
        this.f4985d = bVar;
    }

    /* renamed from: a */
    public static C5127e m7086a() {
        if (f4982a == null) {
            f4982a = new C5127e(new C5107e(), new C5104b());
        }
        return f4982a;
    }

    /* renamed from: e */
    private C5120a m7087e() {
        if (this.f4987f == null) {
            this.f4987f = C5120a.m7050a();
        }
        return this.f4987f;
    }

    /* renamed from: a */
    public void mo41812a(float f) {
        this.f4983b = f;
        for (C5118i f2 : m7087e().mo41868c()) {
            f2.mo41851f().mo41904a(f);
        }
    }

    /* renamed from: a */
    public void mo41894a(Context context) {
        this.f4986e = this.f4984c.mo41816a(new Handler(), context, this.f4985d.mo41811a(), this);
    }

    /* renamed from: a */
    public void mo41877a(boolean z) {
        if (z) {
            C5148a.m7174a().mo41925b();
        } else {
            C5148a.m7174a().mo41927d();
        }
    }

    /* renamed from: b */
    public void mo41895b() {
        C5121b.m7057a().mo41872a((C5121b.C5123a) this);
        C5121b.m7057a().mo41873b();
        if (C5121b.m7057a().mo41875d()) {
            C5148a.m7174a().mo41925b();
        }
        this.f4986e.mo41813a();
    }

    /* renamed from: c */
    public void mo41896c() {
        C5148a.m7174a().mo41926c();
        C5121b.m7057a().mo41874c();
        this.f4986e.mo41814b();
    }

    /* renamed from: d */
    public float mo41897d() {
        return this.f4983b;
    }
}
