package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tappx.a.e0 */
class C1386e0 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final Map<C1358d0<?>, Runnable> f1771a;

    /* renamed from: b */
    private final Handler f1772b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1322b0 f1773c;

    /* renamed from: com.tappx.a.e0$a */
    class C1387a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C1358d0 f1774a;

        C1387a(C1358d0 d0Var) {
            this.f1774a = d0Var;
        }

        public void run() {
            if (((Runnable) C1386e0.this.f1771a.remove(this.f1774a)) != null) {
                C1386e0.this.f1773c.mo15539a(this.f1774a);
            }
        }
    }

    public C1386e0() {
        this(new Handler(Looper.getMainLooper()));
    }

    C1386e0(Handler handler) {
        this.f1771a = new HashMap();
        this.f1772b = handler;
    }

    /* renamed from: a */
    public void mo15729a(C1322b0 b0Var) {
        this.f1773c = b0Var;
    }

    /* renamed from: a */
    public void mo15730a(C1358d0<?> d0Var, long j) {
        C1387a aVar = new C1387a(d0Var);
        this.f1771a.put(d0Var, aVar);
        this.f1772b.postDelayed(aVar, j);
    }

    /* renamed from: a */
    public boolean mo15731a(C1358d0<?> d0Var) {
        Runnable remove = this.f1771a.remove(d0Var);
        if (remove == null) {
            return false;
        }
        this.f1772b.removeCallbacks(remove);
        return true;
    }
}
