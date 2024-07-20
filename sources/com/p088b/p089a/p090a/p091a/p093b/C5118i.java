package com.p088b.p089a.p090a.p091a.p093b;

import android.view.View;
import com.p088b.p089a.p090a.p091a.p095c.C5120a;
import com.p088b.p089a.p090a.p091a.p095c.C5127e;
import com.p088b.p089a.p090a.p091a.p097e.C5139e;
import com.p088b.p089a.p090a.p091a.p098f.C5141a;
import com.p088b.p089a.p090a.p091a.p099g.C5143a;
import com.p088b.p089a.p090a.p091a.p099g.C5145b;
import com.p088b.p089a.p090a.p091a.p099g.C5146c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/* renamed from: com.b.a.a.a.b.i */
public class C5118i extends C5111b {

    /* renamed from: a */
    private final C5113d f4956a;

    /* renamed from: b */
    private final C5112c f4957b;

    /* renamed from: c */
    private final List<C5141a> f4958c = new ArrayList();

    /* renamed from: d */
    private C5141a f4959d;

    /* renamed from: e */
    private C5143a f4960e;

    /* renamed from: f */
    private boolean f4961f = false;

    /* renamed from: g */
    private boolean f4962g = false;

    /* renamed from: h */
    private String f4963h;

    /* renamed from: i */
    private boolean f4964i;

    C5118i(C5112c cVar, C5113d dVar) {
        this.f4957b = cVar;
        this.f4956a = dVar;
        this.f4963h = UUID.randomUUID().toString();
        m7024e((View) null);
        this.f4960e = dVar.mo41840f() == C5114e.HTML ? new C5145b(dVar.mo41837c()) : new C5146c(dVar.mo41836b(), dVar.mo41839e());
        this.f4960e.mo41903a();
        C5120a.m7050a().mo41865a(this);
        this.f4960e.mo41908a(cVar);
    }

    /* renamed from: c */
    private C5141a m7022c(View view) {
        for (C5141a next : this.f4958c) {
            if (next.get() == view) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: d */
    private void m7023d(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    /* renamed from: e */
    private void m7024e(View view) {
        this.f4959d = new C5141a(view);
    }

    /* renamed from: f */
    private void m7025f(View view) {
        Collection<C5118i> b = C5120a.m7050a().mo41866b();
        if (b != null && b.size() > 0) {
            for (C5118i next : b) {
                if (next != this && next.mo41853h() == view) {
                    next.f4959d.clear();
                }
            }
        }
    }

    /* renamed from: n */
    private void m7026n() {
        if (this.f4964i) {
            throw new IllegalStateException("Impression event can only be sent once");
        }
    }

    /* renamed from: a */
    public void mo41828a() {
        if (!this.f4961f) {
            this.f4961f = true;
            C5120a.m7050a().mo41867b(this);
            this.f4960e.mo41904a(C5127e.m7086a().mo41897d());
            this.f4960e.mo41909a(this, this.f4956a);
        }
    }

    /* renamed from: a */
    public void mo41829a(View view) {
        if (!this.f4962g) {
            C5139e.m7136a((Object) view, "AdView is null");
            if (mo41853h() != view) {
                m7024e(view);
                mo41851f().mo41922i();
                m7025f(view);
            }
        }
    }

    /* renamed from: b */
    public void mo41830b() {
        if (!this.f4962g) {
            this.f4959d.clear();
            mo41848c();
            this.f4962g = true;
            mo41851f().mo41920g();
            C5120a.m7050a().mo41869c(this);
            mo41851f().mo41914b();
            this.f4960e = null;
        }
    }

    /* renamed from: b */
    public void mo41831b(View view) {
        if (!this.f4962g) {
            m7023d(view);
            if (m7022c(view) == null) {
                this.f4958c.add(new C5141a(view));
            }
        }
    }

    /* renamed from: c */
    public void mo41848c() {
        if (!this.f4962g) {
            this.f4958c.clear();
        }
    }

    /* renamed from: d */
    public List<C5141a> mo41849d() {
        return this.f4958c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo41850e() {
        m7026n();
        mo41851f().mo41921h();
        this.f4964i = true;
    }

    /* renamed from: f */
    public C5143a mo41851f() {
        return this.f4960e;
    }

    /* renamed from: g */
    public String mo41852g() {
        return this.f4963h;
    }

    /* renamed from: h */
    public View mo41853h() {
        return (View) this.f4959d.get();
    }

    /* renamed from: i */
    public boolean mo41854i() {
        return this.f4961f && !this.f4962g;
    }

    /* renamed from: j */
    public boolean mo41855j() {
        return this.f4961f;
    }

    /* renamed from: k */
    public boolean mo41856k() {
        return this.f4962g;
    }

    /* renamed from: l */
    public boolean mo41857l() {
        return this.f4957b.mo41832a();
    }

    /* renamed from: m */
    public boolean mo41858m() {
        return this.f4957b.mo41833b();
    }
}
