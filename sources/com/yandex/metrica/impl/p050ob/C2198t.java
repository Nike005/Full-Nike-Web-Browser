package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1807a;
import com.yandex.metrica.impl.C1812ad;
import com.yandex.metrica.impl.C1877ba;
import com.yandex.metrica.impl.C1895bj;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.utils.C2228j;
import java.io.Closeable;
import java.util.concurrent.Executor;

/* renamed from: com.yandex.metrica.impl.ob.t */
public class C2198t implements C2200u {

    /* renamed from: a */
    private boolean f3788a;

    /* renamed from: b */
    private boolean f3789b;

    /* renamed from: c */
    private final HandlerThread f3790c;

    /* renamed from: d */
    private final Handler f3791d;

    /* renamed from: e */
    private final Context f3792e;

    /* renamed from: f */
    private final C2190r f3793f;

    /* renamed from: g */
    private C2016ca f3794g;

    /* renamed from: h */
    private C2018cc f3795h;

    /* renamed from: i */
    private C2013by f3796i;

    /* renamed from: j */
    private C2019cd f3797j;

    /* renamed from: k */
    private CounterConfiguration f3798k;

    /* renamed from: l */
    private final C1877ba f3799l;

    /* renamed from: m */
    private C1895bj f3800m;

    /* renamed from: n */
    private C2000bn f3801n;

    /* renamed from: o */
    private C2201v f3802o;

    /* renamed from: p */
    private C1807a f3803p;

    /* renamed from: q */
    private C2189q f3804q;

    /* renamed from: r */
    private long f3805r;

    /* renamed from: s */
    private long f3806s;

    /* renamed from: t */
    private int f3807t;

    /* renamed from: u */
    private int f3808u;

    /* renamed from: v */
    private volatile C1968bi f3809v;

    /* renamed from: w */
    private final C2228j f3810w;

    /* renamed from: x */
    private Runnable f3811x;

    /* renamed from: a */
    public C1968bi mo17838a() {
        return this.f3809v;
    }

    public C2198t(Context context, Executor executor, C2190r rVar, CounterConfiguration counterConfiguration, C2189q qVar) {
        this(context, executor, rVar, counterConfiguration, qVar, new C1877ba());
    }

    C2198t(Context context, Executor executor, C2190r rVar, CounterConfiguration counterConfiguration, C2189q qVar, C1877ba baVar) {
        this.f3788a = false;
        this.f3789b = false;
        this.f3810w = new C2228j();
        this.f3811x = new Runnable() {
            public void run() {
                C2198t.this.mo17850c();
            }
        };
        this.f3799l = baVar;
        this.f3792e = context.getApplicationContext();
        this.f3793f = rVar;
        this.f3798k = counterConfiguration;
        if (m5796J()) {
            C2004bq b = C2003bp.m5024a(this.f3792e).mo17288b(mo17865l());
            this.f3794g = new C2016ca(b);
            this.f3796i = new C2013by(b);
        }
        C2003bp a = C2003bp.m5024a(this.f3792e);
        this.f3795h = new C2018cc(a.mo17287b());
        this.f3797j = new C2019cd(a.mo17290d(), mo17865l().mo17819b());
        int libraryApiLevel = YandexMetrica.getLibraryApiLevel();
        if (m5796J()) {
            long j = (long) libraryApiLevel;
            if (this.f3794g.mo17368d() < j) {
                new C2191s(this, new C2058db(mo17832D())).mo17823a();
                this.f3794g.mo17387u(j).mo17398h();
            }
        }
        if (m5796J()) {
            this.f3801n = new C2000bn(this, C2003bp.m5024a(this.f3792e).mo17285a(mo17865l()));
            this.f3805r = this.f3794g.mo17364c(0);
            this.f3806s = this.f3794g.mo17369d(0);
            this.f3807t = this.f3794g.mo17352a(-1);
            this.f3808u = C1897bk.m4655b(context, rVar.mo17819b());
            this.f3809v = new C1968bi(this, this.f3794g);
            this.f3804q = qVar;
            this.f3803p = qVar.mo17815a(this, this.f3794g);
            if (mo17869p().mo17906b()) {
                mo17869p().mo17903a("Read app environment for component %s. Value: %s", mo17865l().toString(), this.f3803p.mo16757b().f2885a);
            }
        }
        HandlerThread handlerThread = new HandlerThread("TaskHandler [" + rVar.mo17819b() + "]");
        this.f3790c = handlerThread;
        handlerThread.start();
        this.f3791d = new Handler(this.f3790c.getLooper());
        this.f3799l.mo16975a(this);
        this.f3800m = new C1895bj(this, executor);
        C2000bn bnVar = this.f3801n;
        if (bnVar != null) {
            bnVar.mo17272a((C2200u) this);
        }
        this.f3802o = new C1932ab(new C2204y(this));
    }

    /* renamed from: a */
    public void mo17841a(C1915h hVar) {
        if (mo17869p().mo17906b()) {
            mo17869p().mo17927a(hVar, "Event received on service");
        }
        if (C1897bk.m4657b(this.f3799l.mo16969a())) {
            this.f3799l.mo16987c(this);
            this.f3802o.mo17881a(hVar);
        }
    }

    /* renamed from: b */
    public void mo17847b(C1915h hVar) {
        this.f3802o.mo17881a(hVar);
    }

    /* renamed from: J */
    private boolean m5796J() {
        return !this.f3793f.mo17820c();
    }

    /* renamed from: c */
    public void mo17851c(C1915h hVar) {
        m5798b(hVar, this.f3809v.mo17250e());
    }

    /* renamed from: d */
    public void mo17853d(C1915h hVar) {
        if (this.f3809v.mo17247b(hVar)) {
            if (this.f3796i.mo17322d()) {
                m5798b(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_START), this.f3809v.mo17249d());
            } else if (hVar.mo17118c() == C2208p.C2209a.EVENT_TYPE_FIRST_ACTIVATION.mo17884a()) {
                m5798b(hVar, this.f3809v.mo17249d());
                m5798b(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_START), this.f3809v.mo17249d());
                return;
            }
        }
        m5798b(hVar, this.f3809v.mo17249d());
    }

    /* renamed from: b */
    private void m5798b(C1915h hVar, C1969bj bjVar) {
        if (TextUtils.isEmpty(hVar.mo17132k())) {
            hVar.mo17105a(mo17858g());
        }
        this.f3801n.mo17271a(hVar, bjVar, this.f3803p.mo16757b());
        this.f3800m.mo17080b();
    }

    /* renamed from: a */
    public synchronized void mo17840a(CounterConfiguration counterConfiguration) {
        this.f3798k = counterConfiguration;
        this.f3799l.mo16995e(this);
    }

    /* renamed from: b */
    public void mo17845b() {
        if ((this.f3801n.mo17264a() >= ((long) this.f3798k.mo16575c())) || this.f3788a) {
            mo17856f();
            this.f3788a = false;
        }
    }

    /* renamed from: c */
    public synchronized void mo17850c() {
        this.f3789b = true;
        C1897bk.m4645a((Closeable) this.f3800m);
        C1897bk.m4645a((Closeable) this.f3801n);
        this.f3791d.removeCallbacksAndMessages((Object) null);
        this.f3790c.quit();
    }

    /* renamed from: d */
    public void mo17852d() {
        this.f3791d.postDelayed(this.f3811x, C1812ad.f2887a);
    }

    /* renamed from: e */
    public synchronized void mo17854e() {
        this.f3800m.mo17081c();
    }

    /* renamed from: f */
    public synchronized void mo17856f() {
        this.f3800m.mo17079a();
    }

    /* renamed from: g */
    public String mo17858g() {
        return this.f3794g.mo17357a((String) null);
    }

    /* renamed from: h */
    public C1877ba mo17860h() {
        return this.f3799l;
    }

    /* renamed from: i */
    public C2000bn mo17862i() {
        return this.f3801n;
    }

    /* renamed from: j */
    public CounterConfiguration mo17863j() {
        return this.f3798k;
    }

    /* renamed from: k */
    public ResultReceiver mo17864k() {
        CounterConfiguration counterConfiguration = this.f3798k;
        if (counterConfiguration != null) {
            return counterConfiguration.mo16558a();
        }
        return null;
    }

    /* renamed from: l */
    public C2190r mo17865l() {
        return this.f3793f;
    }

    /* renamed from: m */
    public Context mo17866m() {
        return this.f3792e;
    }

    /* renamed from: n */
    public Handler mo17867n() {
        return this.f3791d;
    }

    /* renamed from: o */
    public synchronized boolean mo17868o() {
        return this.f3789b;
    }

    /* renamed from: a */
    public void mo17839a(CounterConfiguration.C1774a aVar) {
        this.f3794g.mo17355a(aVar).mo17398h();
        if (this.f3792e.getPackageName().equals(this.f3793f.mo17819b())) {
            this.f3795h.mo17404a(aVar).mo17398h();
        }
    }

    /* renamed from: p */
    public C2228j mo17869p() {
        CounterConfiguration counterConfiguration;
        if (!this.f3810w.mo17906b() && (counterConfiguration = this.f3798k) != null && counterConfiguration.mo16606s()) {
            this.f3810w.mo17899a();
        }
        return this.f3810w;
    }

    /* renamed from: e */
    public void mo17855e(C1915h hVar) {
        mo17849b(true);
        mo17853d(hVar);
        mo17875v();
    }

    /* renamed from: f */
    public void mo17857f(C1915h hVar) {
        mo17853d(hVar);
        m5797K();
    }

    /* renamed from: g */
    public void mo17859g(C1915h hVar) {
        mo17853d(hVar);
        mo17876w();
    }

    /* renamed from: q */
    public void mo17870q() {
        m5797K();
    }

    /* renamed from: r */
    public void mo17871r() {
        mo17876w();
    }

    /* renamed from: h */
    public void mo17861h(C1915h hVar) {
        this.f3803p.mo16755a(hVar.mo17131j());
        C1807a.C1808a b = this.f3803p.mo16757b();
        if (this.f3804q.mo17816a(b, this.f3794g) && mo17869p().mo17906b()) {
            mo17869p().mo17903a("Save new app environment for %s. Value: %s", mo17865l(), b.f2885a);
        }
    }

    /* renamed from: s */
    public void mo17872s() {
        this.f3803p.mo16754a();
        this.f3804q.mo17817b(this.f3803p.mo16757b(), this.f3794g);
    }

    /* renamed from: a */
    public void mo17843a(String str) {
        this.f3794g.mo17361b(str).mo17398h();
    }

    /* renamed from: b */
    public void mo17848b(String str) {
        this.f3795h.mo17409b(str).mo17398h();
        this.f3797j.mo17448s(str).mo17398h();
    }

    /* renamed from: t */
    public String mo17873t() {
        return this.f3795h.mo17405a((String) null);
    }

    /* renamed from: u */
    public void mo17874u() {
        this.f3795h.mo17407b().mo17398h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: w */
    public void mo17876w() {
        Context context = this.f3792e;
        int b = C1897bk.m4655b(context, context.getPackageName());
        this.f3807t = b;
        this.f3794g.mo17360b(b);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: y */
    public boolean mo17878y() {
        return this.f3807t < this.f3808u;
    }

    /* renamed from: A */
    public boolean mo17829A() {
        return mo17863j().mo16612x() && mo17877x() && mo17860h().mo16964J() && mo17860h().mo16967M();
    }

    /* renamed from: B */
    public boolean mo17830B() {
        return mo17878y() && mo17860h().mo16965K() && mo17860h().mo16967M();
    }

    /* renamed from: C */
    public C2013by mo17831C() {
        return this.f3796i;
    }

    /* renamed from: D */
    public C2060dc mo17832D() {
        return new C2060dc(this.f3792e, this.f3793f.mo17818a());
    }

    /* renamed from: E */
    public C2019cd mo17833E() {
        return this.f3797j;
    }

    /* renamed from: F */
    public C2016ca mo17834F() {
        return this.f3794g;
    }

    /* renamed from: a */
    public void mo17844a(boolean z) {
        this.f3797j.mo17428e(z).mo17398h();
    }

    /* renamed from: H */
    public boolean mo17836H() {
        return !this.f3798k.mo16610w() || !this.f3797j.mo17427d();
    }

    /* renamed from: b */
    public void mo17849b(boolean z) {
        this.f3788a = z;
    }

    /* renamed from: b */
    public void mo17846b(CounterConfiguration counterConfiguration) {
        this.f3798k.mo16564a(counterConfiguration);
    }

    /* renamed from: I */
    public C2016ca mo17837I() {
        return this.f3794g;
    }

    /* renamed from: a */
    public void mo17842a(C1915h hVar, C1969bj bjVar) {
        m5798b(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_ALIVE), bjVar);
    }

    /* renamed from: v */
    public void mo17875v() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f3805r = currentTimeMillis;
        this.f3794g.mo17379m(currentTimeMillis).mo17398h();
    }

    /* renamed from: K */
    private void m5797K() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f3806s = currentTimeMillis;
        this.f3794g.mo17380n(currentTimeMillis).mo17398h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: x */
    public boolean mo17877x() {
        return (System.currentTimeMillis() / 1000) - this.f3806s > C1967bh.f3238b;
    }

    /* renamed from: z */
    public boolean mo17879z() {
        return ((((System.currentTimeMillis() / 1000) - this.f3805r) > C1967bh.f3237a ? 1 : (((System.currentTimeMillis() / 1000) - this.f3805r) == C1967bh.f3237a ? 0 : -1)) > 0) && mo17860h().mo16967M();
    }

    /* renamed from: G */
    public boolean mo17835G() {
        return this.f3795h.mo17402a() == CounterConfiguration.C1774a.TRUE && this.f3794g.mo17365c() == CounterConfiguration.C1774a.TRUE;
    }
}
