package com.p088b.p089a.p090a.p091a.p100h;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.p088b.p089a.p090a.p091a.p096d.C5129a;
import com.p088b.p089a.p090a.p091a.p096d.C5131b;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5138d;
import com.p088b.p089a.p090a.p091a.p097e.C5140f;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5157c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.h.a */
public class C5148a implements C5129a.C5130a {

    /* renamed from: a */
    private static C5148a f5017a = new C5148a();

    /* renamed from: b */
    private static Handler f5018b = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static Handler f5019c = null;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static final Runnable f5020j = new Runnable() {
        public void run() {
            C5148a.m7174a().m7184i();
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: k */
    public static final Runnable f5021k = new Runnable() {
        public void run() {
            if (C5148a.f5019c != null) {
                C5148a.f5019c.post(C5148a.f5020j);
                C5148a.f5019c.postDelayed(C5148a.f5021k, 200);
            }
        }
    };

    /* renamed from: d */
    private List<C5152a> f5022d = new ArrayList();

    /* renamed from: e */
    private int f5023e;

    /* renamed from: f */
    private C5131b f5024f = new C5131b();

    /* renamed from: g */
    private C5161b f5025g = new C5161b();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C5162c f5026h = new C5162c(new C5157c());

    /* renamed from: i */
    private double f5027i;

    /* renamed from: com.b.a.a.a.h.a$a */
    public interface C5152a {
        /* renamed from: a */
        void mo41932a(int i, long j);
    }

    C5148a() {
    }

    /* renamed from: a */
    public static C5148a m7174a() {
        return f5017a;
    }

    /* renamed from: a */
    private void m7176a(long j) {
        if (this.f5022d.size() > 0) {
            for (C5152a a : this.f5022d) {
                a.mo41932a(this.f5023e, j);
            }
        }
    }

    /* renamed from: a */
    private void m7177a(View view, C5129a aVar, JSONObject jSONObject, C5163d dVar) {
        aVar.mo41899a(view, jSONObject, this, dVar == C5163d.PARENT_VIEW);
    }

    /* renamed from: a */
    private boolean m7178a(View view, JSONObject jSONObject) {
        String a = this.f5025g.mo41947a(view);
        if (a == null) {
            return false;
        }
        C5135b.m7119a(jSONObject, a);
        this.f5025g.mo41954e();
        return true;
    }

    /* renamed from: b */
    private void m7179b(View view, JSONObject jSONObject) {
        ArrayList<String> b = this.f5025g.mo41949b(view);
        if (b != null) {
            C5135b.m7121a(jSONObject, (List<String>) b);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m7184i() {
        m7185j();
        mo41928e();
        m7186k();
    }

    /* renamed from: j */
    private void m7185j() {
        this.f5023e = 0;
        this.f5027i = C5138d.m7132a();
    }

    /* renamed from: k */
    private void m7186k() {
        m7176a((long) (C5138d.m7132a() - this.f5027i));
    }

    /* renamed from: l */
    private void m7187l() {
        if (f5019c == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            f5019c = handler;
            handler.post(f5020j);
            f5019c.postDelayed(f5021k, 200);
        }
    }

    /* renamed from: m */
    private void m7188m() {
        Handler handler = f5019c;
        if (handler != null) {
            handler.removeCallbacks(f5021k);
            f5019c = null;
        }
    }

    /* renamed from: a */
    public void mo41900a(View view, C5129a aVar, JSONObject jSONObject) {
        C5163d c;
        if (C5140f.m7149d(view) && (c = this.f5025g.mo41951c(view)) != C5163d.UNDERLYING_VIEW) {
            JSONObject a = aVar.mo41898a(view);
            C5135b.m7122a(jSONObject, a);
            if (!m7178a(view, a)) {
                m7179b(view, a);
                m7177a(view, aVar, a, c);
            }
            this.f5023e++;
        }
    }

    /* renamed from: b */
    public void mo41925b() {
        m7187l();
    }

    /* renamed from: c */
    public void mo41926c() {
        mo41927d();
        this.f5022d.clear();
        f5018b.post(new Runnable() {
            public void run() {
                C5148a.this.f5026h.mo41956b();
            }
        });
    }

    /* renamed from: d */
    public void mo41927d() {
        m7188m();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo41928e() {
        this.f5025g.mo41952c();
        double a = C5138d.m7132a();
        C5129a a2 = this.f5024f.mo41901a();
        if (this.f5025g.mo41950b().size() > 0) {
            this.f5026h.mo41957b(a2.mo41898a((View) null), this.f5025g.mo41950b(), a);
        }
        if (this.f5025g.mo41948a().size() > 0) {
            JSONObject a3 = a2.mo41898a((View) null);
            m7177a((View) null, a2, a3, C5163d.PARENT_VIEW);
            C5135b.m7118a(a3);
            this.f5026h.mo41955a(a3, this.f5025g.mo41948a(), a);
        } else {
            this.f5026h.mo41956b();
        }
        this.f5025g.mo41953d();
    }
}
