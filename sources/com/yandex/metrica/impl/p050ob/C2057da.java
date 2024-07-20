package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.da */
public class C2057da {

    /* renamed from: a */
    private Context f3446a;

    public C2057da(Context context) {
        this.f3446a = context;
    }

    /* renamed from: a */
    public void mo17521a() {
        SharedPreferences a = C2069dl.m5401a(this.f3446a, "_bidoptpreferences");
        if (a.getAll().size() > 0) {
            String string = a.getString(C2066di.f3517c.mo17604a(), (String) null);
            C2066di diVar = new C2066di(this.f3446a);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(diVar.mo17592a((String) null))) {
                diVar.mo17601j(string).mo17551k();
                a.edit().remove(C2066di.f3517c.mo17604a()).apply();
            }
            Map<String, ?> all = a.getAll();
            if (all.size() > 0) {
                for (String next : m5295a(all, C2066di.f3518d.mo17604a())) {
                    String string2 = a.getString(new C2068dk(C2066di.f3518d.mo17604a(), next).mo17606b(), (String) null);
                    C2066di diVar2 = new C2066di(this.f3446a, next);
                    if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(diVar2.mo17593b((String) null))) {
                        diVar2.mo17600i(string2).mo17551k();
                    }
                }
            }
            a.edit().clear().apply();
        }
    }

    /* renamed from: a */
    private static List<String> m5295a(Map<String, ?> map, String str) {
        ArrayList arrayList = new ArrayList();
        for (String next : map.keySet()) {
            if (next.startsWith(str)) {
                arrayList.add(next.replace(str, ""));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public void mo17522b() {
        C2004bq d = C2003bp.m5024a(this.f3446a).mo17290d();
        SharedPreferences a = C2069dl.m5401a(this.f3446a, "_startupserviceinfopreferences");
        C2019cd cdVar = new C2019cd(d, (String) null);
        String string = a.getString(C2066di.f3517c.mo17604a(), (String) null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(cdVar.mo17415a((String) null))) {
            cdVar.mo17438k(string).mo17398h();
            a.edit().remove(C2066di.f3517c.mo17604a()).apply();
        }
        C2019cd cdVar2 = new C2019cd(d, this.f3446a.getPackageName());
        boolean z = a.getBoolean(C2066di.f3519e.mo17604a(), false);
        if (z) {
            cdVar2.mo17428e(z).mo17398h();
        }
        C2019cd cdVar3 = new C2019cd(d, (String) null);
        String string2 = a.getString(C2066di.f3520f.mo17604a(), (String) null);
        if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(cdVar3.mo17433h((String) null))) {
            cdVar3.mo17434i(string2).mo17398h();
        }
        m5296a(d, this.f3446a.getPackageName());
        for (String a2 : m5295a(a.getAll(), C2066di.f3518d.mo17604a())) {
            m5296a(d, a2);
        }
    }

    /* renamed from: a */
    private void m5296a(C2004bq bqVar, String str) {
        C2019cd cdVar = new C2019cd(bqVar, str);
        C2066di diVar = new C2066di(this.f3446a, str);
        String b = diVar.mo17593b((String) null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.mo17436j(b);
        }
        String a = diVar.mo17591a();
        if (!TextUtils.isEmpty(a)) {
            cdVar.mo17448s(a);
        }
        String d = diVar.mo17596d((String) null);
        if (!TextUtils.isEmpty(d)) {
            cdVar.mo17447p(d);
        }
        String f = diVar.mo17598f((String) null);
        if (!TextUtils.isEmpty(f)) {
            cdVar.mo17445n(f);
        }
        String g = diVar.mo17599g((String) null);
        if (!TextUtils.isEmpty(g)) {
            cdVar.mo17442m(g);
        }
        String c = diVar.mo17595c((String) null);
        if (!TextUtils.isEmpty(c)) {
            cdVar.mo17446o(c);
        }
        long a2 = diVar.mo17590a(-1);
        if (a2 != -1) {
            cdVar.mo17417b(a2);
        }
        String e = diVar.mo17597e((String) null);
        if (!TextUtils.isEmpty(e)) {
            cdVar.mo17440l(e);
        }
        cdVar.mo17398h();
        diVar.mo17594b();
    }
}
