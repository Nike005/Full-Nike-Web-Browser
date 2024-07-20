package com.tappx.p048a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: com.tappx.a.c3 */
public class C1341c3 {

    /* renamed from: a */
    private final BroadcastReceiver f1659a;

    /* renamed from: b */
    private final Context f1660b;

    /* renamed from: c */
    private int f1661c = -1;

    /* renamed from: d */
    private C1344c f1662d;

    /* renamed from: e */
    private boolean f1663e = false;

    /* renamed from: com.tappx.a.c3$a */
    class C1342a extends BroadcastReceiver {
        C1342a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.USER_PRESENT".equals(action)) {
                    C1341c3.this.m2344a(true);
                } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    C1341c3.this.m2344a(false);
                }
            }
        }
    }

    /* renamed from: com.tappx.a.c3$c */
    public interface C1344c {
        void onDeviceScreenStateChanged(boolean z);
    }

    public C1341c3(Context context) {
        this.f1660b = context.getApplicationContext();
        this.f1659a = new C1342a();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.f1660b.registerReceiver(this.f1659a, intentFilter);
    }

    /* renamed from: c */
    private void m2345c() {
        C1344c cVar = this.f1662d;
        if (cVar != null) {
            cVar.onDeviceScreenStateChanged(mo15603b());
        }
    }

    /* renamed from: b */
    public boolean mo15603b() {
        return this.f1661c != 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2344a(boolean z) {
        if (z != this.f1661c) {
            this.f1661c = z ? 1 : 0;
            m2345c();
        }
    }

    /* renamed from: a */
    public void mo15602a(C1344c cVar) {
        this.f1662d = cVar;
    }

    /* renamed from: a */
    public void mo15601a() {
        if (!this.f1663e) {
            this.f1663e = true;
            mo15602a((C1344c) null);
            this.f1660b.unregisterReceiver(this.f1659a);
        }
    }

    /* renamed from: com.tappx.a.c3$b */
    public static class C1343b {

        /* renamed from: a */
        private static volatile C1343b f1665a;

        /* renamed from: a */
        public static C1343b m2349a() {
            C1343b bVar = f1665a;
            if (bVar == null) {
                synchronized (C1343b.class) {
                    bVar = f1665a;
                    if (bVar == null) {
                        bVar = new C1343b();
                    }
                }
            }
            return bVar;
        }

        /* renamed from: a */
        public C1341c3 mo15605a(Context context) {
            return new C1341c3(context);
        }
    }
}
