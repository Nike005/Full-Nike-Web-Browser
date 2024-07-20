package com.appnext.base;

import android.content.Context;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4903g;
import com.appnext.base.p082b.C4905i;
import com.appnext.base.p082b.C4906j;
import com.appnext.base.services.OperationService;
import com.appnext.core.C4967f;

public class Appnext {

    /* renamed from: dp */
    private static final Appnext f4550dp = new Appnext();
    /* access modifiers changed from: private */

    /* renamed from: do */
    public Context f4551do = null;
    /* access modifiers changed from: private */

    /* renamed from: dq */
    public boolean f4552dq = false;

    private Appnext() {
    }

    /* renamed from: T */
    protected static Appnext m6464T() {
        return f4550dp;
    }

    /* renamed from: b */
    private void m6468b(Context context) throws ExceptionInInitializerError {
        if (context != null) {
            try {
                if (!this.f4552dq || this.f4551do == null) {
                    this.f4552dq = true;
                    C4901e.init(context);
                    this.f4551do = context.getApplicationContext();
                    if (C4906j.m6598a(OperationService.class)) {
                        C4903g.m6575aN().mo41004b(new Runnable() {
                            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void run() {
                                /*
                                    r14 = this;
                                    android.content.Context r0 = com.appnext.base.p082b.C4901e.getContext()     // Catch:{ all -> 0x00a6 }
                                    r1 = 1
                                    java.lang.String r0 = com.appnext.core.C4967f.m6827b((android.content.Context) r0, (boolean) r1)     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "aidForSend"
                                    com.appnext.base.b.d$a r3 = com.appnext.base.p082b.C4899d.C4900a.String     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.p082b.C4906j.m6596a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.base.p082b.C4899d.C4900a) r3)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f4551do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    boolean r0 = com.appnext.base.p082b.C4906j.m6608i(r0)     // Catch:{ all -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0039
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    r2 = 0
                                    boolean unused = r0.f4552dq = false     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "lat"
                                    r0.putBoolean(r2, r1)     // Catch:{ all -> 0x00a6 }
                                    return
                                L_0x0039:
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.p082b.C4901e.init(r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f4551do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext.m6469b((com.appnext.base.Appnext) r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.a.a r2 = com.appnext.base.p078a.C4880a.m6472X()     // Catch:{  }
                                    com.appnext.base.a.c.c r2 = r2.mo40942ab()     // Catch:{  }
                                    java.util.List r2 = r2.mo40977as()     // Catch:{  }
                                    if (r2 == 0) goto L_0x00a6
                                    int r2 = r2.size()     // Catch:{  }
                                    if (r2 != 0) goto L_0x00a6
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{  }
                                    java.lang.String r3 = "cdm"
                                    r2.<init>(r3)     // Catch:{  }
                                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{  }
                                    r2.append(r3)     // Catch:{  }
                                    java.lang.String r12 = r2.toString()     // Catch:{  }
                                    com.appnext.base.a.b.c r2 = new com.appnext.base.a.b.c     // Catch:{  }
                                    java.lang.String r6 = "on"
                                    java.lang.String r7 = "1"
                                    java.lang.String r8 = "hour"
                                    java.lang.String r9 = "1"
                                    java.lang.String r10 = "interval"
                                    java.lang.String r11 = "cdm"
                                    r13 = 0
                                    r5 = r2
                                    r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{  }
                                    com.appnext.base.a.a r3 = com.appnext.base.p078a.C4880a.m6472X()     // Catch:{  }
                                    com.appnext.base.a.c.c r3 = r3.mo40942ab()     // Catch:{  }
                                    r3.mo40975a((com.appnext.base.p078a.p080b.C4887c) r2)     // Catch:{  }
                                    com.appnext.base.services.b.a r0 = com.appnext.base.services.p084b.C4922a.m6693d(r0)     // Catch:{  }
                                    r0.mo41070a(r2, r1)     // Catch:{  }
                                L_0x00a6:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.Appnext.C48771.run():void");
                            }
                        });
                        return;
                    }
                    return;
                }
                this.f4551do = context.getApplicationContext();
            } catch (Throwable unused) {
            }
        } else {
            throw new ExceptionInInitializerError("Cannot init Appnext with null context");
        }
    }

    public static void setParam(String str, String str2) {
        try {
            if (C4901e.getContext() != null && str.hashCode() == 951500826) {
                C4906j.m6596a(C4899d.f4628fo, str2, C4899d.C4900a.Boolean);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: U */
    private void m6465U() {
        try {
            String b = C4967f.m6827b(this.f4551do, true);
            if (!b.equals(C4905i.m6591aR().getString(C4905i.f4637fB, ""))) {
                C4905i.m6591aR().clear();
                C4905i.m6591aR().putString(C4905i.f4637fB, b);
            }
        } catch (Throwable unused) {
        }
    }

    public static void init(Context context) {
        Appnext appnext = f4550dp;
        if (context != null) {
            try {
                if (!appnext.f4552dq || appnext.f4551do == null) {
                    appnext.f4552dq = true;
                    C4901e.init(context);
                    appnext.f4551do = context.getApplicationContext();
                    if (C4906j.m6598a(OperationService.class)) {
                        C4903g.m6575aN().mo41004b(new Runnable() {
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void run() {
                                /*
                                    r14 = this;
                                    android.content.Context r0 = com.appnext.base.p082b.C4901e.getContext()     // Catch:{ all -> 0x00a6 }
                                    r1 = 1
                                    java.lang.String r0 = com.appnext.core.C4967f.m6827b((android.content.Context) r0, (boolean) r1)     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "aidForSend"
                                    com.appnext.base.b.d$a r3 = com.appnext.base.p082b.C4899d.C4900a.String     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.p082b.C4906j.m6596a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.base.p082b.C4899d.C4900a) r3)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f4551do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    boolean r0 = com.appnext.base.p082b.C4906j.m6608i(r0)     // Catch:{ all -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0039
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    r2 = 0
                                    boolean unused = r0.f4552dq = false     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    java.lang.String r2 = "lat"
                                    r0.putBoolean(r2, r1)     // Catch:{ all -> 0x00a6 }
                                    return
                                L_0x0039:
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.p082b.C4901e.init(r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.b.i r0 = com.appnext.base.p082b.C4905i.m6591aR()     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r2 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r2 = r2.f4551do     // Catch:{ all -> 0x00a6 }
                                    r0.init(r2)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext.m6469b((com.appnext.base.Appnext) r0)     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.Appnext r0 = com.appnext.base.Appnext.this     // Catch:{ all -> 0x00a6 }
                                    android.content.Context r0 = r0.f4551do     // Catch:{ all -> 0x00a6 }
                                    com.appnext.base.a.a r2 = com.appnext.base.p078a.C4880a.m6472X()     // Catch:{  }
                                    com.appnext.base.a.c.c r2 = r2.mo40942ab()     // Catch:{  }
                                    java.util.List r2 = r2.mo40977as()     // Catch:{  }
                                    if (r2 == 0) goto L_0x00a6
                                    int r2 = r2.size()     // Catch:{  }
                                    if (r2 != 0) goto L_0x00a6
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{  }
                                    java.lang.String r3 = "cdm"
                                    r2.<init>(r3)     // Catch:{  }
                                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{  }
                                    r2.append(r3)     // Catch:{  }
                                    java.lang.String r12 = r2.toString()     // Catch:{  }
                                    com.appnext.base.a.b.c r2 = new com.appnext.base.a.b.c     // Catch:{  }
                                    java.lang.String r6 = "on"
                                    java.lang.String r7 = "1"
                                    java.lang.String r8 = "hour"
                                    java.lang.String r9 = "1"
                                    java.lang.String r10 = "interval"
                                    java.lang.String r11 = "cdm"
                                    r13 = 0
                                    r5 = r2
                                    r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{  }
                                    com.appnext.base.a.a r3 = com.appnext.base.p078a.C4880a.m6472X()     // Catch:{  }
                                    com.appnext.base.a.c.c r3 = r3.mo40942ab()     // Catch:{  }
                                    r3.mo40975a((com.appnext.base.p078a.p080b.C4887c) r2)     // Catch:{  }
                                    com.appnext.base.services.b.a r0 = com.appnext.base.services.p084b.C4922a.m6693d(r0)     // Catch:{  }
                                    r0.mo41070a(r2, r1)     // Catch:{  }
                                L_0x00a6:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.Appnext.C48771.run():void");
                            }
                        });
                        return;
                    }
                    return;
                }
                appnext.f4551do = context.getApplicationContext();
            } catch (Throwable unused) {
            }
        } else {
            throw new ExceptionInInitializerError("Cannot init Appnext with null context");
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m6469b(Appnext appnext) {
        try {
            String b = C4967f.m6827b(appnext.f4551do, true);
            if (!b.equals(C4905i.m6591aR().getString(C4905i.f4637fB, ""))) {
                C4905i.m6591aR().clear();
                C4905i.m6591aR().putString(C4905i.f4637fB, b);
            }
        } catch (Throwable unused) {
        }
    }
}
