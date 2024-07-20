package com.p088b.p089a.p090a.p091a.p095c;

import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* renamed from: com.b.a.a.a.c.a */
public class C5120a {

    /* renamed from: a */
    private static C5120a f4966a = new C5120a();

    /* renamed from: b */
    private final ArrayList<C5118i> f4967b = new ArrayList<>();

    /* renamed from: c */
    private final ArrayList<C5118i> f4968c = new ArrayList<>();

    private C5120a() {
    }

    /* renamed from: a */
    public static C5120a m7050a() {
        return f4966a;
    }

    /* renamed from: a */
    public void mo41865a(C5118i iVar) {
        this.f4967b.add(iVar);
    }

    /* renamed from: b */
    public Collection<C5118i> mo41866b() {
        return Collections.unmodifiableCollection(this.f4967b);
    }

    /* renamed from: b */
    public void mo41867b(C5118i iVar) {
        boolean d = mo41870d();
        this.f4968c.add(iVar);
        if (!d) {
            C5127e.m7086a().mo41895b();
        }
    }

    /* renamed from: c */
    public Collection<C5118i> mo41868c() {
        return Collections.unmodifiableCollection(this.f4968c);
    }

    /* renamed from: c */
    public void mo41869c(C5118i iVar) {
        boolean d = mo41870d();
        this.f4967b.remove(iVar);
        this.f4968c.remove(iVar);
        if (d && !mo41870d()) {
            C5127e.m7086a().mo41896c();
        }
    }

    /* renamed from: d */
    public boolean mo41870d() {
        return this.f4968c.size() > 0;
    }
}
